<%-- 
    Document   : doSignIn
    Created on : Jun 14, 2012, 7:56:55 PM
    Author     : Luis Salazar <bp.lusv@gmail.com>
--%>

<response>
    <success>${success}</success>
    <c:if test="${!success}">
        <message><fmt:message key="sign in fail" /></message>
    </c:if>
</response>