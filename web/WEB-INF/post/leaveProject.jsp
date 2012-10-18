<%-- 
    Document   : leaveProject
    Created on : Oct 17, 2012, 11:47:01 AM
    Author     : Luis Salazar <bp.lusv@gmail.com>
--%>

<root>
<success>${success}</success>
<c:if test="${!success}">
    <message><fmt:message key="leave project fail" /></message>
</c:if>
<captions>
    <owner><fmt:message key="owner" /></owner>
    <description><fmt:message key="description" /></description>
    <load><fmt:message key="load" /></load>
    <edit><fmt:message key="edit" /></edit>
    <users><fmt:message key="users" /></users>
    <remove><fmt:message key="remove" /></remove>
    <leave><fmt:message key="leave" /></leave>
</captions>
<projects>
    <c:forEach var="project" items="${userFacade.getProjectCollection(user.id)}">
        <project id="${project.id}" isOwner="${project.owner.id == user.id}" isSelected="${sessionScope.project.id == project.id}">
            <name><![CDATA[${project.name}]]></name>
            <owner>
                <name><![CDATA[${project.owner.name}]]></name>
            </owner>
            <description><![CDATA[${project.description}]]></description>
        </project>
    </c:forEach>
</projects>
</root>