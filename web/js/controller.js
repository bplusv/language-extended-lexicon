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

function getSelectedText() {
    if(window.getSelection) {return window.getSelection();}
    else if(document.getSelection) {return document.getSelection();}
    else {
        var selection = document.selection && document.selection.createRange();
        if(selection.text) {return selection.text;}
        return '';
    }
    return '';
}

function update(request, response) {
    $('.overflowEllipsis').wrapInner('<span class="overflowText" />');
    
    $('.tabs a').removeClass('selected');
    switch (request) {
        case 'explore':
            $('#exploreTab').addClass('selected');       
            break;
        case 'document':
            $('#documentTab').addClass('selected');       
            break;
    }
    $('#central').fadeTo(300, 1.0);
    
    if (response) {
        success = $(response).find('success').text();
        message = $(response).find('message').text();
        if (message) {
            $('#notification').removeClass();
            $('#notification').addClass(success == 'true' ? 'success' : 'fail');
            $('#notification').html(message);
            $('#notification').show();
            $('#notification').delay(3000).fadeOut();
        }
    }
}

function controller(request, vars) {
    $('#central').fadeTo(0, 0.2);
    
    switch (request) {
        case 'classify':
            $.ajax({
                type: 'GET',
                url: '/lel/classify',
                data: vars,
                dataType: 'html',
                success: function(data) { 
                    $('#central').html(data);
                    update(request);
                }
            });
            break;
        case 'document':
            $.ajax({
                type: 'GET',
                url: '/lel/document',
                data: vars,
                dataType: 'html',
                success: function(data) { 
                    $('#central').html(data);
                    update(request);
                }
            });
            break;
        case 'explore':
            $.ajax({
                type: 'GET',
                url: '/lel/explore',
                data: vars,
                dataType: 'html',
                success: function(data) {
                    $('#central').html(data);
                    update(request);
                }
            });
            break;
        case 'loadDocument':
            $.ajax({
                type: 'GET',
                url: '/lel/loadDocument',
                data: vars,
                dataType: 'html',
                success: function(data) { 
                    $('#central').html(data);
                    update(request);
                }
            });
            break;
        case 'loadProject':
            $.ajax({
                type: 'GET',
                url: '/lel/loadProject',
                data: vars,
                dataType: 'html',
                success: function(data) { 
                    $('#central').html(data);
                    update(request);
                }
            });
            break;
        case 'test':
            $.ajax({
                type: 'GET',
                url: '/lel/test',
                data: vars,
                dataType: 'html',
                success: function(data) { 
                    $('#central').html(data);
                    update(request);
                }
            });
            break;
            
            
            
        case 'doCreateDocument':
            $.ajax({
                type: 'POST',
                url: '/lel/doCreateDocument',
                data: vars,
                dataType: 'xml',
                success: function(response) {
                    update('document', response);
                    
                    success = $(response).find('success').text();
                    message = $(response).find('message').text();
                    if (success == 'true') {
                        document.location.hash = '#!nav=document';
                        $(window).hashchange();
                    }
                }
            });
        break;
        case 'doCreateProject':
            $.ajax({
                type: 'POST',
                url: '/lel/doCreateProject',
                data: vars,
                dataType: 'xml',
                success: function(response) { 
                    update(request, response);
                    success = $(response).find('success').text();
                    message = $(response).find('message').text();
                    if (success == 'true') {
                        projectName = $(response).find('project').find('name').text();
                        $('#projectTitle').css('display', 'block');
                        $('#projectName').html(projectName);
                        document.location.hash = '#!nav=explore';
                        $(window).hashchange();
                    }
                }
            });
        break;
        case '/doCreateSymbol':
            $.ajax({
                type: 'POST',
                url: '/lel/doCreateSymbol',
                data: vars,
                dataType: 'xml',
                success: function(response) {
                    update('document', response);
                }
            });
        break;
        case 'doLoadDocument':
            $.ajax({
                type: 'POST',
                url: '/lel/doLoadDocument',
                data: vars,
                dataType: 'xml',
                success: function(response) {
                    update(request, response);
                    
                    success = $(response).find('success').text();
                    message = $(response).find('message').text();
                    if (success == 'true') {
                        document.location.hash = '#!nav=document';
                        $(window).hashchange();
                    }
                }
            });
            break;
        case 'doLoadProject':
            $.ajax({
                type: 'POST',
                url: '/lel/doLoadProject',
                data: vars,
                dataType: 'xml',
                success: function(response) { 
                    success = $(response).find('success').text();
                    message = $(response).find('message').text();
                    if (success == 'true') {
                        projectName = $(response).find('project').find('name').text();
                        $('#projectTitle').css('display', 'block');
                        $('#projectName').html(projectName);
                        document.location.hash = '#!nav=explore';
                        $(window).hashchange();
                    }
                    update(request, response);
                }
            });
            break;
        case 'doSignIn':
            $.ajax({
                type: 'POST',
                url: '/lel/doSignIn',
                data: vars,
                dataType: 'xml',
                success: function(response) { 
                    success = $(response).find('success').text();
                    message = $(response).find('message').text();
                    if (success == 'true') {
                        document.location.href = '/lel';
                    }
                    update(request, response);
                }
            });
            break;
        case 'doSignOut':
            $.ajax({
                type: 'POST',
                url: '/lel/doSignOut',
                data: vars,
                dataType: 'xml',
                success: function(data) { 
                    res = $(data).find('success').text();
                    if (res == 'true') {
                        window.location = '/lel/signIn';
                        update(request);
                    }
                }
            });
            break;
        case 'doUpdateDocument':
            $.ajax({
                type: 'POST',
                url: '/lel/doUpdateDocument',
                data: vars,
                dataType: 'xml',
                success: function(response) {
                    update('document', response);
                }
            });
        break;
        case '/doUpdateSymbol':
            $.ajax({
                type: 'POST',
                url: '/lel/doUpdateSymbol',
                data: vars,
                dataType: 'xml',
                success: function(response) {
                    update('document', response);
                }
            });
        break;

    }
    return false;
}

function ajax_update() {
	var hash = document.location.hash.replace(/#!/, '');
	hash = hash.split('&');
	var hash_values = {};
	hash_values['nav'] = 'explore';
        vars = '';
	for (i in hash) {
		hash_values[hash[i].split('=')[0]] = hash[i].split('=')[1];
                if (hash[i].split('=')[0] != 'nav') {
                    vars += '&' + hash[i].split('=')[0] + '=' + hash[i].split('=')[1];
                }
	}
        
        controller(hash_values['nav'], vars);
        
        /*
	var template_path = 'templates/' + hash_values['section'] + '.tpl';
	$central_container.fadeTo(0, 0.2);
	$central_container.load(template_path, function(r,s,x) {
		if (s === 'success') {
			hash_values['section'] === 'index' ? $backer.css('visibility', 'hidden') :
				$backer.css('visibility', 'visible');

			$central_container.fadeTo(500, 1.0);

		} else {
			$central_container.fadeTo(500, 1.0);
		}
	});
        */
       
       
       
}

$(document).ready(function() {
   
    $(window).hashchange(function(){
        ajax_update();
    });
    $(window).hashchange();

   
   $(document).on('mouseenter', '.overflowEllipsis', function(e) {
       $this = $(this);
       $this.css('text-overflow', 'clip');
       $that = $this.find('span.overflowText');
       offset = $this.width() - $that.width();
       if (offset < 0) {
        $that.animate({'margin-left': offset}, {duration: offset * -15, easing: 'linear'});
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
});
