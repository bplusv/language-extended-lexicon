function updatejScrollPaneTextArea(textareaSelector, settings)
{
		var range=$(textareaSelector).getSelection();

		var textareaClone=$('<div style="word-wrap:break-word" />').appendTo($(textareaSelector).parent());
		var copyAttributes=['font-family','font-size','font-weight','letter-spacing','word-spacing','line-height','width'];
		for (var i = 0; i < copyAttributes.length; i++) 
		{
				textareaClone.css(copyAttributes[i], $(textareaSelector).css(copyAttributes[i]));
		}
		textareaClone.html('&nbsp;');
		var heightPerRow=textareaClone.height();

		textareaClone.html($(textareaSelector).val().replace(/\n/g,'<br />'));
		var overallHeight=textareaClone.height();

		$(textareaSelector).attr('rows', Math.round(overallHeight / heightPerRow));
		$(textareaSelector).css('height', 'auto');

		textareaClone.html($(textareaSelector).val().substring(0, range.end).replace(/\n/g,'<br />') + '&nbsp;');
		var cursorPosition=textareaClone.height() - heightPerRow;

		textareaClone.remove();

		var jspAPI=$(textareaSelector).parent().parent().parent();
		var scrollpaneY=jspAPI.data('jsp').getContentPositionY();
		var scrollpaneHeight=$(textareaSelector).parent().parent().parent().height();
		jspAPI.data('jsp').reinitialise(settings);
		if ( (cursorPosition + heightPerRow) >= (scrollpaneY + scrollpaneHeight) )
		{
				jspAPI.data('jsp').scrollToY(scrollpaneY + cursorPosition + heightPerRow - scrollpaneHeight);
		}
		if (cursorPosition <= scrollpaneY)
		{
				jspAPI.data('jsp').scrollToY(cursorPosition);
		}
}

function jScrollPaneTextArea(textareaSelector, settings)
{
		var width=$(textareaSelector).outerWidth();
		var height=$(textareaSelector).outerHeight();
		$(textareaSelector).wrap('<div />');
		$(textareaSelector).parent().css('height', height).css('width', width).css('overflow', 'auto');
		$(textareaSelector).parent().jScrollPane(settings);
		$(textareaSelector).bind('keyup', function(){updatejScrollPaneTextArea(textareaSelector, settings);});
		$(textareaSelector).bind('click', function(){updatejScrollPaneTextArea(textareaSelector, settings);});
		updatejScrollPaneTextArea(textareaSelector, settings);
}