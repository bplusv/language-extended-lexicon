<%-- 
    Document   : registerUser
    Created on : 09-oct-2012, 15:20:07
    Author     : YANET
--%>

<root>
<success>${success}</success>
<c:if test="${!success}">
    <message><fmt:message key="register fail" /></message>
</c:if>
</root>
