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
function controller(form) {
    var method = form.attr('method').toUpperCase();
    var request = form.attr('action').replace('/lel', '');
    var response, action, redirect;
    
    switch (method) {
        case 'GET':
            switch (request) {
                case '/get/classify':
                    action = function() {};
                    break;
                case '/get/document':
                    action = function() {};
                    break;
                case '/get/explore':
                    action = function() {};
                    break;
                case '/get/loadDocument':
                    action = function() {};
                    break;
                case '/get/loadProject':
                    action = function() {};
                    break;
                case '/get/test':
                    action = function() {};
                    break;
                default:
                    if (document.location.href.indexOf('/signIn') < 0)
                        document.location.href = '/lel/#!/explore';
                    return;
            }
            break;
        case 'POST':
            switch (request) {
                case '/do/chooseLanguage':
                    action = function() {
                        if ($(response).find('success').text() === 'true') {
                            document.location.reload();
                        }
                    }
                    break;
                case '/do/createDocument':
                    action = function() {
                        if ($(response).find('success').text() === 'true') {
                            redirect = '#!/document';
                        }
                    };
                    break;
                case '/do/createProject':
                    action = function() { 
                        if ($(response).find('success').text() == 'true') {
                            projectName = $(response).find('project').find('name').text();
                            $('#ixProjectTitle').show();
                            $('#ixProjectName').html(projectName);
                            redirect = '#!/explore';
                        }
                    };
                    break;
                case '/do/createSymbol':
                    action = function() {
                        if ($(response).find('success').text() === 'true') {
                            $('#clSymbol').val($(response).find('symbol').attr('id'));
                            $('#clForm').attr('action', '/lel/do/updateSymbol');
                        }
                    };
                    break;
                case '/do/loadDocument':
                    action = function() {
                        if ($(response).find('success').text() === 'true') {
                            redirect = '#!/document';
                        }
                    };
                    break;
                case '/do/loadProject':
                    action = function() { 
                        if ($(response).find('success').text() === 'true') {
                            projectName = $(response).find('project').find('name').text();
                            $('#ixProjectTitle').show();
                            $('#ixProjectName').html(projectName);
                            redirect = '#!/explore';
                        }
                    };
                    break;
                case '/do/signIn':
                    action = function() { 
                        if ($(response).find('success').text() === 'true') {
                            document.location.href = '/lel/';
                        }
                    };
                    break;
                case '/do/signOut':
                    action = function() { 
                        if ($(response).find('success').text() === 'true') {
                            window.location.href = '/lel/signIn';
                        }
                    };
                    break;
                case '/do/updateDocument':
                    action = function() {};
                    break;
                case '/do/updateSymbol':
                    action = function() {};
                    break;
                default:
                    return;
        }
        break;
        default:
            return;
    }
 
    if (!isRequesting) {
        isRequesting = true;
        $('#notification').hide();
        $('#ajaxLoader').show();
        $.ajax({
            url: form.attr('action'),
            type: form.attr('method'),
            data: form.serialize(),
            timeout: 5000,
            success: function(data) {
                if ($(data).find('sessionTimeOut').text() === 'true')
                    document.location.href = '/lel/signIn';
                switch (form.attr('method').toUpperCase()) {
                    case 'GET':
                        $('#central').html(data);
                        break;
                    case 'POST':
                        response = $(data).find('response');
                        break;
                }
                action();
            },
            error: function() {
                response = $('<response>').html(
                    $('<message>').html(
                    $('#networkFail').html())
                );
            },
            complete: function() {
                isRequesting = false;
                update(response, redirect);
            }
        });
    }
}

$(document).ready(function() {   
    $(document).on('submit', 'form', function(e) {
        e.preventDefault();
        switch ($(this).attr('method').toUpperCase()) {
            case 'GET':
                document.location.hash = '#!' + 
                $(this).attr('action').replace('/lel/get','') + 
                '?' + $(this).serialize();
                break;
            case 'POST':
                controller($(this));
                break;
        }
    });
    $(window).on('hashchange', function(){
        var hash = document.location.hash.replace(/#!/, '');
	hash = hash.split('?');
        form = $('<form>');
        form
            .attr('action', '/lel/get' + hash[0])
            .attr('method', 'GET')
            .serialize = function() {return hash[1];} 
        controller(form); 
    });
    $(window).trigger('hashchange');
    $window = $(window);
});
