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

var appContext = '/lel';
var bubble;
var projectSymbols = {};
var myCode;

function popBubble(text, left, top) {
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
        bubbleText.html(text);
        bubble.css('top', top - bubble.outerHeight() - 35);
        bubble.css('left', left - bubble.outerWidth() / 2);
        bubble.show();
    }
}

function pushBubble() {
     if (bubble) bubble.hide();
}

function notify(cssClass, message) {
    $notification = $('#notification');
    $notification
        .removeClass()
        .addClass(cssClass)
        .stop(true, true)
        .show()
        .html(message)
        .animate({opacity: 1}, 3000)
        .fadeOut(500);
}

function updateLelMode() {
    CodeMirror.defineMode("lel", function() {
        function wordRegexp(words) {
            return new RegExp("^((" + words.join(")|(") + "))", "");
        }
        var words = [];
        for (i in projectSymbols)
            words.push(i);
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

function updateSymbolicEditors() {
    updateLelMode();
    $('textarea.symbolicEditor').each(function(i,e) {
        var cm = $(e).data('codeMirror');
        if (cm != undefined) {
            cm.setOption('mode', 'text/x-lel');
        } else {
            w = $(e).outerWidth();
            h = $(e).outerHeight();
            var editor = CodeMirror.fromTextArea(e,
            {'onUpdate': tagSymbolicEditorSymbols,
                'onScroll': tagSymbolicEditorSymbols,
                'mode': 'text/x-lel', 'lineWrapping': true});
            $(e).data('codeMirror', editor);
            editor.setSize(w, h);
            editor.refresh();
        }
    });
    tagSymbolicEditorSymbols();
}

function tagSymbolicEditorSymbols() {
    $cms = $('div.CodeMirror');
    $cms.each(function(i, cm) {
        $cm = $(cm);
        cmTop = $cm.offset().top;
        cmBottom = $cm.offset().top + $cm.outerHeight();
        cmLeft = $cm.offset().left;
        cmRight = $cm.offset().left + $cm.outerWidth();
        $symbols = $cm.find('div.CodeMirror-lines span[class^=cm-symbol-]').
            filter(function() {
                curOffset = $(this).offset();
                curTop = curOffset.top;
                curLeft = curOffset.left;
                return curTop > cmTop && curTop < cmBottom &&
                    curLeft > cmLeft && curLeft < cmRight;
        });
        $symbols.each(function(i, e) {
            $(e).wrapInner('<a class="symbol" href="#!/classify?sy='+
                /cm-symbol-(.*)/.exec($(e).attr('class'))[1] + '"></a>');
        });
    });
}

function tagSymbols(text) {
//    for (symbol in projectSymbols) {
//        text = text.replace(new RegExp(symbol, 'g'), 
//            $('<a>').addClass('symbol').attr('href', '#!classify?sy=' + 
//                projectSymbols[symbol]).text(symbol)[0].outerHTML
//        );
//    }
    return text;
}

function update(response, redirect) {
    if (redirect) {
        if (document.location.hash.indexOf(redirect) > -1)
            $(window).trigger('hashchange');
        else
            document.location.hash = redirect;
    }
    
    $('.overflowEllipsis').each(function(i,e) {
        if ($(e).children('.overflowText').length < 1)
            $(e).wrapInner('<span class="overflowText">');
    });

    $('.tabs a').removeClass('selected');
    if (document.location.hash.indexOf('/explore') > 0)
        $('#exploreTab').addClass('selected');
    else if (document.location.hash.indexOf('/document') > 0)
        $('#documentTab').addClass('selected'); 

    
    $('#ajaxLoader').hide();
    if (response) {
        success = $(response).find('success').text();
        message = $(response).find('message').text();
        if (message) notify(success === 'true' ? 'success' : 'fail', message);
    }
}

$(document).ready(function() {
    $(document).on('mouseenter', '.overflowEllipsis', function(e) {
        $this = $(this);
        $this.css('text-overflow', 'clip');
        $that = $this.find('span.overflowText');
        offset = $this.width() - $that.width();
        if (offset < 0) {
            $that.animate({'margin-left': offset}, 
            {duration: offset * -10, easing: 'linear'});
        } 
    });
    $(document).on('mouseleave', '.overflowEllipsis', function(e) {
        $this = $(this);
        $this.css('text-overflow', 'ellipsis');
        $that = $this.find('span.overflowText');
        offset = $this.width() - $that.width();
        if (offset < 0) {
            $that.clearQueue().stop();
            $that.css('margin-left', 0);
        }
    });
    $(document).on('dblclick', 'option', function() {
        $(this.form).submit();
    });
});