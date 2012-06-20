<%-- 
    Document   : index
    Created on : May 8, 2012, 7:03:11 AM
    Author     : Luis Salazar <bp.lusv@gmail.com>
--%>

<% if (session.getAttribute("user") == null) response.sendRedirect("signIn"); %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>LeL</title>
        <meta http-equiv="Content-Type" content="text/html;" charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/base.css" />" media="all" charset="UTF-8" />
        <meta name="author" content="Yanet Garay Santos,Luis Eduardo Salazar Valles" />
        <meta name="description" content="Léxico Extendido del lenguaje" />
        <meta name="keywords" content="UACJ,LEL" />
        <!--[if lt IE 9]><script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript" charset="UTF-8"></script>
        <script src="<c:url value="/js/controller.js" />" type="text/javascript" charset="UTF-8"></script>
    </head>
    <body>
        <h3 id="notification"></h3>
        <header>
            <div id="userMenu">
                <form id="signOutForm" class="signOut" action="<c:url value="/doSignOut" />" method="POST" onsubmit="return controller('doSignOut', $(this).serialize());"></form>
                <div class="downArrow"></div>
                <span class="user overflowEllipsis">${user.name}</span>
                <div style="clear:both"></div>
                <ul class="popUp">
                    <li><a href="#!nav=loadProject"><fmt:message key="load project" /></a></li>
                    <li>
                        <span class="lang"><fmt:message key="language" /></span>
                        <div class="leftArrow"></div>
                        <ul class="langPopUp">
                            <%-- When user hasn't explicitly set language,
                                    render toggle according to browser's preferred locale --%>
                            <c:set var="lang" value="${empty sessionScope['javax.servlet.jsp.jstl.fmt.locale.session'] ? pageContext.request.locale.language : sessionScope['javax.servlet.jsp.jstl.fmt.locale.session']}" />
                            
                            <%-- language selection widget --%>
                            <c:choose>
                                <c:when test="${lang eq 'en'}">
                                    <li>
                                        <span>English</span>
                                        <div class="circle"></div>
                                    </li>
                                    <li>
                                        <a href="<c:url value="/chooseLanguage"><c:param name="language" value="es"/></c:url>">Español</a>
                                    </li>
                                </c:when>
                                <c:when test="${lang eq 'es'}">
                                    <li>
                                        <a href="<c:url value="/chooseLanguage"><c:param name="language" value="en"/></c:url>">English</a>
                                    </li>
                                    <li>
                                        <span>Español</span>
                                        <div class="circle"></div>
                                    </li>
                                </c:when>
                            </c:choose>
                        </ul>
                    </li>
                    <li><a href="javascript:;" onclick="$('#signOutForm').submit();"><fmt:message key="sign out" /></a></li>
                </ul>
            </div>
            <img id="headerLogo" src="img/lelLogo.png" alt="LeL logo" />
            <div id="projectTitle" style="display: ${empty project ? 'none' : 'block'}}">
                <h3 class="overflowEllipsis"><fmt:message key="project" /> - <span id="projectName">${project.name}</span></h3>
            </div>
        </header>
        <nav class="tabs">
            <a href="#!nav=explore" id="exploreTab"><fmt:message key="explore" /></a>
            <a href="#!nav=document" id="documentTab"><fmt:message key="document" /></a>
        </nav>
        <div id="central"></div>
        <footer></footer>
    </body>
</html>