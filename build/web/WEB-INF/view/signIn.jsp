<%-- 
   Document   : classify
   Created on : May 14, 2012, 10:12:22 AM
   Author     : Luis Salazar <bp.lusv@gmail.com>
--%>

<% if (session.getAttribute("user") != null) response.sendRedirect("/lel"); %>

<!DOCTYPE html>
<html>
    <head>
        <title>LeL</title>
        <meta http-equiv="Content-Type" content="text/html;" charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/base.css" />" media="all" charset="UTF-8" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/signIn.css" />" media="all" charset="UTF-8" />
        <meta name="author" content="Yanet Garay Santos,Luis Eduardo Salazar Valles" />
        <meta name="description" content="Léxico Extendido del lenguaje" />
        <meta name="keywords" content="UACJ,LEL" />
         <!--[if lt IE 9]><script src="/lel/js/lib/html5-shim.js" type="text/javascript" charset="UTF-8"></script><![endif]-->
        <script src="/lel/js/lib/jquery-1.7.2.min.js" type="text/javascript" charset="UTF-8"></script>
        <script src="<c:url value="/js/base.js" />" type="text/javascript" charset="UTF-8" ></script>
        <script src="<c:url value="/js/controller.js" />" type="text/javascript" charset="UTF-8" ></script>
    </head>
    <body>
        <span id="networkFail"><fmt:message key="network fail" /></span>
        <h3 id="notification"></h3>
        <img id="ajaxLoader" src="/lel/img/ajaxLoader.gif" />
        <div id="siCentral">
            <div id="siLeftSide">
                <img id="siLelLogo" src="/lel/img/signInLogo.png" />
            </div>
            <div id="siRightSide">
                <form id="siForm" action="<c:url value="/do/signIn" />" method="POST">
                    <h2 id="siAd"><fmt:message key="sign in" /></h2>
                    <div class="siField">
                        <label id="siUsernameLabel" for="siUsername"><strong><fmt:message key="username" /></strong></label>
                        <input id="siUsername" type="text" name="username" maxlength="50"  />
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
    </body>
</html>