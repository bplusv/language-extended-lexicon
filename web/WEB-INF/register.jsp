<%-- 
    Document   : register
    Created on : 08-oct-2012, 12:57:56
    Author     : YANET
--%>

<%
    if (session.getAttribute("user") != null) {
        response.sendRedirect("/lel");
}%>

<!DOCTYPE html>
<html>
    <head>
        <title>LeL</title>
        <title>LeL</title>
        <meta http-equiv="Content-Type" content="text/html;" charset="UTF-8">
        <link rel="icon" type="image/ico" href="img/favicon.ico">
        <link rel="stylesheet" type="text/css" href="css/base.css" media="all" charset="UTF-8" />
        <link rel="stylesheet" href="js/lib/codemirror-2.35/lib/codemirror.css">
        <link rel="stylesheet" type="text/css" href="css/register.css" media="all" charset="UTF-8" />
        <meta name="author" content="Yanet Garay Santos,Luis Eduardo Salazar Valles" />
        <meta name="description" content="Léxico Extendido del lenguaje" />
        <meta name="keywords" content="UACJ,LEL" />
        <!--[if lt IE 9]><script src="js/lib/html5-shim.js" type="text/javascript" charset="UTF-8"></script><![endif]-->
        <script src="js/lib/codemirror-2.35/lib/codemirror.js" type="text/javascript" charset="UTF-8"></script>
        <script src="js/lib/jquery-1.7.2.min.js" type="text/javascript" charset="UTF-8"></script>
        <script src="js/lib/jquery.pwdStrength.js" type="text/javascript" charset="UTF-8"></script>
        <script src="js/controller/baseController.js" type="text/javascript" charset="UTF-8" ></script>
        <script src="js/controller/registerController.js" type="text/javascript" charset="UTF-8" ></script>
        <script src="js/events/base.js" type="text/javascript" charset="UTF-8" ></script>
        <script src="js/events/register.js" type="text/javascript" charset="UTF-8" ></script>
    </head>
    <body>
        <h3 id="notification"></h3>
        <img id="ajaxLoader" src="img/ajaxLoader.gif" />
        <header id="reHeader">
            <a id="reHeaderLogo" href="signIn">
                <img src="img/headerLogo.png" />
            </a>
        </header>
        <div id="reCentral">
            <form id="reForm" action="/post/registerUser" method="POST">
                <h2 id="reAd"><fmt:message key="sign up" /></h2>
                <div class="reField">
                    <label for="reUsername"><fmt:message key="username" /></label>
                    <input id="reUsername" name="username" type="text" maxlength="255" />
                </div>
                <div class="reField">
                    <label for="rePassword"><fmt:message key="password" /></label>
                    <input id="rePassword" type="password" name="password" maxlenght="255" />
                    <label id="rePasswordStrength"></label>
                    <div id="rePasswordStrengthBar"></div>
                </div>
                <div class="reField">
                    <label for="reConfirmPassword"><fmt:message key="confirm password" /></label>
                    <input id="reConfirmPassword" type="password" name="passwordConfirmation" />
                </div>
                    <input id="reRegister" class="button" type="submit" value="<fmt:message key="sign up" />">
            </form>
        </div>
        <footer id="ixFooter">
            <img id="ixFooterBackground" src="img/footerBackground.png" />
        </footer>
        <div id="messages">
            <span class="networkFail"><fmt:message key="network fail" /></span>
            <span class="very_secure"><fmt:message key="very secure" /></span>
            <span class="secure"><fmt:message key="secure" /></span>
            <span class="very_strong"><fmt:message key="very strong" /></span>
            <span class="strong"><fmt:message key="strong" /></span>
            <span class="average"><fmt:message key="average" /></span>
            <span class="weak"><fmt:message key="weak" /></span>
            <span class="very_weak"><fmt:message key="very weak" /></span>
        </div>
    </body>
</html>
