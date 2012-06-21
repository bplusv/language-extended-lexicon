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

/*
 * jQuery hashchange event - v1.3 - 7/21/2010
 * http://benalman.com/projects/jquery-hashchange-plugin/
 * 
 * Copyright (c) 2010 "Cowboy" Ben Alman
 * Dual licensed under the MIT and GPL licenses.
 * http://benalman.com/about/license/
 */
(function($,e,b){var c="hashchange",h=document,f,g=$.event.special,i=h.documentMode,d="on"+c in e&&(i===b||i>7);function a(j){j=j||location.href;return"#"+j.replace(/^[^#]*#?(.*)$/,"$1")}$.fn[c]=function(j){return j?this.bind(c,j):this.trigger(c)};$.fn[c].delay=50;g[c]=$.extend(g[c],{setup:function(){if(d){return false}$(f.start)},teardown:function(){if(d){return false}$(f.stop)}});f=(function(){var j={},p,m=a(),k=function(q){return q},l=k,o=k;j.start=function(){p||n()};j.stop=function(){p&&clearTimeout(p);p=b};function n(){var r=a(),q=o(m);if(r!==m){l(m=r,q);$(e).trigger(c)}else{if(q!==m){location.href=location.href.replace(/#.*/,"")+q}}p=setTimeout(n,$.fn[c].delay)}$.browser.msie&&!d&&(function(){var q,r;j.start=function(){if(!q){r=$.fn[c].src;r=r&&r+a();q=$('<iframe tabindex="-1" title="empty"/>').hide().one("load",function(){r||l(a());n()}).attr("src",r||"javascript:0").insertAfter("body")[0].contentWindow;h.onpropertychange=function(){try{if(event.propertyName==="title"){q.document.title=h.title}}catch(s){}}}};j.stop=k;o=function(){return a(q.location.href)};l=function(v,s){var u=q.document,t=$.fn[c].domain;if(v!==s){u.title=h.title;u.open();t&&u.write('<script>document.domain="'+t+'"<\/script>');u.close();q.location.hash=v}}})();return j})()})(jQuery,this);

function update(request, response) {
    $('.overflowEllipsis').wrapInner('<span class="overflowText" />');
    
    $('.tabs a').removeClass('selected');
    switch (request) {
        case '/explore':
            $('#exploreTab').addClass('selected');       
            break;
        case '/document':
            $('#documentTab').addClass('selected');       
            break;
    }
    
    if (response) {
        success = $(response).find('success').text();
        message = $(response).find('message').text();
        if (message) {
            $('#notification')
                .removeClass()
                .addClass(success === 'true' ? 'success' : 'fail')
                .html(message)
                .show()
                .delay(3000).fadeOut();
        }
    }
}

function controller(form) {
    method = form.attr('method').toUpperCase();
    request = form.attr('action').replace('/lel', '');
    var action = function() {};
    
    switch (method) {
        case 'GET':
            switch (request) {
                case '/classify':
                    action = function() {}
                    break;
                case '/document':
                    action = function() {}
                    break;
                case '/explore':
                    action = function() {}
                    break;
                case '/loadDocument':
                    action = function() {}
                    break;
                case '/loadProject':
                    action = function() {}
                    break;
                case '/test':
                    action = function() {}
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
                    action = function(response) {
                        if ($(response).find('success').text() === 'true') {
                            document.location.reload();
                        }
                    }
                    break;
                case '/doCreateDocument':
                    action = function(response) {
                        if ($(response).find('success').text() === 'true') {
                            if (document.location.hash.indexOf('/document') > 0) $(window).hashchange();
                            else document.location.hash = '#!/document';
                        }
                    }
                    break;
                case '/doCreateProject':
                    action = function(response) { 
                        if ($(response).find('success').text() == 'true') {
                            projectName = $(response).find('project').find('name').text();
                            $('#ixProjectTitle').css('display', 'block');
                            $('#ixProjectName').html(projectName);
                            if (document.location.hash.indexOf('/explore') > 0) $(window).hashchange();
                            else document.location.hash = '#!/explore';
                        }
                    }
                    break;
                case '/doCreateSymbol':
                    action = function(response) {}
                    break;
                case '/doLoadDocument':
                    action = function(response) {
                        if ($(response).find('success').text() === 'true') {
                            if (document.location.hash.indexOf('/document') > 0) $(window).hashchange();
                            else document.location.hash = '#!/document';
                        }
                    }
                    break;
                case '/doLoadProject':
                    action = function(response) { 
                        if ($(response).find('success').text() === 'true') {
                            projectName = $(response).find('project').find('name').text();
                            $('#ixProjectTitle').css('display', 'block');
                            $('#ixProjectName').html(projectName);
                            if (document.location.hash.indexOf('/explore') > 0) $(window).hashchange();
                            else document.location.hash = '#!/explore';
                        }
                    }
                    break;
                case '/doSignIn':
                    action = function(response) { 
                        if ($(response).find('success').text() === 'true') {
                            document.location.href = '/lel/';
                        }
                    }
                    break;
                case '/doSignOut':
                    action = function(response) { 
                        if ($(response).find('success').text() === 'true') {
                            window.location.href = '/lel/signIn';
                        }
                    }
                    break;
                case '/doUpdateDocument':
                    action = function(response) {}
                    break;
                case '/doUpdateSymbol':
                    action = function(response) {}
                    break;
                default:
                    return;
        }
        break;
        default:
            return;
    }
 
    $.ajax({
        url: form.attr('action'),
        type: form.attr('method'),
        data: form.serialize(),
        dataType: form.attr('method') === 'GET' ? 'HTML' : 'XML',
        success: function(data) {
            var response;
            switch (form.attr('method').toUpperCase()) {
                case 'GET':
                    $('#central').html(data);
                    break;
                case 'POST':
                    response = $(data).find('response');
                    break;
            }
            update(request, response);
            action(response);
        }
    });
}

$(document).ready(function() {   
    $(document).on('submit', 'form', function(e) {
        e.preventDefault();
        switch ($(this).attr('method').toUpperCase()) {
            case 'GET':
                document.location.hash = '#!' + 
                $(this).attr('action').replace('/lel','') + 
                '?' + $(this).serialize();
                break;
            case 'POST':
                controller($(this));
                break;
        }
    });
    $(document).on('mouseenter', '.overflowEllipsis', function(e) {
        $this = $(this);
        $this.css('text-overflow', 'clip');
        $that = $this.find('span.overflowText');
        offset = $this.width() - $that.width();
        if (offset < 0) {
            $that.animate({'margin-left': offset}, {duration: offset * -10, easing: 'linear'});
        } 
    });
    $(document).on('mouseleave', '.overflowEllipsis', function(e) {
        $this = $(this);
        $this.css('text-overflow', 'ellipsis');
        $that = $this.find('span.overflowText');
        offset = $this.width() - $that.width();
        if (offset < 0) {
            $that.clearQueue().stop();
            $that.css('margin-left', 0);
        }
    });
    
    $(window).hashchange(function(){
        var hash = document.location.hash.replace(/#!/, '');
	hash = hash.split('?');
        form = $('<form>');
        form
            .attr('action', '/lel' + hash[0])
            .attr('method', 'GET')
            .serialize = function() {return hash[1];} 
        controller(form); 
    });
    $(window).hashchange();
});
