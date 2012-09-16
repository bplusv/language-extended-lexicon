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

window.lel = (function($, CodeMirror) {
    "use strict";
    var bubble;
    var bubbleText;
    var bubbleArrow;
    var api = {};
    var projectSymbols = {};
    
    function notify(cssClass, message) {
        var $notification = $('#notification');
        $notification
            .removeClass()
            .addClass(cssClass)
            .stop(true, true)
            .show()
            .html(message)
            .animate({opacity: 1}, 3000)
            .fadeOut(500);
    }

    var regexEscape = function (text) {
        return text.replace(/[-[\]{}()*+?.,\\^$|#\s]/g, "\\$&");
    }

    var updateLelMode = function() {
        CodeMirror.defineMode("lel", function() {
            function wordRegexp(words) {
                return new RegExp("^((" + words.join(")|(") + "))", "");
            }
            var words = [];
            for (var i in projectSymbols)
                words.push(regexEscape(i));
            var keywords = wordRegexp(words);
            function tokenLexer(stream) {
                if (stream.eatSpace()) return null;
                var match = stream.match(keywords);
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

    var tagEditorSymbols = function() {
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
                $(e).wrapInner('<a class="symbol" href="#!/classify?sy='+
                    /cm-symbol-(.*)/.exec($(e).attr('class'))[1] + '"></a>');
            });
        });
    }
    
    api.tagSymbols = function(text) {
        text = $('<span>').text(text).html();
        var symbolName = '';
        for (var symbol in projectSymbols) {
            symbolName = $('<span>').text(symbol).html();
            text = text.replace(new RegExp(regexEscape(symbolName), 'g'), 
                $('<a>').addClass('symbol').attr('href', '#!classify?sy=' + 
                    projectSymbols[symbol]).text(symbol)[0].outerHTML
            );
        }
        return text;
    }

    api.getProjectSymbols = function() {
        return projectSymbols;
    };
    api.setProjectSymbols = function(symbols){
        projectSymbols = symbols;
    };
    api.update = function(response, redirect) {
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
    api.updateSymbolicEditors = function() {
        updateLelMode();
        $('textarea.symbolicEditor').each(function(i,e) {
            var cm = $(e).data('codeMirror');
            if (cm != undefined) {
                cm.setOption('mode', 'text/x-lel');
            } else {
                var w = $(e).outerWidth();
                var h = $(e).outerHeight();
                var editor = CodeMirror.fromTextArea(e,
                {'onUpdate': tagEditorSymbols,
                    'onScroll': tagEditorSymbols,
                    'mode': 'text/x-lel', 'lineWrapping': true});
                $(e).data('codeMirror', editor);
                editor.setSize(w, h);
                editor.refresh();
            }
        });
        tagEditorSymbols();
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

$(function() {
    $(window).on('mouseenter', '.overflowEllipsis', function(e) {
        var $this = $(this);
        $this.css('text-overflow', 'clip');
        var $that = $this.find('span.overflowText');
        var offset = $this.width() - $that.width();
        if (offset < 0) {
            $that.animate({'margin-left': offset}, 
            {duration: offset * -10, easing: 'linear'});
        } 
    });
    $(window).on('mouseleave', '.overflowEllipsis', function(e) {
        var $this = $(this);
        $this.css('text-overflow', 'ellipsis');
        var $that = $this.find('span.overflowText');
        var offset = $this.width() - $that.width();
        if (offset < 0) {
            $that.clearQueue().stop();
            $that.css('margin-left', 0);
        }
    });
    $(window).on('dblclick', 'option', function() {
        $(this.form).submit();
    });
});