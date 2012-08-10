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

$(document).ready(function() {
    $(document).on('keyup', '#exSearch', function() {
        $('#exForm').submit();
    });
    
    $(document).on('click', '#exSearchClear', function() {
        $('#exSearch').val('');
        $('#exForm').submit();
    });
    
    $(document).on('click', '.exSymbolsRow span.removeSymbol', function(e) {
        e.preventDefault();
        var id = /exSy(.*)/.exec($(this).attr('id'))[1];
        var $symbolName = $(this).siblings('.exSyName').clone();
        var title = $('#messages .ixDeleteConfirmationTitle').html();
        var message = $('#messages .ixDeleteConfirmation').html();
        var deleteMsg = $('#messages .ixDelete').html();
        var cancelMsg = $('#messages .ixCancel').html();
        $.confirm({
            'title'		: title,
            'message'	: $symbolName[0].outerHTML + message,
            'buttons'	: {
                'delete'	: {
                    'msg'   : deleteMsg,
                    'class'	: 'red',
                    'action': function(){
                        controller('/post/removeSymbol', 'symbol=' + id);
                    }
                },
                'cancel'    : {
                    'msg'   : cancelMsg,
                    'class'	: 'blue',
                    'action': function(){}
                }
            }
        });
    });
});