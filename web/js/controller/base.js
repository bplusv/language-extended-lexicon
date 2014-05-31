/* 
 * The MIT License
 *
 * Copyright 2012 Luis Salazar <bp.lusv@gmail.com>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

'use strict';
var centralContainerOffset;
var infoBubble;
var appContext = '/lel';
var projectSymbols = {};
var symbolsRegex;
var baseController = {};

baseController.ajaxRequest = function(request, callback, params, async) {
    
    var response, redirect;
    var isRequesting = false;
    if (!isRequesting) {
        isRequesting = true;
        $('#notification').hide();
        $('#ajaxLoader').show();
        $.ajax({
            type: /\/([^\/]*)\//.exec(request)[1],
            url: appContext + request,
            data: params,
            timeout: 5000,
            success: function(data) {
                isRequesting = false;
                response = data;
                if ($(response).find('sessionTimeOut').text() === 'true') {
                    window.location.href = appContext + '/signIn';
                    return;
                }
                redirect = callback && callback(response);
            },
            error: function(jqXHR, textStatus, errorThrown) {
                isRequesting = false;
                response = $('<root>').append(
                        $('<message>').text(
                        $('#messages .networkFail').text()));
            },
            complete: function() {
                isRequesting = false;
                baseController.updateMainInterface(response, redirect);
            },
            async: async === undefined ? true : async
        });
    }
    
};

baseController.notify = function(cssClass, message) {
    
    var $notification = $('#notification');
    $notification
        .removeClass()
        .addClass(cssClass)
        .stop(true, true)
        .show()
        .html(message)
        .animate({
            opacity: 1
        }, 3000)
        .fadeOut(500);
    
};

baseController.regexEscape = function(text) {
    return text.replace(/[-[\]{}()*+?.,\\^$|#\s]/g, "\\$&");
};

baseController.updateSymbolsRegex = function() {
    var words = [];
    for (var key in projectSymbols) {
        if (projectSymbols.hasOwnProperty(key)) {
            words.push(key);
        }
    }
    words = words.sort().reverse().map(baseController.regexEscape);
    symbolsRegex = RegExp("^((" + words.join(")|(") + "))", "");
};

baseController.updateProjectSymbols = function() {
    baseController.ajaxRequest('/get/data/projectSymbols', function(response) {
        
        projectSymbols = {};
        var $xmlSymbols = $(response).find('symbols').children();
        $xmlSymbols.each(function(i, e) {
            projectSymbols[$(e).children('name').text()] = $(e).attr('id');
        });
        baseController.updateSymbolsRegex();
        
    }, null, false);
};

baseController.updateCodeMirrorLelMode = function() {
    CodeMirror.defineMode("lel", function() {
        function tokenLexer(stream) {
            if (stream.eatSpace())
                return null;
            var match = stream.match(symbolsRegex);
            if (match && match[0]) {
                return 'symbol-' + projectSymbols[match[0]];
            } else {
                stream.next();
                return null;
            }
        }
        return {
            token: function(stream) {
                return tokenLexer(stream);
            }
        };
    });
    CodeMirror.defineMIME("text/x-lel", "lel");
};

baseController.updateCodeMirrorEditors = function() {
    baseController.updateCodeMirrorLelMode();
    $('textarea.symbolicEditor').each(function(i, e) {
        var cm = $(e).data('codeMirror');
        if (cm !== undefined) {
            cm.setOption('mode', 'text/x-lel');
        } else {
            //var w = $(e).outerWidth();
            //var h = $(e).outerHeight();
            var editor = CodeMirror.fromTextArea(e,
                    {
                        'onUpdate': baseController.tagCodeMirrorSymbols,
                        'onScroll': baseController.tagCodeMirrorSymbols,
                        'mode': 'text/x-lel',
                        'lineWrapping': true
                    });
            $(e).data('codeMirror', editor);
            //editor.setSize(w, h);
            //editor.refresh();
        }
    });
    baseController.tagCodeMirrorSymbols();
};

baseController.tagCodeMirrorSymbols = function() {
    var $cms = $('div.CodeMirror');
    $cms.each(function(i, cm) {
        var $cm = $(cm);
        var cmTop = $cm.offset().top;
        var cmBottom = $cm.offset().top + $cm.outerHeight();
        var cmLeft = $cm.offset().left;
        var cmRight = $cm.offset().left + $cm.outerWidth();
        var $symbols = $cm.find('div.CodeMirror-lines span[class^=cm-symbol-]').
                filter(function() {
                    var curOffset = $(this).offset();
                    var curTop = curOffset.top;
                    var curLeft = curOffset.left;
                    return curTop > cmTop && curTop < cmBottom &&
                            curLeft > cmLeft && curLeft < cmRight;
                });
        $symbols.each(function(i, e) {
            $(e).wrapInner('<a class="symbol" href="#!/classify?sy=' +
                    /cm-symbol-(.*)/.exec($(e).attr('class'))[1] + '"></a>');
            $(e).attr('class', 'symbol');
        });
    });
};

baseController.tagSymbols = function(text) {
    var result = '';
    while (text) {
        var match = text.match(symbolsRegex);
        if (match && match[0]) {
            result += $('<a>').addClass('symbol').attr('href', '#!/classify?sy=' +
                    projectSymbols[match[0]]).text(match[0]).get(0).outerHTML;
            text = text.substring(match[0].length);
        } else {
            result += $('<span>').text(text.charAt(0)).html();
            text = text.substring(1);
        }
    }
    return result;
};

baseController.getPwdScoreCssClass = function(pwdScore) {
    var pwdClass = '';
    if (pwdScore >= 90) {
        pwdClass = 'very_secure';
    } else if (pwdScore >= 80) {
        pwdClass = 'secure';
    } else if (pwdScore >= 70) {
        pwdClass = 'very_strong';
    } else if (pwdScore >= 60) {
        pwdClass = 'strong';
    } else if (pwdScore >= 50) {
        pwdClass = 'average';
    } else if (pwdScore >= 25) {
        pwdClass = 'weak';
    } else if (pwdScore >= 0) {
        pwdClass = 'very_weak';
    }
    return pwdClass;
};

baseController.updateMainInterface = function(response, redirect) {
    if (redirect) {
        if (window.location.hash.indexOf(redirect) > -1) {
            $(window).trigger('hashchange');
        } else {
            window.location.hash = redirect;
        }
    }
    $('.overflowEllipsis').each(function(i, e) {
        if ($(e).children('.overflowText').length < 1) {
            $(e).wrapInner('<span class="overflowText">');
        }
    });
    $('.tabs a').removeClass('selected');
    if (window.location.hash.indexOf('/explore') > 0) {
        $('#exploreTab').addClass('selected');
    } else if (window.location.hash.indexOf('/document') > 0) {
        $('#documentTab').addClass('selected');
    } else if (window.location.hash.indexOf('/manageProjects') > 0) {
        $('#projectsTab').addClass('selected');
    }
    $('#ajaxLoader').hide();
    if (response) {
        var success = $(response).find('success').text();
        var message = $(response).find('message').text();
        if (message)
            baseController.notify(success === 'true' ? 'success' : 'fail', message);
    }
};

baseController.changeLanguage = function(lang) {
    baseController.ajaxRequest('/post/chooseLanguage', function(response) {
        if ($(response).find('success').text() === 'true') {
            window.location.reload();
        }
    }, 'language=' + lang);
};

baseController.changeView = function(hash) {
    if (window.location.href.indexOf('/signIn') < 0 &&
            window.location.href.indexOf('/register') < 0) {
        if (hash) {
            if ($('#dcUpdateForm').length > 0) {
                $('#dcDocumentContent').data('codeMirror').save();
                baseController.ajaxRequest('/post/updateDocument', null,
                        $('#dcUpdateForm').serialize(), false);
            }
            hash = hash.replace(/#!/, '');
            hash = hash.split('?');
            baseController.ajaxRequest('/get/view' + hash[0],
                    function(res) {
                        switch (hash[0]) {
                            case '/classify':
                                baseController.updateProjectSymbols();
                                $('#central').html(res);
                                baseController.updateCodeMirrorEditors();
                                classifyController.updateInterface();
                                break;
                            case '/document':
                                baseController.updateProjectSymbols();
                                $('#central').html(res);
                                baseController.updateCodeMirrorEditors();
                                break;
                            default:
                                $('#central').html(res);
                                break;
                        }
                        $(window).scrollTop(0);
                        centralContainerOffset = $('#central').offset();
                        baseController.updateCommandPanel();
                    }, hash[1]);
        } else {
            window.location.hash = '#!/explore';
        }
    }
};

baseController.showInfoBubble = function(text, left, top) {            
    if (text !== '') {
        if(!infoBubble) {
            infoBubble = $('<a>').attr('id','infoBubble').
            append($('<span>').addClass('caption')).
            append($('<span>').addClass('arrow'));
            infoBubble.hide();
            infoBubble.on('click', function(e){
                infoBubble.hide();
            });
            $('body').append(infoBubble);
        }
        infoBubble.attr('href', '#!/classify?na=' + text);
        infoBubble.find('.caption').text(text);
        infoBubble.css('top', top - infoBubble.outerHeight() - 35);
        infoBubble.css('left', left - infoBubble.outerWidth() / 2);
        infoBubble.show();
    }
};

baseController.hideInfoBubble = function() {
    if (infoBubble) infoBubble.hide();
};

baseController.scrollText = function(container) {
    var $container = $(container);
    $container.css('text-overflow', 'clip');
    var $content = $container.find('span.overflowText');
    var offset = $container.width() - $content.width();
    if (offset < 0) {
        $content.animate({
            'margin-left': offset
        }, {
            duration: offset * -10, 
            easing: 'linear'
        });
    } 
};

baseController.resetScrollText = function(container) {
    var $containter = $(container);
    $containter.css('text-overflow', 'ellipsis');
    var $content = $containter.find('span.overflowText');
    var offset = $containter.width() - $content.width();
    if (offset < 0) {
        $content.clearQueue().stop();
        $content.css('margin-left', 0);
    }
};

baseController.signIn = function() {
    baseController.ajaxRequest('/post/signIn', function(response) {
        if ($(response).find('success').text() === 'true') {
            window.location.href = appContext + '/#!/manageProjects';
        }
    }, $('#siForm').serialize());
};
    
baseController.signOut = function() {
    baseController.ajaxRequest('/post/signOut', function(response) {
        if ($(response).find('success').text() === 'true') {
            window.location.href = appContext + '/signIn';
        }
    });
};

baseController.updateCommandPanel = function() {
    if (centralContainerOffset && $(window).scrollTop() > centralContainerOffset.top) {
        $('#commandPanel').addClass('fixed');
    } else {
        $('#commandPanel').removeClass('fixed');
    }
};