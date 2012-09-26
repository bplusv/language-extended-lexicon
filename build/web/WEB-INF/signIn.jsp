<%-- 
   Document   : classify
   Created on : May 14, 2012, 10:12:22 AM
   Author     : Luis Salazar <bp.lusv@gmail.com>
--%>

<%
    if (session.getAttribute("user") != null) {
        response.sendRedirect("/lel");
    }%>

<!DOCTYPE html>
<html>
    <head>
        <title>LeL</title>
        <meta http-equiv="Content-Type" content="text/html;" charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="css/base.css" media="all" charset="UTF-8" />
        <link rel="stylesheet" href="js/lib/codemirror-2.34/lib/codemirror.css">
        <link rel="stylesheet" type="text/css" href="css/signIn.css" media="all" charset="UTF-8" />
        <meta name="author" content="Yanet Garay Santos,Luis Eduardo Salazar Valles" />
        <meta name="description" content="Léxico Extendido del lenguaje" />
        <meta name="keywords" content="UACJ,LEL" />
        <!--[if lt IE 9]><script src="js/lib/html5-shim.js" type="text/javascript" charset="UTF-8"></script><![endif]-->
        <script src="js/lib/codemirror-2.34/lib/codemirror.js" type="text/javascript" charset="UTF-8"></script>
        <script src="js/lib/jquery-1.7.2.min.js" type="text/javascript" charset="UTF-8"></script>
        <script src="js/controller.js" type="text/javascript" charset="UTF-8" ></script>
        <script src="js/base.js" type="text/javascript" charset="UTF-8" ></script>
    </head>
    <body>
        <h3 id="notification"></h3>
        <img id="ajaxLoader" src="img/ajaxLoader.gif" />
        <div id="siCentral">
            <div id="siLeftSide">
                <img id="siLelLogo" src="img/signInLogo.png" />
            </div>
            <div id="siRightSide">
                <form id="siForm" action="/post/signIn" method="POST">
                    <h2 id="siAd"><fmt:message key="sign in" /></h2>
                    <div class="siField">
                        <label id="siUsernameLabel" for="siUsername"><strong><fmt:message key="username" /></strong></label>
                        <input id="siUsername" type="text" name="username" maxlength="50" autofocus />
                    </div>
                    <div class="siField">
                        <label for="siPassword" id="siPasswordLabel"><strong><fmt:message key="password" /></strong></label>
                        <input type="password" maxlength="255" id="siPassword" name="password" />
                    </div>
                    <input id="siDoSignIn" type="submit" class="button" value="<fmt:message key="sign in" />" />
                </form>
            </div>
            <div style="clear:both"></div>
        </div>
        <div id="messages">
            <span id="ixNetworkFail"><fmt:message key="network fail" /></span>
        </div>
    </body>
</html>