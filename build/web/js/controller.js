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

window.lel.controller = (function($, lel) {
    var appContext = '/lel';
    var isRequesting = false;
    function controller(request, params, asynchronous) {
        var response, action, redirect;

        switch (request) {
            case '/get/data/classifySelectSynonym':
                action = function() {
                    var $xmlSynonyms = $(response).find('synonymsGroup').children();
                    var synonyms = [];
                    $xmlSynonyms.each(function(i, e) {
                        if ($(e).attr('id') != $('#clSymbol').val())
                            synonyms.push($('<a>').attr('href','#!/classify?sy='+ 
                            $(e).attr('id')).text($(e).children('name').text())[0].outerHTML);
                    });
                    $('#clSynonymsGroup').html(synonyms.join(', '));
                    if ($(response).find('symbol').attr('id') === $('#clSymbol').val()) {
                        $('#clLeaveGroup').css('display', synonyms.length > 0 ? 'inline' : 'none');
                    }
                    $('#clDocumentTitle').text($(response).find('document > name').text());
                    $('#clCategory').val($(response).find('category').text());
                    $('#clCategory').trigger('change');
                    $('#clClassification').val($(response).find('classification').text());
                    $('#clLogUserName').text($(response).find('log > user > name').text());
                    $('#clLogDate').text($(response).find('log > date').text());
                    $('#clNotion').data('codeMirror').setValue($(response).find('notion').text());
                    $('#clActualIntention').data('codeMirror').setValue($(response).find('actualIntention').text());
                    $('#clFutureIntention').data('codeMirror').setValue($(response).find('futureIntention').text());
                    updateComments(response);
                };
                break;
            case '/get/data/classifyShowSynonyms':
                action = function() {
                    var $xmlSynonyms = $(response).find('synonyms').children();
                    var $sel = $('#clSynonymsSelect');
                    var syId = $('#clSymbol').val();
                    $sel.empty();
                    $xmlSynonyms.each(function(i, e) {
                        if ($(e).attr('id') != syId) {
                            $sel.append($('<option>').attr('value', 
                            $(e).attr('id')).text($(e).children('name').text()));
                        }
                    });
                };
            break;
            case '/get/data/exploreSymbols':
                action = function() {
                    var $xmlSymbols = $(response).find('symbols').children();
                    var $tbody = $('#exSymbolsTable tbody');
                    $tbody.empty();
                    $xmlSymbols.each(function(i, e) {
                        $('<tr>').wrapInner($('<td>').attr('colspan', '5')
                        .css('background', i % 2 == 0 ? '#fff' : '#f9f9f9').wrapInner(
                            $('<a>').addClass('exSymbolsRow').attr('href', '#!/classify?sy='+$(e).attr('id'))
                            .append($('<span>').addClass('overflowEllipsis exSyName').text($(e).children('name').text()))
                            .append($('<span>').addClass('overflowEllipsis').text($(e).find('category > name').text()))
                            .append($('<span>').addClass('overflowEllipsis').text($(e).find('classification > name').text()))
                            .append($('<span>').addClass('overflowEllipsis').text($(e).find('document > name').text()))
                            .append($('<span>').attr('id', 'exSy' + $(e).attr('id')).addClass('removeSymbol').html('&#215;'))
                        )).appendTo($tbody);
                    });
                    $('#exSearchClear').css('visibility', $('#exSearch').val() ? 'visible' : 'hidden');
                };
                break;
            case '/get/data/projectSymbols':
                action = function() {
                    var $xmlSymbols = $(response).find('symbols').children();
                    var symbols = {};
                    $xmlSymbols.each(function(i, e) {
                        symbols[$(e).children('name').text()] = $(e).attr('id');
                    });
                    lel.setProjectSymbols(symbols);
                }
                break;
            case '/get/view/classify':
                controller('/get/data/projectSymbols', '', false);
                action = function() {
                    $('#central').html(response);
                    lel.updateSymbolicEditors();
                    updateClassifyFields();
                };
                break;
            case '/get/view/document':
                controller('/get/data/projectSymbols', '', false);
                action = function() {
                    $('#central').html(response); 
                    lel.updateSymbolicEditors();
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
                        window.location.reload();
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
                        var projectName = $(response).find('project').find('name').text();
                        $('#ixProjectTitle').show();
                        $('#ixProjectName').text(projectName);
                        redirect = '#!/explore';
                    }
                };
                break;
            case '/post/createSymbol':
                action = function() {
                    if ($(response).find('success').text() === 'true') {
                        controller('/get/data/projectSymbols', '', false);
                        $('#clSynonymsSelect').val(-1);
                        $('#clSymbol').val($(response).find('symbol').attr('id'));
                        $('#clForm').attr('action', '/post/updateSymbol');
                        $('#clDefinitionTopRight').css('visibility', 'visible');
                        $('#clLogUserName').text($(response).find('log > user > name').text());
                        $('#clLogDate').text($(response).find('log > date').text());
                        $('#clNewComment').data('codeMirror').setValue('');
                        lel.updateSymbolicEditors();
                        updateComments(response);
                    }
                };
                break;
            case '/post/leaveSynonymsGroup':
                action = function() {
                    if ($(response).find('success').text() === 'true') {
                        $('#clLeaveGroup').css('display', 'none');
                        $('#clSynonymsGroup').html('');
                        $('#clLogUserName').text($(response).find('log > user > name').text());
                        $('#clLogDate').text($(response).find('log > date').text());
                        $('#clNotion').data('codeMirror').setValue('');
                        $('#clActualIntention').data('codeMirror').setValue('');
                        $('#clFutureIntention').data('codeMirror').setValue('');
                        $('#clNewComment').data('codeMirror').setValue('');
                        updateComments();
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
                        var projectName = $(response).find('project').find('name').text();
                        $('#ixProjectTitle').show();
                        $('#ixProjectName').text(projectName);
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
                        window.location.href = appContext;
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
                        $('#clSynonymsSelect').val(-1);
                        var synonyms = $(response).find('synonymsGroup').children();
                        $('#clLeaveGroup').css('display', synonyms.length > 0 ? 'inline' : 'none');
                        $('#clLogUserName').text($(response).find('log > user > name').text());
                        $('#clLogDate').text($(response).find('log > date').text());
                        $('#clNewComment').data('codeMirror').setValue('');
                        updateComments(response);
                    }
                };
                break;
            default:
                if (window.location.href.indexOf('/signIn') < 0) {
                    window.location.hash = '#!/explore';
                }
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
                    isRequesting = false;
                    if ($(data).find('sessionTimeOut').text() === 'true')
                        window.location.href = appContext + '/signIn';
                    response = $(data);
                    action();
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    isRequesting = false;
                    response = $('<response>').append(
                        $('<message>').text(
                        $('#messages .ixNetworkFail').text())
                    );
                },
                complete: function() {
                    isRequesting = false;
                    lel.update(response, redirect);
                },
                async: asynchronous === false ? false : true
            });
        }
    }
    return controller;
})(jQuery, lel);

$(function() {   
    $(window).on('submit', 'form', function(e) {
        e.preventDefault();
        lel.controller($(this).attr('action'), $(this).serialize());
    });
    $(window).on('hashchange', function(){
        var hash = window.location.hash.replace(/#!/, '');
        hash = hash.split('?');
        lel.controller('/get/view' + hash[0], hash[1]);
    });
    $(window).trigger('hashchange');
});