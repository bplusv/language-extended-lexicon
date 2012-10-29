<%-- 
    Document   : register
    Created on : 08-oct-2012, 12:57:56
    Author     : YANET
--%>


<!DOCTYPE html>
<html>
    <head>
        <title>LeL</title>
        <meta http-equiv="Content-Type" content="text/html;" charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="css/base.css" media="all" charset="UTF-8" />
        <link rel="icon" type="image/ico" href="img/favicon.ico">
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
        <script src="js/register.js" type="text/javascript" charset="UTF-8" ></script>
        
    </head>
    <body>
        <h3 id="notification"></h3>
        <img id="ajaxLoader" src="img/ajaxLoader.gif" />
        <form id="reForm" action="/post/registerUser" method="POST">
            Username: <input type="text" name="username"><br>
            Password: <input type="password" name="password"><br>
            Confirm password: <input type="password" name="passwordConfirmation"><br>
          <input type="submit" value="Submit">
        </form>
    </body>
</html>
