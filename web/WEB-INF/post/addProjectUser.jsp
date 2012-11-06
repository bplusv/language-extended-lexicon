<%-- 
    Document   : addProjectUser
    Created on : Oct 9, 2012, 11:26:47 AM
    Author     : Luis Salazar <bp.lusv@gmail.com>
--%>

<root>
<success>${success}</success>
<message><fmt:message key="add project user ${success ? 'success' : 'fail'}" /></message>
<c:if test="${success}">
<project id="${projectId}">
    <users>
        <c:forEach var="user" items="${projectFacade.getUserCollection(projectId)}">
            <user id="${user.id}">
                <name><![CDATA[${user.name}]]></name>
            </user>
        </c:forEach>
    </users>
</project>
    </c:if>
</root>
