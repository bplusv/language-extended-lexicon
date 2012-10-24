<%-- 
    Document   : removeDocument
    Created on : Oct 23, 2012, 12:07:24 PM
    Author     : Luis Salazar <bp.lusv@gmail.com>
--%>

<root>
<success>${success}</success>
<c:if test="${!success}">
    <message><fmt:message key="remove document fail" /></message>
</c:if>
<c:if test="${success}">
    <documents>
        <c:forEach var="document" items="${projectFacade.getDocumentCollection(project.id)}">
            <document id="${document.id}" isSelected="${sessionScope.document.id == document.id}">
                <name><![CDATA[${document.name}]]></name>
            </document>
        </c:forEach>
    </documents>
</c:if>
</root>
