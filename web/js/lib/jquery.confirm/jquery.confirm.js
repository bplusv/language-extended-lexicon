(function($){
    "use strict";
    $.confirm = function(params){
        if($('#jqConfirmOverlay').length){
            return false;
        }
        var buttonHTML = '';
        $.each(params.buttons,function(name,obj){
            buttonHTML += '<a href="#" class="button '+obj['class']+'">'+obj['msg']+'<span></span></a>';
            if(!obj.action){
                obj.action = function(){};
            }
        });
        var markup = [
        '<div id="jqConfirmOverlay">',
        '<div class="confirmBox">',
        '<h1>',params.title,'</h1>',
        '<div class="message">',params.message,'</div>',
        '<div class="confirmButtons">',
        buttonHTML,
        '</div></div></div>'
        ].join('');
        $(markup).hide().appendTo('body').fadeIn(0);
        var buttons = $('#jqConfirmOverlay .button'), i = 0;
        $.each(params.buttons,function(name,obj){
            buttons.eq(i++).click(function(){
                obj.action();
                $.confirm.hide();
                return false;
            });
        });
    }
    
    $.confirm.hide = function(){
        $('#jqConfirmOverlay').fadeOut(0, function(){
            $(this).remove();
        });
    }
})(jQuery);