<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <table>
            <c:forEach var="concept" items="${concepts}">
                <tr>
                    <td>${concept.active}</td>
                    <td>${concept.name}</td>
                  
                </tr>
                
            </c:forEach>
                 <c:forEach var="docdef" items="${documents}">
                <tr style=" color : #F00">
                    <td>${docdef.name}</td>
                                  
                </tr>
                
            </c:forEach>
                
        </table>
    </body>
</html>
