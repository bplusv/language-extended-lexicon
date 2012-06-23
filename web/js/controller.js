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
    var request = form.attr('action').replace('/lel/ajax', '');
    var response, action, redirect;
    
    switch (method) {
        case 'GET':
            switch (request) {
                case '/classify':
                    action = function() {};
                    break;
                case '/document':
                    action = function() {};
                    break;
                case '/explore':
                    action = function() {};
                    break;
                case '/loadDocument':
                    action = function() {};
                    break;
                case '/loadProject':
                    action = function() {};
                    break;
                case '/test':
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
                case '/doChooseLanguage':
                    action = function() {
                        if ($(response).find('success').text() === 'true') {
                            document.location.reload();
                        }
                    }
                    break;
                case '/doCreateDocument':
                    action = function() {
                        if ($(response).find('success').text() === 'true') {
                            redirect = '/document';
                        }
                    };
                    break;
                case '/doCreateProject':
                    action = function() { 
                        if ($(response).find('success').text() == 'true') {
                            projectName = $(response).find('project').find('name').text();
                            $('#ixProjectTitle').show();
                            $('#ixProjectName').html(projectName);
                            redirect = '/explore';
                        }
                    };
                    break;
                case '/doCreateSymbol':
                    action = function() {};
                    break;
                case '/doLoadDocument':
                    action = function() {
                        if ($(response).find('success').text() === 'true') {
                            redirect = '/document';
                        }
                    };
                    break;
                case '/doLoadProject':
                    action = function() { 
                        if ($(response).find('success').text() === 'true') {
                            projectName = $(response).find('project').find('name').text();
                            $('#ixProjectTitle').show();
                            $('#ixProjectName').html(projectName);
                            redirect = '/explore';
                        }
                    };
                    break;
                case '/doSignIn':
                    action = function() { 
                        if ($(response).find('success').text() === 'true') {
                            document.location.href = '/lel/';
                        }
                    };
                    break;
                case '/doSignOut':
                    action = function() { 
                        if ($(response).find('success').text() === 'true') {
                            window.location.href = '/lel/signIn';
                        }
                    };
                    break;
                case '/doUpdateDocument':
                    action = function() {};
                    break;
                case '/doUpdateSymbol':
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
        $.ajax({
            url: form.attr('action'),
            type: form.attr('method'),
            data: form.serialize(),
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
                $(this).attr('action').replace('/lel/ajax','') + 
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
            .attr('action', '/lel/ajax' + hash[0])
            .attr('method', 'GET')
            .serialize = function() {return hash[1];} 
        controller(form); 
    });
    $(window).trigger('hashchange');
    $window = $(window);
});
