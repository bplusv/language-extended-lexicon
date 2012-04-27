<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>LeL</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" href="css/lel.css" rel="stylesheet" media="all"/>
        <meta name="author" content="Yanet Garay Santos,Luis Eduardo Salazar Valles" />
        <meta name="description" content="Léxico Extendido del lenguaje" />
        <meta name="keywords" content="UACJ,LEL" />
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
    </head>
    <body>
        <div id="signInMain">
            <div id="signInLeftColumn">
                <img id="signInLelLogo" src="img/lelLogo.png" />
                <img id="signInBackground" src="img/signInBackground.png" />
            </div>
            <div id="signInRightColumn">
                <form action="" id="signInForm" method="post">
                    <h2>Sign in</h2>
                    <div class="signInField">
                        <label for="username"><strong>Username</strong></label>
                        <input type="text" id="username" name="username" />
                    </div>
                    <div class="signInField">
                        <label for="password"><strong>Password</strong></label>
                        <input type="password" id="password" name="password" />
                    </div>
                    <input type="submit" name="signIn" value="Sign In" id="SignIn" class="button" />
                </form>
            </div>
        </div>
    </body>
</html>
