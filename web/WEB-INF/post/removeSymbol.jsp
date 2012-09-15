<%-- 
    Document   : removeSymbol
    Created on : Aug 6, 2012, 2:10:26 PM
    Author     : lu
--%>

<root>
    <success>${success}</success>
    <c:if test="${not success}">
        <message><fmt:message key="remove symbol fail" /></message>
    </c:if>
</root>