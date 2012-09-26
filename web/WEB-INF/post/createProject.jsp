<%-- 
    Document   : doSignIn
    Created on : Jun 14, 2012, 7:56:55 PM
    Author     : Luis Salazar <bp.lusv@gmail.com>
--%>

<root>
<success>${success}</success>
<message><fmt:message key="create project ${success ? 'success' : 'fail'}" /></message>
<c:if test="${success}">
    <project id="{project.id}">
        <name><![CDATA[${project.name}]]></name>
        <description><![CDATA[${project.description}]]></description>
    </project>
</c:if>
</root>