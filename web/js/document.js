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

var infoBubble;
$(document).ready(function(){
    $(document).on('mouseup', function(e) {
        if ($('#dcDocumentContainer').length > 0) {               
            selectedText = new String(getSelectedText()).replace(/^\s+|\s+$/g,'').substr(0,255);
            
            if (selectedText != '') {
                $('#dcSymbolName').val(selectedText);
                
                if(!infoBubble) {
                    infoBubble = $('<a>').attr({
                        contenteditable: 'false',
                        href: 'javascript:;',
                        id: 'infoBubble'
                    });
                    infoBubbleText = $('<span>').attr({id: 'infoBubbleText'});
                    infoBubbleArrow = $('<div>').attr({id: 'infoBubbleArrow'});
                    infoBubble.append(infoBubbleText);
                    infoBubble.append(infoBubbleArrow);
                    infoBubble.hide();
                    infoBubble.on('click', function(e){
                        infoBubble.hide();
                    });
                    $('body').append(infoBubble);
                }
                
                infoBubble.attr('href', '#!/classify?dc=' + $('#dcDocument').val() + '&sn=' + selectedText);
                infoBubbleText.html(selectedText);

                cont = $('#dcDocumentContainer');
                container = {top: cont.offset().top, bottom: cont.offset().top + cont.outerHeight(),
                            left: cont.offset().left, right: cont.offset().left + cont.outerWidth()};                
                newPos = {x: 0, y: 0};
                newPos.y = e.pageY < container.top ? container.top + 10 : e.pageY > container.bottom ? container.bottom - 10 : e.pageY;
                newPos.x = e.pageX < container.left ? container.left + 30 : e.pageX > container.right ? container.right - 30 : e.pageX;

                infoBubble.clearQueue().stop().css({
                    top: newPos.y - infoBubble.outerHeight() - 35,
                    left: newPos.x - infoBubble.outerWidth() / 2,
                    opacity: 1
                }).show();
            }
        }
    });
    
    $(document).on('mouseup', '#infoBubble', function(e) {
        e.stopPropagation();
    });
    
    $(document).on('mousedown', '#infoBubble', function(e) {
        e.stopPropagation();
    });
    
    $(document).on('mousedown', function (e) {
        if (infoBubble) infoBubble.hide();
    });
});

function updateDocumentContent() {
    containerText = $('#dcDocumentContainer').html();
    
    // Internet Explorer
    containerText = containerText.replace(/<p>/i, '');
    containerText = containerText.replace(/<p>&nbsp;<\/p>/ig,'\n');
    containerText = containerText.replace(/<p>/ig,'\n');
    
    // Chrome
    containerText = containerText.replace(/<div><br><\/div>/ig,'\n');
    containerText = containerText.replace(/<div>/ig,'\n');
    
    // Firefox
    containerText = containerText.replace(/<br>/ig, '\n');
    
    containerText = containerText.replace(/<[^>]+>/ig,'');
    containerText = containerText.replace(/&nbsp;/ig,' ');
    
    $('#dcDocumentContent').val(containerText);
    return false;
}
