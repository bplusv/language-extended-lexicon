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
    var $clComments = $('#clComments');
    $clComments.children().remove();
    $(response).find('comments').children().each(function(i,e) {
        $('<li>').css('background', i % 2 == 0 ? '#fff' : '#f9f9f9')
            .append($('<div>').addClass('left')
                .append($('<span>').addClass('overflowEllipsis')
                    .text($(e).find('user > name').text()+':'))
                .append($('<span>').text($(e).find('date').text()))
            ).append($('<div>').addClass('right').html(
                lel.tagSymbols($(e).find('content').text()))
            ).append($('<div>').css('clear', 'both'))
        .appendTo($clComments);
    });
    $clComments.scrollTop(0);
}
    
$(function() {
    $(window).on('change', '#clCategory', function() {
       updateClassifyFields(); 
    });
    
    $(window).on('change', '#clSynonymsSelect', function() {
        lel.controller('/get/data/classifySelectSynonym',
            'symbol=' + $(this).val()); 
    });
    
    $(window).on('click', '#clChangeGroup', function() {
        $(window).scrollTop($('#clTitle').offset().top);
        $('#clSynonymsSelect').css('display', 'block');
        $('#clLeaveGroup').css('display', 'none');
        $('#clChangeGroup').css('display', 'none');
        $('#clCancelGroup').css('display', 'inline');
        $('#clSaveGroup').css('display', 'inline');
        lel.controller('/get/data/classifyShowSynonyms');
    });
    
    $(window).on('click', '#clCancelGroup', function() {
        $(window).scrollTop(0);
        $('#clSynonymsSelect').val(-1);
        $('#clSynonymsSelect').css('display', 'none');
        $('#clChangeGroup').css('display', 'inline');
        $('#clCancelGroup').css('display', 'none');
        $('#clSaveGroup').css('display', 'none');
        if ($('#clSymbol').val()) {
            lel.controller('/get/data/classifySelectSynonym',
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
    
    $(window).on('click', '#clSaveGroup', function() {
        $('#clSynonymsSelect').css('display', 'none');
        $('#clChangeGroup').css('display', 'inline');
        $('#clCancelGroup').css('display', 'none');
        $('#clSaveGroup').css('display', 'none');
    });
    
    $(window).on('submit', '#clForm', function() {
        $('#clSynonymsSelect').css('display', 'none');
        $('#clLeaveGroup').css('display', 'inline');
        $('#clChangeGroup').css('display', 'inline');
        $('#clCancelGroup').css('display', 'none');
        $('#clSaveGroup').css('display', 'none'); 
    });
    
    $(window).on('click', '#clLeaveGroup', function() {
        lel.controller('/post/leaveSynonymsGroup',
            'symbol=' + $('#clSymbol').val() + 
            '&category=' + $('#clCategory').val() +
            '&classification=' + $('#clClassification').val());
    });
});
