<%-- 
    Document   : account
    Created on : Oct 1, 2012, 1:06:57 PM
    Author     : Luis Salazar <bp.lusv@gmail.com>
--%>

<form id="acChangePassForm" action="/post/changePassword" method="POST">
    <h2 id="acChangePassAd"><fmt:message key="change password" /></h2>
    <div class="acChangePassField">
        <label id="acCurrentPasswordLabel" for="acCurrentPassword"><strong><fmt:message key="current password" /></strong></label>
        <input id="acCurrentPassword" name="currentPassword" type="password" maxlength="255" autofocus />
    </div>
    <div class="acChangePassField">
        <label id="acNewPasswordLabel" for="acNewPassword"><strong><fmt:message key="new password" /></strong></label>
        <input id="acNewPassword" name="newPassword" type="password" maxlength="255" />
    </div>
    <div class="acChangePassField">
        <label id="acConfirmNewPasswordLabel" for="acConfirmNewPassword"><strong><fmt:message key="confirm new password" /></strong></label>
        <input id="acConfirmNewPassword" name="confirmNewPassword" type="password" maxlength="255" />
    </div>
    <input id="acChangePassSave" type="submit" class="button" value="<fmt:message key="save" />" />
</form>