<%-- 
    Document   : removeProjectUser
    Created on : Oct 9, 2012, 1:39:13 PM
    Author     : lu
--%>

<root>
<success>${success}</success>
<c:if test="${!success}">
    <message><fmt:message key="remove project user fail" /></message>
</c:if>
<project id="${projectId}">
    <users>
        <c:forEach var="user" items="${projectFacade.getUserCollection(projectId)}">
            <user id="${user.id}">
                <name><![CDATA[${user.name}]]></name>
            </user>
        </c:forEach>
    </users>
</project>
</root>