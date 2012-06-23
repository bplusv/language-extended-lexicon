<%-- 
    Document   : doSignIn
    Created on : Jun 14, 2012, 7:56:55 PM
    Author     : Luis Salazar <bp.lusv@gmail.com>
--%>

<response>
    <success>${success}</success>
    <message><fmt:message key="create project ${success ? 'success' : 'fail'}" /></message>
    <c:if test="${success}">
        <project>
            <name>
                ${project.name}
            </name>
        </project>
    </c:if>
</response>