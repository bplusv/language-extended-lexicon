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

(function() {
    'use strict';
    $(function() {
        $('#ixChooseLanguageEn').on('click', function() {
            controller.changeLanguage('en');
        });
        
        $('#ixChooseLanguageEs').on('click', function(){
            controller.changeLanguage('es');
        });

        $('#siForm').on('submit', function(e){
            e.preventDefault();
            controller.signIn();
        });
        
        $('#ixSignOut').on('click', function() {
            controller.signOut();
        });
        
        $(window).on('dblclick', 'select', function(e) {
            $(this.form).submit();
        });
        
        $(window).on('hashchange', function(){
            controller.changeView(window.location.hash);
        });
        
        $(window).on('mouseenter', '.overflowEllipsis', function() {
            controller.scrollingText.scroll(this);
        });
        $(window).on('mouseleave', '.overflowEllipsis', function() {
            controller.scrollingText.reset(this);
        });

        $(window).trigger('hashchange');
    });
})();