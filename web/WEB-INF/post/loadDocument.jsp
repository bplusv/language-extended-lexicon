<%-- 
    Document   : doSignIn
    Created on : Jun 14, 2012, 7:56:55 PM
    Author     : Luis Salazar <bp.lusv@gmail.com>
--%>

<root>
<success>${success}</success>
<c:if test="${!success}">
    <message><fmt:message key="load document fail" /></message>
</c:if>
<c:if test="${success}">
    <document id="${document.id}">
        <name><![CDATA[${document.name}]]></name>
        <project>${document.project.id}</project>
    </document>
</c:if>
</root>