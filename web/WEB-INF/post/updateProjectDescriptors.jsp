<%-- 
    Document   : updateProjectDescriptors
    Created on : Oct 22, 2012, 10:38:54 AM
    Author     : Luis Salazar <bp.lusv@gmail.com>
--%>


<root>
<success>${success}</success>
<message><fmt:message key="update project descriptors ${success ? 'success' : 'fail'}" /></message>
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