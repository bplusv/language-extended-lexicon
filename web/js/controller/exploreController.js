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


exploreController = {};

exploreController.clearSearch = function() {
    
    $('#exSearch').val('');
    $('#exForm').submit();
    
};

exploreController.confirmRemoveSymbol = function(targetSymbol) {
    
    var id = $(targetSymbol).data('symbol.id');
    var symbolName = $(targetSymbol).data('symbol.name');
    var title = $('#messages .removeSymbolConfirmationTitle').text();
    var message = $('#messages .removeSymbolConfirmation').text();
    var deleteMsg = $('#messages .remove').text();
    var cancelMsg = $('#messages .cancel').text();
    $.confirm({
        'title'	: title,
        'message'	: $('<div>')
                        .append($('<span>').addClass('itemName')
                            .addClass('overflowEllipsis').text(symbolName))
                        .append($('<span>').text(message)).html(),
        'buttons'	: {
            'delete'	: {
                'msg'   : deleteMsg,
                'class'	: 'red',
                'action': function(){
                    exploreController.removeSymbol(id);
                }
            },
            'cancel'    : {
                'msg'   : cancelMsg,
                'class'	: 'blue',
                'action': function(){}
            }
        }
    });
    baseController.updateMainInterface();
    
};

exploreController.removeSymbol = function(id) {
    
    baseController.ajaxRequest('/post/removeSymbol', function(response) {
        var redirect;
        if ($(response).find('success').text() === 'true') {
            redirect = '#!/explore';
        }
        return redirect;
    }, 'symbol=' + id);
    
};

exploreController.search = function(response){
    
    baseController.ajaxRequest('/get/data/exploreSymbols', function(response) {
        var $xmlSymbols = $(response).find('symbols').children();
        var $symbols = [];
        $xmlSymbols.each(function(i, e) {
            $symbols.push($('<li>').addClass(i % 2 === 0 ? 'rowEven' : 'rowOdd')
                .append($('<a>').addClass('exSymbol').attr('href', '#!/classify?sy='+$(e).attr('id'))
                    .append($('<span>').addClass('overflowEllipsis exSyName').text($(e).children('name').text()))
                    .append($('<span>').addClass('overflowEllipsis').text($(e).find('category > name').text()))
                    .append($('<span>').addClass('overflowEllipsis').text($(e).find('classification > name').text()))
                    .append($('<span>').addClass('overflowEllipsis').text($(e).find('document > name').text()))
                    .append($('<span>').addClass('removeSymbol').data('symbol.id', $(e).attr('id'))
                        .data('symbol.name', $(e).children('name').text()).html('&#215;'))).get(0));
        });
        $('#exSymbolsList').html($symbols);
        $('#exSearchClear').css('visibility', $('#exSearch').val() ? 'visible' : 'hidden');
    }, $('#exForm').serialize());
    
};