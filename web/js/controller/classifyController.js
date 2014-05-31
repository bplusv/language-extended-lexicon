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


classifyController = {};

classifyController.cancelSelectSynonym = function() {
    
    $('#clSynonymsSelect').val(-1);
    $('#clSynonymsSelect').css('display', 'none');
    $('#clCancelGroup').css('display', 'none');
    $('#clChangeGroup').css('display', 'inline');
    classifyController.selectSynonym($('#clSymbol').val());
    $('#clCommandPanelWrapper').height($('#commandPanel').outerHeight() + 25);
    
};

classifyController.createSymbol = function() {
    
    baseController.ajaxRequest('/post/createSymbol', function(response) {
        if ($(response).find('success').text() === 'true') {
            $('#clCancelGroup').css('display', 'none');
            $('#clSaveGroup').css('display', 'none');
            $('#clChangeGroup').css('display', 'inline');
            $('#clSynonymsSelect').val(-1);
            $('#clSynonymsSelect').css('display', 'none');
            $('#clSymbol').val($(response).find('symbol').attr('id'));
            $('#clForm').attr('action', '/post/updateSymbol');
            $('#clDefinitionTop > div.right').css('visibility', 'visible');
            $('#clLogUserName').text($(response).find('log > user > name').text());
            $('#clLogDate').text($(response).find('log > date').text());
            baseController.updateProjectSymbols();
            baseController.updateCodeMirrorEditors();
            classifyController.updateComments(response);
        }
    }, $('#clForm').serialize());
    
};

classifyController.leaveSynonymsGroup = function(params) {
    
    baseController.ajaxRequest('/post/leaveSynonymsGroup', function(response) {
        if ($(response).find('success').text() === 'true') {
            $('#clLeaveGroup').css('display', 'none');
            $('#clSynonymsGroup').html('');
            $('#clLogUserName').text($(response).find('log > user > name').text());
            $('#clLogDate').text($(response).find('log > date').text());
            $('#clNotion').data('codeMirror').setValue('');
            $('#clActualIntention').data('codeMirror').setValue('');
            $('#clFutureIntention').data('codeMirror').setValue('');
            $('#clNewComment').data('codeMirror').setValue('');
            classifyController.updateComments(response);
        }
    }, $('#clForm').serialize());
    
};

classifyController.selectSynonym = function(id) {
    
    baseController.ajaxRequest('/get/data/classifySelectSynonym', function(response) {
        var $xmlSynonyms = $(response).find('synonymsGroup').children();
        var synonyms = [];
        $xmlSynonyms.each(function(i, e) {
            if ($(e).attr('id') !== $('#clSymbol').val())
                synonyms.push($('<a>').attr('href','#!/classify?sy='+ 
                    $(e).attr('id')).text($(e).children('name').text()).get(0).outerHTML);
        });
        $('#clSynonymsGroup').html(synonyms.join(', '));
        if ($(response).find('symbol').attr('id') === $('#clSymbol').val()) {
            $('#clLeaveGroup').css('display', synonyms.length > 0 ? 'inline' : 'none');
        }
        $('#clDocumentTitle').text($(response).find('document > name').text() || $('#clDocumentName').val());
        $('#clCategory').val($(response).find('category').text());
        $('#clClassification').val($(response).find('classification').text());
        $('#clLogUserName').text($(response).find('log > user > name').text());
        $('#clLogDate').text($(response).find('log > date').text());
        $('#clNotion').data('codeMirror').setValue($(response).find('notion').text());
        $('#clActualIntention').data('codeMirror').setValue($(response).find('actualIntention').text());
        $('#clFutureIntention').data('codeMirror').setValue($(response).find('futureIntention').text());
        classifyController.updateComments(response);
        classifyController.updateInterface();
    }, 'symbol=' + id);
    
};

classifyController.hideComments = function() {
    
    $('#clComments').css('display', 'none');
    $('#clShowComments').css('display', 'inline-block');
    $('#clHideComments').css('display', 'none');
    
};

classifyController.showComments = function() {
    
    $('#clComments').css('display', 'block');
    $('#clShowComments').css('display', 'none');
    $('#clHideComments').css('display', 'inline-block');
    
};

classifyController.showPossibleSynonyms = function() {
    
    $('#clSynonymsSelect').css('display', 'block');
    $('#clLeaveGroup').css('display', 'none');
    $('#clChangeGroup').css('display', 'none');
    $('#clCancelGroup').css('display', 'inline');
    baseController.ajaxRequest('/get/data/classifyShowPossibleSynonyms', function(response) {
        var $xmlPossibleSynonyms = $(response).find('symbols').children();
        var syId = $('#clSymbol').val();
        var selectItems = [];
        $xmlPossibleSynonyms.each(function(i, e) {
            if ($(e).attr('id') !== syId) {
                selectItems.push($('<option>').attr('value', 
                    $(e).attr('id')).text($(e).children('name').text()).get(0));
            }
        });
        $('#clSynonymsSelect').html(selectItems);
        $('#clCommandPanelWrapper').height($('#commandPanel').outerHeight() + 25);
    });
    
};

classifyController.updateComments = function(response) {
    
    var $xmlComments = $(response).find('comments').children();
    var comments = [];
    $xmlComments.each(function(i, e) {
    comments.push($('<li>').addClass(i % 2 === 0 ? 'rowEven' : 'rowOdd')
        .append($('<div>').addClass('left')
            .append($('<span>').addClass('overflowEllipsis')
                .text($(e).find('user > name').text()+':'))
            .append($('<span>').text($(e).find('date').text())))
        .append($('<div>').addClass('right').html(
            baseController.tagSymbols($(e).find('content').text())))
        .append($('<div>').css('clear', 'both')).get(0));
    });
    var $clComments = $('#clComments');
    var $clNewComment = $('#clNewComment');
    $clComments.html(comments);
    $clComments.scrollTop(0);
    if ($clNewComment.val()) {
        $('#clCommentsToggle').css('display', 'block');
        classifyController.showComments();
    }
    $clNewComment.data('codeMirror').setValue('');
    
};

classifyController.updateInterface = function() {
    
    // is 'general term' or 'no functional requirement' category selected?
    if ($('#clCategory').val() === '1' || $('#clCategory').val() === '2') {
        $('#clClassificationLabel').hide();
        $('#clClassification').hide();
        $('#clIntentionFields').hide();
    } else {
        $('#clClassificationLabel').show();
        $('#clClassification').show();
        $('#clIntentionFields').show();
    }
    if ($('#clComments').children().length > 0) {
        $('#clCommentsToggle').css('display', 'block');
    } else {
        $('#clCommentsToggle').css('display', 'none');
    }
};

classifyController.updateSymbol = function() {
    
    baseController.ajaxRequest('/post/updateSymbol', function(response) {
        if ($(response).find('success').text() === 'true') {
            $('#clCancelGroup').css('display', 'none');
            $('#clSaveGroup').css('display', 'none');
            $('#clChangeGroup').css('display', 'inline');
            $('#clSynonymsSelect').val(-1);
            $('#clSynonymsSelect').css('display', 'none');
            $('#clCommandPanelWrapper').height($('#commandPanel').outerHeight() + 25);
            var synonyms = $(response).find('synonymsGroup').children();
            $('#clLeaveGroup').css('display', synonyms.length > 0 ? 'inline' : 'none');
            $('#clLogUserName').text($(response).find('log > user > name').text());
            $('#clLogDate').text($(response).find('log > date').text());
            classifyController.updateComments(response);
        }
    }, $('#clForm').serialize());
    
};