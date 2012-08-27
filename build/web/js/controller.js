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

var isRequesting = false;
function controller(request, params, asynchronous) {
    var response, action, redirect;

    switch (request) {
        case '/get/data/classifySelectSynonym':
            action = function() {
                $xmlSynonyms = $(response).find('synonymsGroup').children();
                var synonyms = [];
                $xmlSynonyms.each(function(i, e) {
                    if ($(e).attr('id') != $('#clSymbol').val())
                        synonyms.push($('<a>').attr('href','#!/classify?sy='+ 
                        $(e).attr('id')).html($(e).children('name').text())[0].outerHTML);
                });
                $('#clSynonyms').html(synonyms.join(', '));
                $('#clDocumentTitle').html($(response).find('document > name').text());
                $('#clCategory').val($(response).find('category').text());
                $('#clCategory').trigger('change');
                $('#clClassification').val($(response).find('classification').text());
                $('#clLogUserName').html($(response).find('log > user > name').text());
                $('#clLogDate').html($(response).find('log > date').text());
                $('#cm-clNotion').data('instance').setValue($(response).find('notion').text());
                $('#cm-clActualIntention').data('instance').setValue($(response).find('actualIntention').text());
                $('#cm-clFutureIntention').data('instance').setValue($(response).find('futureIntention').text());
                //$('#cm-clComments').data('instance').setValue($(response).find('comments').text());
            };
            break;
        case '/get/data/classifyShowSynonyms':
            action = function() {
                var $xmlSynonyms = $(response).find('synonyms').children();
                $sel = $('#clSynonymsSelect');
                syId = $('#clSymbol').val();
                $sel.empty();
                $xmlSynonyms.each(function(i, e) {
                    if ($(e).attr('id') != syId) {
                        $sel.append($('<option>').attr('value', 
                        $(e).attr('id')).html($(e).children('name').text()));
                    }
                });
            };
        break;
        case '/get/data/exploreSymbols':
            action = function() {
                var $xmlSymbols = $(response).find('symbols').children();
                $tbody = $('#exSymbolsTable tbody');
                $tbody.empty();
                $xmlSymbols.each(function(i, e) {
                    $('<tr>').wrapInner($('<td>').attr('colspan', '5')
                    .css('background', i % 2 == 0 ? '#fff' : '#f9f9f9').wrapInner(
                        $('<a>').addClass('exSymbolsRow').attr('href', '#!/classify?sy='+$(e).attr('id'))
                        .append($('<span>').addClass('overflowEllipsis exSyName').html($(e).children('name').text()))
                        .append($('<span>').addClass('overflowEllipsis').html($(e).find('category > name').text()))
                        .append($('<span>').addClass('overflowEllipsis').html($(e).find('classification > name').text()))
                        .append($('<span>').addClass('overflowEllipsis').html($(e).find('document > name').text()))
                        .append($('<span>').attr('id', 'exSy' + $(e).attr('id')).addClass('removeSymbol').html('&#215;'))
                    )).appendTo($tbody);
                });
                $('#exSearchClear').css('visibility', $('#exSearch').val() ? 'visible' : 'hidden');
            };
            break;
        case '/get/data/projectSymbols':
            action = function() {
                $xmlSymbols = $(response).find('symbols').children();
                projectSymbols = [];
                $xmlSymbols.each(function(i, e) {
                    projectSymbols.push({'id':$(e).attr('id'), 'name':$(e).children('name').text()});
                });
            }
            break;
        case '/get/view/classify':
        action = function() {
                $('#central').html(response);
                updateSymbolicEditors();
                updateClassifyFields();
            };
            break;
        case '/get/view/document':
        action = function() {
                $('#central').html(response); 
                updateSymbolicEditors();
            };
            break;
        case '/get/view/explore':
        case '/get/view/loadDocument':
        case '/get/view/loadProject':
        case '/get/view/test':
            action = function() {
                $('#central').html(response); 
            };
            break;
        case '/post/chooseLanguage':
            action = function() {
                if ($(response).find('success').text() === 'true') {
                    document.location.reload();
                }
            };
            break;
        case '/post/createDocument':
            action = function() {
                if ($(response).find('success').text() === 'true') {
                    redirect = '#!/document';
                }
            };
            break;
        case '/post/createProject':
            action = function() { 
                if ($(response).find('success').text() == 'true') {
                    projectName = $(response).find('project').find('name').text();
                    $('#ixProjectTitle').show();
                    $('#ixProjectName').html(projectName);
                    redirect = '#!/explore';
                }
            };
            break;
        case '/post/createSymbol':
            action = function() {
                if ($(response).find('success').text() === 'true') {
                    $('#clSymbol').val($(response).find('symbol').attr('id'));
                    $('#clForm').attr('action', appContext + 'do/updateSymbol');
                    $('#clDefinitionTopRight').css('visibility', 'visible');
                    $('#clLogUserName').html($(response).find('log > user > name').text());
                    $('#clLogDate').html($(response).find('log > date').text());
                    $('#cm-clNewComment').data('instance').setValue('');
                    $clComments = $('#clComments');
                    $clComments.empty();
                    $(response).find('comments').children().each(function(i,e) {
                        $clComments.append($('<li>').html($(e).find('content').text() + ' / ' + 
                            $(e).find('user > name').text() + ' / ' + $(e).find('date').text()));
                    });
                    
                }
            };
            break;
        case '/post/loadDocument':
            action = function() {
                if ($(response).find('success').text() === 'true') {
                    redirect = '#!/document';
                }
            };
            break;
        case '/post/loadProject':
            action = function() { 
                if ($(response).find('success').text() === 'true') {
                    projectName = $(response).find('project').find('name').text();
                    $('#ixProjectTitle').show();
                    $('#ixProjectName').html(projectName);
                    controller('/get/data/projectSymbols');
                    redirect = '#!/explore';
                }
            };
            break;
        case '/post/removeSymbol':
            action = function() {
                if ($(response).find('success').text() === 'true') {
                    redirect = '#!/explore';
                }
            }
            break;
        case '/post/signIn':
            action = function() { 
                if ($(response).find('success').text() === 'true') {
                    document.location.href = appContext;
                }
            };
            break;
        case '/post/signOut':
            action = function() { 
                if ($(response).find('success').text() === 'true') {
                    window.location.href = appContext + '/signIn';
                }
            };
            break;
        case '/post/updateDocument':
            action = function() {};
            break;
        case '/post/updateSymbol':
            action = function() {
                if ($(response).find('success').text() === 'true') {
                    $('#clLogUserName').html($(response).find('log > user > name').text());
                    $('#clLogDate').html($(response).find('log > date').text());
                    $('#cm-clNewComment').data('instance').setValue('');
                    $('#clComments > li:not(.first)').remove();
                    $clComments = $('#clComments');
                    $(response).find('comments').children().each(function(i,e) {
                        $('<li>').css('background', i % 2 == 0 ? '#fff' : '#f9f9f9')
                            .append($('<div>').addClass('left')
                                .append($('<span>').addClass('overflowEllipsis')
                                    .html($(e).find('user > name').text()+':'))
                                .append($('<span>').html($(e).find('date').text()))
                            ).append($('<div>').addClass('right').
                                append($('<span>').html($(e).find('content').text()))
                            ).append($('<div>').css('clear', 'both'))
                        .appendTo($clComments);
                    });
                }
            };
            break;
        default:
            if (document.location.href.indexOf('/signIn') < 0)
                document.location.hash = '#!/explore';
            return;
    }
 
    if (!isRequesting) {
        isRequesting = true;
        $('#notification').hide();
        $('#ajaxLoader').show();
        $.ajax({
            url: appContext + request,
            type: /\/([^\/]*)\//.exec(request)[1],
            data: params,
            timeout: 5000,
            success: function(data) {
                if ($(data).find('sessionTimeOut').text() === 'true')
                    document.location.href = appContext + '/signIn';
                response = $(data);
                action();
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.log(textStatus);
                response = $('<response>').html(
                    $('<message>').html(
                    $('#messages .ixNetworkFail').html())
                );
            },
            complete: function() {
                isRequesting = false;
                update(response, redirect);
            },
            async: asynchronous === false ? false : true
        });
    }
}

$(document).ready(function() {   
    $(document).on('submit', 'form', function(e) {
        e.preventDefault();
        controller($(this).attr('action'), $(this).serialize());
    });
    $(window).on('hashchange', function(){
        var hash = document.location.hash.replace(/#!/, '');
        hash = hash.split('?');
        controller('/get/view' + hash[0], hash[1]);
    });
    $(window).trigger('hashchange');
});