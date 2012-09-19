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

window.controller = (function($, CodeMirror) {
    'use strict';
    var bubble, bubbleText, bubbleArrow;
    var appContext = '/lel';
    var projectSymbols = {};
    var symbolsRegex;
    var api = {};
    
    var ajaxRequest = function(request, callback, params, async) {
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
                    if ($(data).find('sessionTimeOut').text() === 'true') {
                        window.location.href = appContext + '/signIn';
                        return;
                    }
                    response = $(data);
                    redirect = callback && callback(response);
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    isRequesting = false;
                    response = $('<response>').append(
                        $('<message>').text(
                        $('#messages .ixNetworkFail').text()));
                },
                complete: function() {
                    isRequesting = false;
                    update(response, redirect);
                },
                async: async === false ? false : true
            });
        }
    }
    
    var updateProjectSymbols = function() {
        ajaxRequest('/get/data/projectSymbols', function(response) {
            projectSymbols = {};
            var $xmlSymbols = $(response).find('symbols').children();
            $xmlSymbols.each(function(i, e) {
                projectSymbols[$(e).children('name').text()] = $(e).attr('id');
            });
            updateSymbolsRegex();
        }, null, false);
    };
    
    var notify = function(cssClass, message) {
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
    }

    var regexEscape = function (text) {
        return text.replace(/[-[\]{}()*+?.,\\^$|#\s]/g, "\\$&");
    }

    var updateSymbolsRegex = function() {
        var words = [];
            for (var key in projectSymbols) {
                if (projectSymbols.hasOwnProperty(key)) {
                    words.push(key);
                }
            }
            words = words.sort().reverse().map(regexEscape);
        symbolsRegex = RegExp("^((" + words.join(")|(") + "))", "");
    }
    
    var updateLelMode = function() {
        CodeMirror.defineMode("lel", function() {
            function tokenLexer(stream) {
                if (stream.eatSpace()) return null;
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
    }

    var tagCodeMirrorSymbols = function() {
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
                $(e).wrap('<a class="symbol" href="#!/classify?sy='+
                    /cm-symbol-(.*)/.exec($(e).attr('class'))[1] + '"></a>');
                $(e).attr('class', 'symbol');
            });
        });
    }
    
    var tagSymbols = function(text) {
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
    }
    
    var update = function(response, redirect) {
        if (redirect) {
            if (window.location.hash.indexOf(redirect) > -1) {
                $(window).trigger('hashchange');
            } else {
                window.location.hash = redirect;
            }
        }
        $('.overflowEllipsis').each(function(i,e) {
            if ($(e).children('.overflowText').length < 1) {
                $(e).wrapInner('<span class="overflowText">');
            }
        });
        $('.tabs a').removeClass('selected');
        if (window.location.hash.indexOf('/explore') > 0) {
            $('#exploreTab').addClass('selected');
        } else if (window.location.hash.indexOf('/document') > 0) {
            $('#documentTab').addClass('selected');
        }
        $('#ajaxLoader').hide();
        if (response) {
            var success = $(response).find('success').text();
            var message = $(response).find('message').text();
            if (message) notify(success === 'true' ? 'success' : 'fail', message);
        }
    };
    
    var updateCodeMirrorEditors = function() {
        updateLelMode();
        $('textarea.symbolicEditor').each(function(i,e) {
            var cm = $(e).data('codeMirror');
            if (cm != undefined) {
                cm.setOption('mode', 'text/x-lel');
            } else {
                var w = $(e).outerWidth();
                var h = $(e).outerHeight();
                var editor = CodeMirror.fromTextArea(e,
                {
                    'onUpdate': tagCodeMirrorSymbols,
                    'onScroll': tagCodeMirrorSymbols,
                    'mode': 'text/x-lel', 
                    'lineWrapping': true
                });
                $(e).data('codeMirror', editor);
                editor.setSize(w, h);
                editor.refresh();
            }
        });
        tagCodeMirrorSymbols();
    };
    
    api.signIn = function() {
        ajaxRequest('/post/signIn', function(response) {
            if ($(response).find('success').text() === 'true') {
                window.location.href = appContext + '#!/explore';
            }
        }, $('#siForm').serialize());
    };
    
    api.signOut = function() {
        ajaxRequest('/post/signOut', function(response) {
            if ($(response).find('success').text() === 'true') {
                window.location.href = appContext + '/signIn';
            }
        });
    };
    
    api.changeLanguage = function(lang) {
        ajaxRequest('/post/chooseLanguage', function(response) {
            var redirect;
            if ($(response).find('success').text() === 'true') {
                redirect =  window.location.reload();
            }
            return redirect;
        }, 'language=' + lang);
    };
    
    api.loadDocument = {};
    api.loadDocument.load = function() {
        ajaxRequest('/post/loadDocument', function(response) {
            var redirect;
            if ($(response).find('success').text() === 'true') {
                redirect = '#!/document';
            }
            return redirect;
        }, $('#ldLoadForm').serialize());
    };
    api.loadDocument.createDoc = function() {
        ajaxRequest('/post/createDocument', function(response) {
            var redirect;
            if ($(response).find('success').text() === 'true') {
                redirect = '#!/document';
            }
            return redirect;
        }, $('#ldCreateForm').serialize());
    };
    
    api.loadProject = {};
    api.loadProject.load = function() {
        ajaxRequest('/post/loadProject', function(response) {
            var redirect;
            if ($(response).find('success').text() === 'true') {
                var projectName = $(response).find('project').find('name').text();
                $('#ixProjectTitle').show();
                $('#ixProjectName').text(projectName);
                redirect = '#!/explore';
            }
            return redirect;
        }, $('#lpLoadForm').serialize());
    };
    api.loadProject.createProj = function() {
        ajaxRequest('/post/createProject', function(response) {
            var redirect;
            if ($(response).find('success').text() === 'true') {
                var projectName = $(response).find('project').find('name').text();
                $('#ixProjectTitle').show();
                $('#ixProjectName').text(projectName);
                redirect = '#!/explore';
            }
            return redirect;
        }, $('#lpCreateForm').serialize());
    };
    
    api.classify = {};
    api.classify.updateFields = function() {
        // is 'general' category selected?
        if ($('#clCategory').val() === '1') {
            $('#clClassificationField').hide();
            $('#clIntentionFields').hide();
        } else {
            $('#clClassificationField').show();
            $('#clIntentionFields').show();
        }
    };

    api.classify.updateSymbol = function() {
        ajaxRequest('/post/updateSymbol', function(response) {
            if ($(response).find('success').text() === 'true') {
                $('#clCancelGroup').css('display', 'none');
                $('#clSaveGroup').css('display', 'none');
                $('#clChangeGroup').css('display', 'inline');
                $('#clSynonymsSelect').val(-1);
                $('#clSynonymsSelect').css('display', 'none');
                var synonyms = $(response).find('synonymsGroup').children();
                $('#clLeaveGroup').css('display', synonyms.length > 0 ? 'inline' : 'none');
                $('#clLogUserName').text($(response).find('log > user > name').text());
                $('#clLogDate').text($(response).find('log > date').text());
                $('#clNewComment').data('codeMirror').setValue('');
                api.classify.updateComments(response);
            }
        }, $('#clForm').serialize());
    };
    
    api.classify.leaveSynonymsGroup = function(params) {
        ajaxRequest('/post/leaveSynonymsGroup', function(response) {
            if ($(response).find('success').text() === 'true') {
                $('#clLeaveGroup').css('display', 'none');
                $('#clSynonymsGroup').html('');
                $('#clLogUserName').text($(response).find('log > user > name').text());
                $('#clLogDate').text($(response).find('log > date').text());
                $('#clNotion').data('codeMirror').setValue('');
                $('#clActualIntention').data('codeMirror').setValue('');
                $('#clFutureIntention').data('codeMirror').setValue('');
                $('#clNewComment').data('codeMirror').setValue('');
                api.classify.updateComments(response);
            }
        }, params);
    };
    
    api.classify.cancelSelectSynonym = function() {
        $('#clSynonymsSelect').val(-1);
        $('#clSynonymsSelect').css('display', 'none');
        $('#clCancelGroup').css('display', 'none');
        $('#clSaveGroup').css('display', 'none');
        $('#clChangeGroup').css('display', 'inline');
        api.classify.selectSynonym($('#clSymbol').val());
    }
    
    api.classify.createSymbol = function() {
        ajaxRequest('/post/createSymbol', function(response) {
            if ($(response).find('success').text() === 'true') {
                $('#clCancelGroup').css('display', 'none');
                $('#clSaveGroup').css('display', 'none');
                $('#clChangeGroup').css('display', 'inline');
                $('#clSynonymsSelect').val(-1);
                $('#clSynonymsSelect').css('display', 'none');
                $('#clSymbol').val($(response).find('symbol').attr('id'));
                $('#clForm').attr('action', '/post/updateSymbol');
                $('#clDefinitionTopRight').css('visibility', 'visible');
                $('#clLogUserName').text($(response).find('log > user > name').text());
                $('#clLogDate').text($(response).find('log > date').text());
                $('#clNewComment').data('codeMirror').setValue('');
                updateProjectSymbols();
                updateCodeMirrorEditors();
                api.classify.updateComments(response);
            }
        }, $('#clForm').serialize());
    };

    api.classify.selectSynonym = function(id) {
        ajaxRequest('/get/data/classifySelectSynonym', function(response) {
            var $xmlSynonyms = $(response).find('synonymsGroup').children();
            var synonyms = [];
            $xmlSynonyms.each(function(i, e) {
                if ($(e).attr('id') != $('#clSymbol').val())
                    synonyms.push($('<a>').attr('href','#!/classify?sy='+ 
                        $(e).attr('id')).text($(e).children('name').text())[0].outerHTML);
            });
            $('#clSynonymsGroup').html(synonyms.join(', '));
            if ($(response).find('symbol').attr('id') === $('#clSymbol').val()) {
                $('#clLeaveGroup').css('display', synonyms.length > 0 ? 'inline' : 'none');
            }
            $('#clDocumentTitle').text($(response).find('document > name').text() || $('#clDocumentName').val());
            $('#clCategory').val($(response).find('category').text());
            $('#clCategory').trigger('change');
            $('#clClassification').val($(response).find('classification').text());
            $('#clLogUserName').text($(response).find('log > user > name').text());
            $('#clLogDate').text($(response).find('log > date').text());
            $('#clNotion').data('codeMirror').setValue($(response).find('notion').text());
            $('#clActualIntention').data('codeMirror').setValue($(response).find('actualIntention').text());
            $('#clFutureIntention').data('codeMirror').setValue($(response).find('futureIntention').text());
            api.classify.updateComments(response);
        }, 'symbol=' + id);
    };

    api.classify.showSynonyms = function() {
        $(window).scrollTop($('#clTitle').offset().top);
        $('#clSynonymsSelect').css('display', 'block');
        $('#clLeaveGroup').css('display', 'none');
        $('#clChangeGroup').css('display', 'none');
        $('#clCancelGroup').css('display', 'inline');
        $('#clSaveGroup').css('display', 'inline');
        ajaxRequest('/get/data/classifyShowSynonyms', function(response) {
            var $xmlSynonyms = $(response).find('synonyms').children();
            var $sel = $('#clSynonymsSelect');
            var syId = $('#clSymbol').val();
            $sel.empty();
            $xmlSynonyms.each(function(i, e) {
                if ($(e).attr('id') != syId) {
                    $sel.append($('<option>').attr('value', 
                        $(e).attr('id')).text($(e).children('name').text()));
                }
            });
        });
    };
    api.classify.updateComments = function(response) {
        var $clComments = $('#clComments');
        $clComments.children().remove();
        $(response).find('comments').children().each(function(i,e) {
            $('<li>').css('background', i % 2 == 0 ? '#fff' : '#f9f9f9')
                .append($('<div>').addClass('left')
                    .append($('<span>').addClass('overflowEllipsis')
                        .text($(e).find('user > name').text()+':'))
                    .append($('<span>').text($(e).find('date').text()))
                ).append($('<div>').addClass('right').html(
                    tagSymbols($(e).find('content').text()))
                ).append($('<div>').css('clear', 'both'))
            .appendTo($clComments);
        });
        $clComments.scrollTop(0);
    };
    
    api.document = {}
    api.document.updateDoc = function() {
        ajaxRequest('/post/updateDocument', null, $('#dcUpdateForm').serialize());
    };
    
    api.explore = {};
    api.explore.removeSymbol = function(id) {
        ajaxRequest('/post/removeSymbol', function(response) {
            var redirect;
            if ($(response).find('success').text() === 'true') {
                redirect = '#!/explore';
            }
            return redirect;
        }, 'symbol=' + id);
    };
    api.explore.clearSearch = function() {
        $('#exSearch').val('');
        $('#exForm').submit();
    };
    api.explore.search = function(response){
        ajaxRequest('/get/data/exploreSymbols', function(response) {
            var $xmlSymbols = $(response).find('symbols').children();
            var $tbody = $('#exSymbolsTable tbody');
            $tbody.empty();
            $xmlSymbols.each(function(i, e) {
                $('<tr>').wrapInner($('<td>').attr('colspan', '5')
                    .css('background', i % 2 == 0 ? '#fff' : '#f9f9f9').wrapInner(
                        $('<a>').addClass('exSymbolsRow').attr('href', '#!/classify?sy='+$(e).attr('id'))
                        .append($('<span>').addClass('overflowEllipsis exSyName').text($(e).children('name').text()))
                        .append($('<span>').addClass('overflowEllipsis').text($(e).find('category > name').text()))
                        .append($('<span>').addClass('overflowEllipsis').text($(e).find('classification > name').text()))
                        .append($('<span>').addClass('overflowEllipsis').text($(e).find('document > name').text()))
                        .append($('<span>').attr('id', 'exSy' + $(e).attr('id')).addClass('removeSymbol').html('&#215;'))
                        )).appendTo($tbody);
            });
            $('#exSearchClear').css('visibility', $('#exSearch').val() ? 'visible' : 'hidden');
        }, $('#exForm').serialize());
    };
    
    api.changeView = function(hash) {
        if (window.location.href.indexOf('/signIn') < 0) {
            if (hash){
                hash = hash.replace(/#!/, '');
                hash = hash.split('?');
                ajaxRequest('/get/view' + hash[0], 
                function(res) {
                    $('#central').html(res);
                    switch(hash[0]) {
                        case '/classify':
                            updateProjectSymbols();
                            updateCodeMirrorEditors();
                            api.classify.updateFields();
                        break;
                        case '/document':
                            updateProjectSymbols();
                            updateCodeMirrorEditors();
                        break;
                    }
                }, hash[1]);
            }  else {
                window.location.hash = '#!/explore';
            }
        }
    };

    api.popBubble = function(text, left, top) {
        if (text != '') {
            if(!bubble) {
                bubble = $('<a>').attr('id','infoBubble');
                bubbleText = $('<span>').attr('id', 'infoBubbleText');
                bubbleArrow = $('<div>').attr('id', 'infoBubbleArrow');
                bubble.append(bubbleText);
                bubble.append(bubbleArrow);
                bubble.hide();
                bubble.on('click', function(e){
                    bubble.hide();
                });
                $('body').append(bubble);
            }

            bubble.attr('href', '#!/classify?dc=' + 
                $('#dcDocument').val() + '&na=' + text);
            bubbleText.text(text);
            bubble.css('top', top - bubble.outerHeight() - 35);
            bubble.css('left', left - bubble.outerWidth() / 2);
            bubble.show();
        }
    };

    api.pushBubble = function() {
        if (bubble) bubble.hide();
    };
    
    return api;
})(jQuery, CodeMirror);

(function() {
    $(function() {
        $(window).on('dblclick', 'select', function(e) {
            $(this.form).submit();
        });
        
        $(window).on('hashchange', function(){
            controller.changeView(window.location.hash);
        });
        $(window).trigger('hashchange');
    });
})();