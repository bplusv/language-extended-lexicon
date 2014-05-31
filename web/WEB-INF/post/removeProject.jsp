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
<c:if test="${success}">
    <projects>
        <c:forEach var="project" items="${userAccessManager.getUserAccesibleProjects(user.id)}">
            <project id="${project.id}" isOwner="${project.owner.id == user.id}" isSelected="${sessionScope.project.id == project.id}">
                <name><![CDATA[${project.name}]]></name>
                <owner>
                    <name><![CDATA[${project.owner.name}]]></name>
                </owner>
                <description><![CDATA[${project.description}]]></description>
            </project>
        </c:forEach>
    </projects>
</c:if>
</root>