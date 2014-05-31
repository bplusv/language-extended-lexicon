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


manageProjectsController = {};

manageProjectsController.createProject = function() {

    baseController.ajaxRequest('/post/createProject', function(response) {
        var redirect;
        if ($(response).find('success').text() === 'true') {
            var projectName = $(response).find('project').find('name').text();
            $('#ixProjectTitle').show();
            $('#ixProjectName').text(projectName);
            redirect = '#!/explore';
        }
        return redirect;
    }, $('#mpCreateForm').serialize());

};

manageProjectsController.leaveProject = function(projectId) {

    baseController.ajaxRequest('/post/leaveProject', function(response) {
        if ($(response).find('success').text() === 'true') {
            manageProjectsController.updateProjectsList(response);
        }
    }, 'project=' + projectId);

};

manageProjectsController.confirmLeaveProject = function(targetProject) {

    var projectId = $(targetProject).data('project.id');
    var projectName = $(targetProject).data('project.name');
    var title = $('#messages .leaveProjectConfirmationTitle').text();
    var message = $('#messages .leaveProjectConfirmation').text();
    var deleteMsg = $('#messages .leave').text();
    var cancelMsg = $('#messages .cancel').text();
    $.confirm({
        'title'	: title,
        'message'	: $('<div>')
                        .append($('<span>').addClass('itemName')
                            .addClass('overflowEllipsis').text(projectName))
                        .append($('<span>').text(message)).html(),
        'buttons'	: {
            'delete'	: {
                'msg'   : deleteMsg,
                'class'	: 'red',
                'action': function(){
                    manageProjectsController.leaveProject(projectId);
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

manageProjectsController.confirmRemoveProject = function(targetProject) {

    var projectId = $(targetProject).data('project.id');
    var projectName = $(targetProject).data('project.name');
    var title = $('#messages .removeProjectConfirmationTitle').text();
    var message = $('#messages .removeProjectConfirmation').text();
    var deleteMsg = $('#messages .remove').text();
    var cancelMsg = $('#messages .cancel').text();
    $.confirm({
        'title'	: title,
        'message'	: $('<div>')
                        .append($('<span>').addClass('itemName')
                            .addClass('overflowEllipsis').text(projectName))
                        .append($('<span>').text(message)).html(),
        'buttons'	: {
            'delete'	: {
                'msg'   : deleteMsg,
                'class'	: 'red',
                'action': function(){
                    manageProjectsController.removeProject(projectId);
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

manageProjectsController.loadProject = function(targetProject) {

    baseController.ajaxRequest('/post/loadProject', function(response) {
        var redirect;
        if ($(response).find('success').text() === 'true') {
            var projectName = $(response).find('project').find('name').text();
            $('#ixProjectTitle').show();
            $('#ixProjectName').text(projectName);
            redirect = '#!/explore';
        }
        return redirect;
    }, 'project=' + $(targetProject).data('project.id'));

};

manageProjectsController.removeProject = function(projectId) {

  baseController.ajaxRequest('/post/removeProject', function(response) {
        if ($(response).find('success').text() === 'true') {
            manageProjectsController.updateProjectsList(response);
        }
    }, 'project=' + projectId);

};

manageProjectsController.setEditableView = function(trigger) {

    var container = $(trigger).parents('form');
    container.find('.noneditable').css('display', 'none');
    container.find('.editable').css('display', 'block');
    container.find('.options').css('display', 'none');
    container.find('.saveConfirmation').css('display', 'block');
    var $title = container.find('.title');
    var $description = container.find('.description');
    var $titleEdit = container.find('.titleEdit');
    var $descriptionEdit = container.find('.descriptionEdit');
    $titleEdit.val($title.text());
    $descriptionEdit.val($description.text());
    $descriptionEdit.height($description.height());

};

manageProjectsController.setNonEditableView = function(trigger) {

    var container = $(trigger).parents('form');
    container.find('.editable').css('display', 'none');
    container.find('.noneditable').css('display', 'block');
    container.find('.saveConfirmation').css('display', 'none');
    container.find('.options').css('display', 'block');

};

manageProjectsController.updateProjectDescriptors = function(trigger) {

    var $projectForm = $(trigger).parents('form');
    baseController.ajaxRequest('/post/updateProjectDescriptors', function(response) {
        if ($(response).find('success').text() === 'true') {
            manageProjectsController.updateProjectsList(response);
        }
    }, $projectForm.serialize());

};

manageProjectsController.updateProjectsList = function(response) {

    var $xmlProjects = $(response).find('projects').children();
    var projects = [];
    var isOwner;
    var projectId;
    var projectName;
    var projectDescription;
    var isSelected;
    var captionOwner = $('#messages .owner').text();
    var captionLoad = $('#messages .load').text();
    var captionEdit = $('#messages .edit').text();
    var captionUsers = $('#messages .users').text();
    var captionCancel = $('#messages .cancel').text();
    var captionSave = $('#messages .save').text();
    var captionDescription = $('#messages .description').text();
    $xmlProjects.each(function(i, e) {
        projectId = $(e).attr('id');
        projectName = $(e).children('name').text();
        projectDescription = $(e).find('description').text();
        isOwner = $(e).attr('isOwner') === 'true';
        isSelected = $(e).attr('isSelected') === 'true';
        projects.push($('<li>').addClass(isSelected ? 'rowSelected' : i % 2 == 0 ? 'rowEven' : 'rowOdd')
            .append($('<form>').attr('action', '/post/updateProjectDescription').attr('method', 'POST')
                .append($('<input>').attr('type', 'hidden').attr('name', 'project').val(projectId))
                .append($('<a>').addClass('clear').addClass(isOwner ? 'remove' : 'leave')
                    .data('project.id', projectId).data('project.name', projectName).html('&#215;'))
                .append($('<h2>').addClass('title overflowEllipsis noneditable').text(projectName))
                .append($('<input>').addClass('titleEdit editable').attr('type', 'text').attr('name', 'name').val(projectName))
                .append($('<h3>').addClass('owner overflowEllipsis')
                    .append($('<label>').html(captionOwner + ':&nbsp;'))
                    .append($('<span>').text($(e).find('owner > name').text())))
                .append($('<label>').html(captionDescription + ':&nbsp;'))
                .append($('<p>').addClass('description noneditable').text(projectDescription))
                .append($('<textarea>').addClass('descriptionEdit editable').attr('name', 'description').text(projectDescription))
                .append($('<div>').addClass('options')
                    .append($('<a>').addClass('button load').data('project.id', projectId).text(captionLoad))
                    .append(isOwner ? $('<a>').addClass('button edit').text(captionEdit) : null)
                    .append(isOwner ? $('<a>').addClass('button')
                        .attr('href', '#!/manageProjectUsers?pj=' + projectId).text(captionUsers) : null))
                .append($('<div>').addClass('saveConfirmation')
                    .append($('<a>').addClass('button cancelSave').text(captionCancel))
                    .append($('<input>').addClass('button save').attr('type', 'submit').val(captionSave))
                )
            ).get(0));
    });
    $('#mpProjectsList').html(projects);

};