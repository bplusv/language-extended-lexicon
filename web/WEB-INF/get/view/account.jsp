<%-- 
    Document   : account
    Created on : Oct 1, 2012, 1:06:57 PM
    Author     : Luis Salazar <bp.lusv@gmail.com>
--%>

<h3 id="acChangePassAd"><fmt:message key="change password" /></h3>
<form id="acChangePassForm" action="/post/changePassword" method="post">
    <div class="acChangePassField">
        <label id="acCurrentPasswordLabel" for="acCurrentPassword"><fmt:message key="current password" /></label>
        <input id="acCurrentPassword" name="currentPassword" type="password" maxlength="255" autofocus />
    </div>
    <div class="acChangePassField">
        <label id="acNewPasswordLabel" for="acNewPassword"><fmt:message key="new password" /></label>
        <input id="acNewPassword" name="newPassword" type="password" maxlength="255" />
        <label id="acNewPasswordStrength"></label>
        <div id="acNewPasswordStrengthBar"></div>
    </div>
    <div class="acChangePassField">
        <label id="acConfirmNewPasswordLabel" for="acConfirmNewPassword"><fmt:message key="confirm new password" /></label>
        <input id="acConfirmNewPassword" name="confirmNewPassword" type="password" maxlength="255" />
    </div>
    <input id="acChangePassSave" type="submit" class="button" value="<fmt:message key="save" />" />
</form>