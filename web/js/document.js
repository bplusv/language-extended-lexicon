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
var infoBubbleText;
var infoBubbleArrow;
var dataInput = $('#data');
var docContainer = $('#documentContainer');
    
$(document).ready(function(){
    dataInput = $('#data');
    docContainer = $('#documentContainer');
    contOffset = docContainer.offset();
    contSize = {width: docContainer.outerWidth(), height: docContainer.outerHeight()};
    container = {top: contOffset.top, bottom: contOffset.top + contSize.height,
                  left: contOffset.left, right: contOffset.left + contSize.width};
    
        
    $(document).on('mouseup', function(e) {
        text = getSelectedText();
        text = new String(text).replace(/^\s+|\s+$/g,'') // remove spaces before & after
        if (text != false && text != '') {
            text = text.substr(0,255);
            $('#name').val(text);
            
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
                    $('#conceptForm').submit();
                });
                
                $('body').append(infoBubble);
            }
            
            infoBubbleText.html(text);
            newPos = {x: 0, y: 0};
            
            newPos.y = e.pageY < container.top ? container.top + 10 : e.pageY > container.bottom ? container.bottom - 10 : e.pageY;
            newPos.x = e.pageX < container.left ? container.left + 30 : e.pageX > container.right ? container.right - 30 : e.pageX;
            
            infoBubble.clearQueue().stop().css({
                top: newPos.y - infoBubble.outerHeight() - 35,
                left: newPos.x - infoBubble.outerWidth() / 2,
                opacity: 1
            }).fadeIn(0);
        }
    });
    
    $(document).on('mouseup', '#infoBubble', function(e) {
        e.stopPropagation();
    });
    
    $(document).on('mousedown', '#infoBubble', function(e) {
        e.stopPropagation();
    });
    
    $(document).on('mousedown', function (e) {
        if (infoBubble) infoBubble.fadeOut();
    });
    
});

function updateDocumentData() {
    containerData = docContainer.html();
    
    // internet explorer
    containerData = containerData.replace(/<p>/i, '');
    containerData = containerData.replace(/<p>&nbsp;<\/p>/ig,'\n');
    containerData = containerData.replace(/<p>/ig,'\n');
    
    // chrome
    containerData = containerData.replace(/<div><br><\/div>/ig,'\n');
    containerData = containerData.replace(/<div>/ig,'\n');
    
    // firefox
    containerData = containerData.replace(/<br>/ig, '\n');
    
    containerData = containerData.replace(/<[^>]+>/ig,'');
    containerData = containerData.replace(/&nbsp;/ig,' ');
    dataInput.val(containerData);

    return true;
}
