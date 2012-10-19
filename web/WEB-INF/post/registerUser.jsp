<%-- 
    Document   : registerUser
    Created on : 09-oct-2012, 15:20:07
    Author     : YANET
--%>

<c:set var="success" value="${!empty facadeResponse.response}" />
<root>
<success>${success}</success>
<c:if test="${!success}">
    <message><fmt:message key="${facadeResponse.message}" /></message>
</c:if>
</root>
