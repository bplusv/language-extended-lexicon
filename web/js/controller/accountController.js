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

accountController = {};
accountController.changePassword = function() {
    baseController.ajaxRequest('/post/changePassword', function(response) {

        if ($(response).find('success').text() === 'true') {
            $('.acChangePassField input').val('');
            $('#acNewPasswordStrength').text('');
            $('#acNewPasswordStrengthBar').width(0);
        }

    }, $('#acChangePassForm').serialize());
};

accountController.checkPasswordStrength = function() {

    var $passStrength = $('#acNewPasswordStrength');
    var $passStrengthBar = $('#acNewPasswordStrengthBar');
    var newPass = $('#acNewPassword').val();
    if (newPass) {
        var pwdScore = $.pwdStrength(newPass);
        var pwdClass = baseController.getPwdScoreCssClass(pwdScore);
        $passStrength.removeClass();
        $passStrength.addClass(pwdClass);
        $passStrength.text($('#messages').find('.' + pwdClass).text());
        $passStrengthBar.removeClass();
        $passStrengthBar.addClass(pwdClass);
        $passStrengthBar.width($passStrength.width() * pwdScore / 100); 
    } else {
        $passStrength.text('');
        $passStrengthBar.width(0);
    }

};