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


manageDocumentsController = {};

manageDocumentsController.confirmRemoveDocument = function(targetDocument) {

    var documentId = $(targetDocument).data('document.id');
    var documentName = $(targetDocument).data('document.name');
    var title = $('#messages .removeDocumentConfirmationTitle').text();
    var message = $('#messages .removeDocumentConfirmation').text();
    var deleteMsg = $('#messages .remove').text();
    var cancelMsg = $('#messages .cancel').text();
    $.confirm({
        'title'	: title,
        'message'	: $('<div>')
                        .append($('<span>').addClass('itemName')
                            .addClass('overflowEllipsis').text(documentName))
                        .append($('<span>').text(message)).html(),
        'buttons'	: {
            'delete'	: {
                'msg'   : deleteMsg,
                'class'	: 'red',
                'action': function(){
                    manageDocumentsController.removeDocument(documentId);
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

manageDocumentsController.createDocument = function() {

    baseController.ajaxRequest('/post/createDocument', function(response) {
        var redirect;
        if ($(response).find('success').text() === 'true') {
            redirect = '#!/document';
        }
        return redirect;
    }, $('#mdCreateForm').serialize());

};

manageDocumentsController.loadDocument = function(targetProject) {

    baseController.ajaxRequest('/post/loadDocument', function(response) {
        var redirect;
        if ($(response).find('success').text() === 'true') {
            redirect = '#!/document';
        }
        return redirect;
    }, 'document=' + $(targetProject).data('document.id'));

};

manageDocumentsController.removeDocument = function(documentId) {

  baseController.ajaxRequest('/post/removeDocument', function(response) {
        if ($(response).find('success').text() === 'true') {
            manageDocumentsController.updateDocumentsList(response);
        }
    }, 'document=' + documentId);

};

manageDocumentsController.setEditableView = function(trigger) {

    var container = $(trigger).parents('form');
    container.find('.noneditable').css('display', 'none');
    container.find('.editable').css('display', 'block');
    container.find('.options').css('display', 'none');
    container.find('.saveConfirmation').css('display', 'block');
    var $title = container.find('.title');
    var $titleEdit = container.find('.titleEdit');
    $titleEdit.val($title.text());

};

manageDocumentsController.setNonEditableView = function(trigger) {

    var container = $(trigger).parents('form');
    container.find('.editable').css('display', 'none');
    container.find('.noneditable').css('display', 'block');
    container.find('.saveConfirmation').css('display', 'none');
    container.find('.options').css('display', 'block');

};

manageDocumentsController.updateDocumentDescriptors = function(trigger) {

    var $documentForm = $(trigger).parents('form');
    baseController.ajaxRequest('/post/updateDocumentDescriptors', function(response) {
        if ($(response).find('success').text() === 'true') {
            manageDocumentsController.updateDocumentsList(response);
        }
    }, $documentForm.serialize());

};

manageDocumentsController.updateDocumentsList = function(response) {

    var $xmlDocuments = $(response).find('documents').children();
    var documents = [];
    var documentId;
    var documentName;
    var isSelected;
    var captionLoad = $('#messages .load').text();
    var captionEdit = $('#messages .edit').text();
    var captionCancel = $('#messages .cancel').text();
    var captionSave = $('#messages .save').text();
    $xmlDocuments.each(function(i, e) {
        documentId = $(e).attr('id');
        documentName = $(e).children('name').text();
        isSelected = $(e).attr('isSelected') === 'true';
        documents.push($('<li>').addClass(isSelected ? 'rowSelected' : i % 2 == 0 ? 'rowEven' : 'rowOdd')
            .append($('<form>').attr('action', '/post/updateDocumentDescriptors').attr('method', 'post')
                .append($('<input>').attr('type', 'hidden'). attr('name', 'document').val(documentId))
                .append($('<a>').addClass('clear').data('document.id', documentId)
                        .data('document.name', documentName).html('&#215;'))
                .append($('<h2>').addClass('title overflowEllipsis noneditable').text(documentName))
                .append($('<input>').addClass('titleEdit editable').attr('type', 'text')
                        .attr('name', 'name').val(documentName))
                .append($('<div>').addClass('options')
                    .append($('<a>').addClass('button load').data('document.id', documentId).text(captionLoad))
                    .append($('<a>').addClass('button edit').text(captionEdit))
                )
                .append($('<div>').addClass('saveConfirmation')
                    .append($('<a>').addClass('button cancelSave').text(captionCancel))
                    .append($('<input>').addClass('button save').attr('type', 'submit').val(captionSave))
                )
            )
            .get(0));
    });
    $('#mdDocumentsList').html(documents);

};