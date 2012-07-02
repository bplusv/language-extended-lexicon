<%-- 
    Document   : doSignIn
    Created on : Jun 14, 2012, 7:56:55 PM
    Author     : Luis Salazar <bp.lusv@gmail.com>
--%>

<response>
    <success>${success}</success>
    <message><fmt:message key="create symbol ${success ? 'success' : 'fail'}" /></message>
    <c:if test="${success}">
        <symbol id="${symbol.id}">
            <active>${symbol.active}</active>
            <name>${symbol.name}</name>
            <document>${symbol.document}</document>
            <definition>${symbol.definition}</definition>
            <project>${symbol.project}</project>
        </symbol>
    </c:if>
</response>