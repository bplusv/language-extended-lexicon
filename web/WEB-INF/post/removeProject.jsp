<%-- 
    Document   : removeProject
    Created on : Oct 17, 2012, 1:08:59 PM
    Author     : Luis Salazar <bp.lusv@gmail.com>
--%>

<root>
<success>${success}</success>
<c:if test="${!success}">
    <message><fmt:message key="remove project fail" /></message>
</c:if>
<projects id="${projectId}">
    <c:forEach var="user" items="${projectFacade.getUserCollection(projectId)}">
        <project id="${project.id}">
            <name><![CDATA[${project.name}]]></name>
        </project>
    </c:forEach>
</projects>
</root>