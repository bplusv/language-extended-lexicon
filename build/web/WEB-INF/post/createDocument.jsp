<%-- 
    Document   : doSignIn
    Created on : Jun 14, 2012, 7:56:55 PM
    Author     : Luis Salazar <bp.lusv@gmail.com>
--%>

<root>
    <success>${success}</success>
    <message><fmt:message key="create document ${success ? 'success' : 'fail'}" /></message>
    <c:if test="${success}">
        <document id="${document.id}">
            <name>${document.name}</name>
            <project>${document.project.id}</project>
        </document>
    </c:if>
</root>