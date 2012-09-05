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

function updateClassifyFields() {
    // is 'general' category selected?
    if ($('#clCategory').val() === '1') {
        $('#clClassificationField').hide();
        $('#clIntentionFields').hide();
    } else {
        $('#clClassificationField').show();
        $('#clIntentionFields').show();
    }
}

function updateComments(response) {
    $('#clComments > li:not(.first)').remove();
    $clComments = $('#clComments');
    $(response).find('comments').children().each(function(i,e) {
        $('<li>').css('background', i % 2 == 0 ? '#fff' : '#f9f9f9')
            .append($('<div>').addClass('left')
                .append($('<span>').addClass('overflowEllipsis')
                    .html($(e).find('user > name').text()+':'))
                .append($('<span>').html($(e).find('date').text()))
            ).append($('<div>').addClass('right').html(
                tagSymbols($(e).find('content').text()))
            ).append($('<div>').css('clear', 'both'))
        .appendTo($clComments);
    });
}
    
$(document).ready(function() {
    $(document).on('change', '#clCategory', function() {
       updateClassifyFields(); 
    });
    
    $(document).on('change', '#clSynonymsSelect', function() {
        controller('/get/data/classifySelectSynonym',
            'symbol=' + $(this).val()); 
    });
    
    $(document).on('click', '#clChangeGroup', function() {
        $(window).scrollTop($('#clTitle').offset().top);
        $('#clSynonymsSelect').css('display', 'block');
        $('#clLeaveGroup').css('display', 'none');
        $('#clChangeGroup').css('display', 'none');
        $('#clCancelGroup').css('display', 'inline');
        $('#clSaveGroup').css('display', 'inline');
        controller('/get/data/classifyShowSynonyms');
    });
    
    $(document).on('click', '#clCancelGroup', function() {
        $(window).scrollTop(0);
        $('#clSynonymsSelect').val(-1);
        $('#clSynonymsSelect').css('display', 'none');
        $('#clChangeGroup').css('display', 'inline');
        $('#clCancelGroup').css('display', 'none');
        $('#clSaveGroup').css('display', 'none');
        if ($('#clSymbol').val()) {
            controller('/get/data/classifySelectSynonym',
                'symbol=' + $('#clSymbol').val());
        } else {
            $('#clCategory').val(-1);
            $('#clCategory').trigger('change');
            $('#clClassification').val(-1);
            $('#clNotion').html('');
            $('#clActualIntention').html('');
            $('#clFutureIntention').html('');
            $('#clComments').html('');
            $('#clDocumentTitle').html($('#clDocumentName').val());
        }
    });
    
    $(document).on('click', '#clSaveGroup', function() {
        $('#clSynonymsSelect').val(-1);
        $('#clSynonymsSelect').css('display', 'none');
        $('#clChangeGroup').css('display', 'inline');
        $('#clCancelGroup').css('display', 'none');
        $('#clSaveGroup').css('display', 'none');
    });
    
    $(document).on('click', '#clSaveSymbol', function() {
        $('#clSynonymsSelect').val(-1);
        $('#clSynonymsSelect').css('display', 'none');
        $('#clLeaveGroup').css('display', 'inline');
        $('#clChangeGroup').css('display', 'inline');
        $('#clCancelGroup').css('display', 'none');
        $('#clSaveGroup').css('display', 'none');
    });
    
    $(document).on('click', '#clLeaveGroup', function() {
        controller('/post/leaveSynonymsGroup',
            'symbol=' + $('#clSymbol').val() + 
            '&category=' + $('#clCategory').val() +
            '&classification=' + $('#clClassification').val());
    });
});
