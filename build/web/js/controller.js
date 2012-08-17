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
                        synonyms.push('<a href="#!/classify?sy=' + $(e).attr('id') + '">' + 
                        $(e).children('name').text() + '</a>');
                });
                $('#clSynonyms').html(synonyms.join(', '));
                $('#clDocumentTitle').html($(response).find('document > name').text());
                $('#clCategory').val($(response).find('category').text());
                $('#clCategory').trigger('change');
                $('#clClassification').val($(response).find('classification').text());
                $('#clLogUserName').html($(response).find('log > user > name').text());
                $('#clLogDate').html($(response).find('log > date').text());
                $('#clNotion').html($(response).find('notion').text());
                $('#clActualIntention').html($(response).find('actualIntention').text());
                $('#clFutureIntention').html($(response).find('futureIntention').text());
                $('#clComments').html($(response).find('comments').text());
            };
            break;
        case '/get/data/classifyShowSynonyms':
            action = function() {
                var $xmlSynonyms = $(response).find('synonyms').children();
                var synonyms = '';
                $xmlSynonyms.each(function(i, e) {
                    if ($(e).attr('id') != $('#clSymbol').val()) {
                        synonyms += '<option value="' + $(e).attr('id') + '">' + $(e).children('name').text() + '</option>';
                    }
                });
                $('#clSynonymsSelect').html(synonyms);
            };
        break;
        case '/get/data/exploreSymbols':
            action = function() {
                var $xmlSymbols = $(response).find('symbols').children();
                var symbols = '';
                $xmlSymbols.each(function(i, e) {
                    symbols += 
                        '<tr>' +
                            '<td colspan="5" style="background-color:'+(i % 2 == 0 ? '#fff' : '#f9f9f9')+';">' +
                                '<a class="exSymbolsRow" href="#!/classify?sy='+$(e).attr('id')+'">' +
                                    '<span class="overflowEllipsis exSyName">'+ $(e).children('name').text()+'</span>' +
                                    '<span class="overflowEllipsis">'+$(e).find('category > name').text()+'</span>' +
                                    '<span class="overflowEllipsis">'+$(e).find('classification > name').text()+'</span>' +
                                    '<span class="overflowEllipsis">'+$(e).find('document > name').text()+'</span>' +
                                    '<span id="exSy'+$(e).attr('id')+'" class="removeSymbol">&#215;</span>' +
                                '</a>' +
                            '</td>' +
                        '</tr>';
                });
                $('#exSymbolsTable tbody').html(symbols);
                $('#exSearchClear').css('visibility', $('#exSearch').val() ? 'visible' : 'hidden');
            };
            break;
        case '/get/data/projectSymbols':
            action = function() {
                var $xmlSymbols = $(response).find('symbols').children();
                projectSymbols = [];
                $xmlSymbols.each(function(i, e) {
                    projectSymbols.push({'id':$(e).attr('id'), 'name':$(e).children('name').text()});
                });
            }
            break;
        case '/get/view/document':
            action = function() {
                    $('#central').html(response);
                    isRequesting = false;
                    controller('/get/data/projectSymbols', '', false);
                    if ($('#dcDocumentContent').length > 0) {
                        myCode = CodeMirror.fromTextArea($('#dcDocumentContent')[0],
                        {'onChange': tagSymbols, 'mode': 'text/plain'});
                        tagSymbols();
                    }
            }
            break;
        case '/get/view/classify':
        case '/get/view/explore':
        case '/get/view/loadDocument':
        case '/get/view/loadProject':
        case '/get/view/test':
            action = function() {$('#central').html(response);};
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
                $('#clLogUserName').html($(response).find('log > user > name').text());
                $('#clLogDate').html($(response).find('log > date').text());
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