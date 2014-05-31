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


manageProjectUsersController = {};

manageProjectUsersController.addProjectUser = function() {

    baseController.ajaxRequest('/post/addProjectUser', function(response) {
        if ($(response).find('success').text() === 'true') {
            $('#mpuAddUserName').val('');
            manageProjectUsersController.updateUserList(response);
        }
    }, $('#mpuAddUserForm').serialize());

};

manageProjectUsersController.confirmRemoveUser = function(targetUser) {

    var userId = $(targetUser).data('user.id');
    $('#mpuRemoveUserId').val(userId);
    var userName = $(targetUser).data('user.name');
    var title = $('#messages .removeProjectUserConfirmationTitle').text();
    var message = $('#messages .removeProjectUserConfirmation').text();
    var deleteMsg = $('#messages .remove').text();
    var cancelMsg = $('#messages .cancel').text();
    $.confirm({
        'title'	: title,
        'message'	: $('<div>')
                        .append($('<span>').addClass('itemName')
                            .addClass('overflowEllipsis').text(userName))
                        .append($('<span>').text(message)).html(),
        'buttons'	: {
            'delete'	: {
                'msg'   : deleteMsg,
                'class'	: 'red',
                'action': function(){
                    manageProjectUsersController.removeProjectUser();
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

manageProjectUsersController.removeProjectUser = function() {

    baseController.ajaxRequest('/post/removeProjectUser', function(response) {
        if ($(response).find('success').text() === 'true') {
            manageProjectUsersController.updateUserList(response);
        }
    }, $('#mpuRemoveUserForm').serialize());
};

manageProjectUsersController.updateUserList = function(response) {

    var $xmlUsers = $(response).find('users').children();
    var users = [];
    $xmlUsers.each(function(i, e) {
    users.push($('<li>').addClass(i % 2 === 0 ? 'rowEven' : 'rowOdd')
        .append($('<span>').addClass('overflowEllipsis').text($(e).find('name').text()))
        .append($('<a>').addClass('removeUser').data('user.id', $(e).attr('id'))
            .data('user.name', $(e).find('name').text()).html('&#215;')).get(0));
    });
    $('#mpuUsersList').html(users);

};