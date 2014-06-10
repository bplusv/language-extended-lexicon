<%-- 
    Document   : 404
    Created on : Jun 23, 2012, 9:49:47 AM
    Author     : Luis Salazar <bp.lusv@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>kDeL</title>
        <style type="text/css">
            #central {
                border: none;
                margin-top: 50px;
                text-align: center;
            }
            #error {
                color: #CA322D;
                font: 20px 'Century Gothic', sans-serif;
                margin-bottom: 50px; 
            }
        </style>
        <link rel="icon" type="image/ico" href="img/favicon.ico">
        <meta name="author" content="Yanet Garay Santos,Luis Eduardo Salazar Valles" />
        <meta name="description" content="Léxico Extendido del lenguaje" />
        <meta name="keywords" content="UACJ,LEL" />
        <!--[if lt IE 9]>
            <script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
    </head>
    <body>
        <div id="central">
            <h1 id="error"><fmt:message key="error page not found" /></h1>
            <img src="/lel/img/error404.png" />
        </div>
    </body>
</html>
