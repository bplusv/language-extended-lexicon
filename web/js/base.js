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
var projectSymbols = [];
var myCode;

function tagSymbols(content) {
    $(projectSymbols).each(function(i,e) {
        var patt = new RegExp(e.name, 'g');
        content = content.replace(patt, 
            '<a href="#!/classify?sy=' + e.id + '" ' + 
            'contenteditable="false">' + e.name + '</a>');
    });
    return content;
}

function getSelectedText() {
    if(window.getSelection) {return window.getSelection();}
    else if(document.getSelection) {return document.getSelection();}
    else {
        var selection = document.selection && document.selection.createRange();
        if(selection.text) {return selection.text;}
        return '';
    }
    return '';
}

function popBubble(text, left, top) {
    if (text != '') {
        if(!bubble) {
            bubble = $('<a>').attr({
                contenteditable: 'false',
                href: 'javascript:;',
                id: 'infoBubble'
            });
            bubbleText = $('<span>').attr({id: 'infoBubbleText'});
            bubbleArrow = $('<div>').attr({id: 'infoBubbleArrow'});
            bubble.append(bubbleText);
            bubble.append(bubbleArrow);
            bubble.hide();
            bubble.on('click', function(e){
                bubble.hide();
            });
            $('body').append(bubble);
        }

        bubble.attr('href', '#!/classify?dc=' + $('#dcDocument').val() + '&na=' + text);
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

function updateSymbolicEditors() {
    isRequesting = false;
    controller('/get/data/projectSymbols', '', false);
    $('textarea.symbolicEditor').each(function(i,e) {
        if ($(e).css('display') != 'none') {
            w = $(e).outerWidth();
            h = $(e).outerHeight();
            var editor = CodeMirror.fromTextArea(e,
            {'onChange': tagEditorSymbols, 'mode': 'text/plain'});
            $(editor.getWrapperElement()).attr('id', 
                'cm-' + $(e).attr('id')).data('instance', editor);
            editor.setSize(w, h);
            editor.refresh();
        }
    });
    tagEditorSymbols();
}

function tagEditorSymbols() {
    $('.editorSymbol').remove();
    $('.CodeMirror').each(function(i,e) {
        var editor = $(e).data('instance');
        for (i in projectSymbols) {
            var line = 0, ch = 0;
            for (line = 0; line < editor.lineCount(); line++) {
                ch = editor.getLine(line).indexOf(projectSymbols[i].name);
                while (ch > -1) {
                    widget = $('<a>').addClass('editorSymbol').
                        attr('href', '#!/classify?sy=' + projectSymbols[i].id)
                        .html(projectSymbols[i].name.replace(/ /g, '&nbsp;'))[0];
                    pos = {'ch': ch, 'line': line};
                    editor.addWidgetTop(pos, widget);
                    ch = editor.getLine(line).indexOf(projectSymbols[i].name, ch + 1);
                }
            }
        }
    });
}

function tagSymbols(text) {
    for (i in projectSymbols) {
        text = text.replace(new RegExp(projectSymbols[i].name, 'g'), 
            $('<a>').addClass('symbol').attr('href', '#!classify?sy=' + 
                projectSymbols[i].id).html(projectSymbols[i].name)[0].outerHTML
        );
    }
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