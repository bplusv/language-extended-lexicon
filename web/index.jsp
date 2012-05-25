<%-- 
    Document   : index
    Created on : May 8, 2012, 7:03:11 AM
    Author     : Luis Salazar <bp.lusv@gmail.com>
--%>

<% if (session.getAttribute("user") != null) response.sendRedirect("explore"); %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>LeL</title>
        <meta http-equiv="Content-Type" content="text/html;" charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/base.css" />" media="all" charset="UTF-8" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/index.css" />" media="all" charset="UTF-8" />
        <meta name="author" content="Yanet Garay Santos,Luis Eduardo Salazar Valles" />
        <meta name="description" content="Léxico Extendido del lenguaje" />
        <meta name="keywords" content="UACJ,LEL" />
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript" charset="UTF-8" ></script>
        <script src="<c:url value="/js/base.js" />" type="text/javascript" charset="UTF-8" ></script>
    </head>
    <body>
        <c:choose>
            <c:when test="${requestScope.signInError == true}">
                <h3 class="notification error">Wrong username or password, please try again.</h3>
            </c:when>
        </c:choose>
        <c:choose>
            <c:when test="${requestScope.sessionTimedOut == true}">
                <h3 class="notification error">Your session timed out, please sign in again.</h3>
            </c:when>
        </c:choose>
        <div id="center">
            <div id="leftSide">
                <img id="lelLogo" src="img/lelLogo.png" />
                <img id="bubblesBackground" src="img/signInBackground.png" />
            </div>
            <div id="rightSide">
                <form action="<c:url value="/doSignIn" />" id="signInForm" method="post">
                    <h2 id="signInAd">Sign in</h2>
                    <div class="signInField">
                        <label for="username"><strong>Username</strong></label>
                        <input type="text" maxlength="50" id="username" name="username" />
                    </div>
                    <div class="signInField">
                        <label for="password"><strong>Password</strong></label>
                        <input type="password" maxlength="255" id="password" name="password" />
                    </div>
                    <input type="submit" name="signIn" value="Sign In" id="SignIn" class="button" />
                </form>
            </div>
            <div style="clear:both"></div>
        </div>
    </body>
</html>
