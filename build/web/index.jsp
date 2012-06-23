<%-- 
    Document   : index
    Created on : May 8, 2012, 7:03:11 AM
    Author     : Luis Salazar <bp.lusv@gmail.com>
--%>

<% if (session.getAttribute("user") == null) response.sendRedirect("/lel/signIn"); 
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", -1);
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>LeL</title>
        <meta http-equiv="Content-Type" content="text/html;" charset="UTF-8" />
        <meta http-equiv="pragma" content="no-cache" /> 
        <meta http-equiv="expires" content="-1" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/base.css" />" media="all" charset="UTF-8" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/index.css" />" media="all" charset="UTF-8" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/classify.css" />" media="all" charset="UTF-8" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/document.css" />" media="all" charset="UTF-8" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/explore.css" />" media="all" charset="UTF-8" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/loadDocument.css" />" media="all" charset="UTF-8" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/loadProject.css" />" media="all" charset="UTF-8" />
        <meta name="author" content="Yanet Garay Santos,Luis Eduardo Salazar Valles" />
        <meta name="description" content="Léxico Extendido del lenguaje" />
        <meta name="keywords" content="UACJ,LEL" />
        <!--[if lt IE 9]><script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript" charset="UTF-8"></script>
        <script src="<c:url value="/js/base.js" />" type="text/javascript" charset="UTF-8"></script>
        <script src="<c:url value="/js/controller.js" />" type="text/javascript" charset="UTF-8"></script>
        <script src="<c:url value="/js/classify.js" />" type="text/javascript" charset="UTF-8"></script>
        <script src="<c:url value="/js/document.js" />" type="text/javascript" charset="UTF-8"></script>
    </head>
    <body>
        <span id="networkFail"><fmt:message key="network fail" /></span>
        <h3 id="notification"></h3>
        <header id="ixHeader">
            <img id="ixHeaderLogo" src="/lel/img/headerLogo.png" />
            <div id="ixUserMenu">
                <form id="ixSignOutForm" class="signOut" action="<c:url value="/ajax/doSignOut" />" method="POST"></form>
                <form id="ixChooseLanguageForm" action="<c:url value="/ajax/doChooseLanguage" />" method="POST">
                    <input id="ixLanguage" type="hidden" name="language" />
                </form>
                <div class="downArrow"></div>
                <span class="user overflowEllipsis">${user.name}</span>
                <div style="clear:both"></div>
                <ul class="popUp">
                    <li><a href="#!/loadProject"><fmt:message key="load project" /></a></li>
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
                                        <a href="javascript:;" onclick="$($('#ixLanguage').val('es').prop('form')).submit();">Español</a>
                                    </li>
                                </c:when>
                                <c:when test="${lang eq 'es'}">
                                    <li>
                                        <a href="javascript:;" onclick="$($('#ixLanguage').val('en').prop('form')).submit();">English</a>
                                    </li>
                                    <li>
                                        <span>Español</span>
                                        <div class="circle"></div>
                                    </li>
                                </c:when>
                            </c:choose>
                        </ul>
                    </li>
                    <li><a href="javascript:;" onclick="$('#ixSignOutForm').submit();"><fmt:message key="sign out" /></a></li>
                </ul>
            </div>
            <div id="ixProjectTitle" style="display: ${empty project ? 'none' : 'block'}}">
                <h3 class="overflowEllipsis"><fmt:message key="project" /> - <span id="ixProjectName">${project.name}</span></h3>
            </div>
        </header>
        <nav class="tabs">
            <a href="#!/explore" id="exploreTab"><fmt:message key="explore" /></a>
            <a href="#!/document" id="documentTab"><fmt:message key="document" /></a>
        </nav>
        <div id="central"></div>
        <footer id="ixFooter">
            <img id="ixFooterBackground" src="/lel/img/footerBackground.png" />
        </footer>
    </body>
</html>