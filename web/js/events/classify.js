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

(function(){
    'use strict';
    $(function() {
        $(window).on('change', '#clCategory', function() {
            classifyController.updateInterface();
        });

        $(window).on('change', '#clSynonymsSelect', function() {
            classifyController.selectSynonym($(this).val());
        });

        $(window).on('click', '#clCancelGroup', function() {
            classifyController.cancelSelectSynonym();
        });
        
        $(window).on('click', '#clChangeGroup', function() {
            classifyController.showPossibleSynonyms();
        });

        $(window).on('click', '#clLeaveGroup', function() {
            classifyController.leaveSynonymsGroup();
        });
        
        $(window).on('click', '#clShowComments', function() {
            classifyController.showComments();
        });
        
        $(window).on('click', '#clHideComments', function() {
            classifyController.hideComments();
        });
        
        $(window).on('submit', '#clForm', function(e) {
            e.preventDefault();
            switch ($('#clForm').attr('action')) {
                case '/post/createSymbol':
                    classifyController.createSymbol();
                    break;
                case '/post/updateSymbol':
                    classifyController.updateSymbol();
                    break;
            }
        });
    });
})();