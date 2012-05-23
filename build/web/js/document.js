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

$(document).ready(function(){
    var infoBubble;
    $('#document').on('mouseup', function(e) {
        text = getSelectedText();
        text = new String(text).replace(/^\s+|\s+$/g,'') // remove spaces before & after
        if (text != '') {
            text = text.substr(0,255);
            $('#name').val(text);
            
            if(!infoBubble) {
                infoBubble = $('<a>').attr({
                    contenteditable: 'false',
                    href: '#',
                    id: 'infoBubble'
                });
                infoBubbleText = $('<span>').attr({ id: 'infoBubbleText' });
                infoBubbleArrow = $('<div>').attr({ id: 'infoBubbleArrow' });
                
                infoBubble.append(infoBubbleText);
                infoBubble.append(infoBubbleArrow);
                infoBubble.hide();
                
                infoBubble.on('mousedown', function(e){
                    $('#conceptForm').submit();
                });
                
                $('#document').append(infoBubble);
            }
            
            $('#infoBubbleText').html(text);
            infoBubble.show().css({
                top: e.pageY - infoBubble.outerHeight() - 30,
                left: e.pageX - infoBubble.outerWidth() / 2
            });
        }
    });
    
    $(document).on('mousedown', function (e) {
        if (infoBubble) infoBubble.fadeOut();
    });
});
