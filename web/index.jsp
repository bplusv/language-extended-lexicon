<%-- 
    Document   : index
    Created on : May 8, 2012, 7:03:11 AM
    Author     : Luis Salazar <bp.lusv@gmail.com>
--%>

<%
    if (session.getAttribute("user") == null) {
        response.sendRedirect("/lel/signIn");
    }
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
        <link rel="stylesheet" href="js/lib/codemirror-2.34/lib/codemirror.css">
        <link rel="stylesheet" type="text/css" href="js/lib/jquery.confirm/jquery.confirm.css" media="all" charset="UTF-8" />
        <link rel="stylesheet" type="text/css" href="css/base.css" media="all" charset="UTF-8" />
        <link rel="stylesheet" type="text/css" href="css/index.css" media="all" charset="UTF-8" />
        <link rel="stylesheet" type="text/css" href="css/account.css" media="all" charset="UTF-8" />
        <link rel="stylesheet" type="text/css" href="css/classify.css" media="all" charset="UTF-8" />
        <link rel="stylesheet" type="text/css" href="css/document.css" media="all" charset="UTF-8" />
        <link rel="stylesheet" type="text/css" href="css/explore.css" media="all" charset="UTF-8" />
        <link rel="stylesheet" type="text/css" href="css/manageDocuments.css" media="all" charset="UTF-8" />
        <link rel="stylesheet" type="text/css" href="css/manageProjects.css" media="all" charset="UTF-8" />
        <link rel="stylesheet" type="text/css" href="css/manageProjectUsers.css" media="all" charset="UTF-8" />
        <meta name="author" content="Yanet Garay Santos,Luis Eduardo Salazar Valles" />
        <meta name="description" content="Léxico Extendido del lenguaje" />
        <meta name="keywords" content="UACJ,LEL" />
        <!--[if lt IE 9]><script src="js/lib/html5-shim.js" type="text/javascript" charset="UTF-8"></script><![endif]-->
        <script src="js/lib/codemirror-2.34/lib/codemirror.js" type="text/javascript" charset="UTF-8"></script>
        <script src="js/lib/jquery-1.7.2.min.js" type="text/javascript" charset="UTF-8"></script>
        <script src="js/lib/jquery.confirm/jquery.confirm.js" type="text/javascript" charset="UTF-8"></script>
        <script src="js/lib/jquery.pwdStrength.js" type="text/javascript" charset="UTF-8"></script>
        <script src="js/controller.js" type="text/javascript" charset="UTF-8"></script>
        <script src="js/base.js" type="text/javascript" charset="UTF-8"></script>
        <script src="js/account.js" type="text/javascript" charset="UTF-8"></script>
        <script src="js/classify.js" type="text/javascript" charset="UTF-8"></script>
        <script src="js/document.js" type="text/javascript" charset="UTF-8"></script>
        <script src="js/explore.js" type="text/javascript" charset="UTF-8"></script>
        <script src="js/manageProjects.js" type="text/javascript" charset="UTF-8"></script>
        <script src="js/manageDocuments.js" type="text/javascript" charset="UTF-8"></script>
    </head>
    <body>
        <h3 id="notification"></h3>
        <img id="ajaxLoader" src="img/ajaxLoader.gif" />
        <header id="ixHeader">
            <a id="ixHeaderLogo" href="#!/manageProjects">
                <img src="img/headerLogo.png" />
            </a>
            <div id="ixUserMenu">
                <div class="downArrow"></div>
                <span class="user overflowEllipsis"><c:out value="${user.name}" /></span>
                <div style="clear:both"></div>
                <ul class="popUp">
                    <li><a href="#!/manageProjects"><fmt:message key="projects" /></a></li>
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
                                        <a id="ixChooseLanguageEs" href="javascript:;">Español</a>
                                    </li>
                                </c:when>
                                <c:when test="${lang eq 'es'}">
                                    <li>
                                        <a id="ixChooseLanguageEn" href="javascript:;">English</a>
                                    </li>
                                    <li>
                                        <span>Español</span>
                                        <div class="circle"></div>
                                    </li>
                                </c:when>
                            </c:choose>
                        </ul>
                    </li>
                    <li><a href="#!/account"><fmt:message key="account" /></a></li>
                    <li><a id="ixSignOut" href="javascript:;" ><fmt:message key="sign out" /></a></li>
                </ul>
            </div>
            <div id="ixProjectTitle" style="display: ${empty project ? 'none' : 'block'}}">
                <h3 class="overflowEllipsis"><fmt:message key="project" /> - <span id="ixProjectName"><c:out value="${project.name}" /></span></h3>
            </div>
        </header>
        <nav class="tabs">
            <a href="#!/explore" id="exploreTab"><fmt:message key="explore" /></a>
            <a href="#!/document" id="documentTab"><fmt:message key="document" /></a>
        </nav>
        <div id="central"></div>
        <footer id="ixFooter">
            <img id="ixFooterBackground" src="img/footerBackground.png" />
        </footer>
        <div id="messages">
            <span class="cancel"><fmt:message key="cancel" /></span>
            <span class="remove"><fmt:message key="remove" /></span>
            <span class="removeSymbolConfirmation"><fmt:message key="remove symbol confirmation" /></span>
            <span class="removeSymbolConfirmationTitle"><fmt:message key="remove symbol confirmation title" /></span>
            <span class="networkFail"><fmt:message key="network fail" /></span>
        </div>
    </body>
</html>