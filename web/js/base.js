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

function notify(cssClass, message) {
    $notification = $('#notification');
    $notification
        .removeClass()
        .addClass(cssClass)
        .stop(true, true)
        .show()
        .html(message)
        .animate({opacity: 1},3000)
        .fadeOut(500);
}

function update(response, redirect) {
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
    
    if (redirect) {
        if (document.location.hash.indexOf(redirect) > -1)
            $(window).trigger('hashchange');
        else
            document.location.hash = redirect;
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
});