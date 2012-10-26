<%-- 
    Document   : updateDocumentDescriptors
    Created on : Oct 23, 2012, 12:08:01 PM
    Author     : Luis Salazar <bp.lusv@gmail.com>
--%>

<root>
<success>${success}</success>
<message><fmt:message key="update document descriptors ${success ? 'success' : 'fail'}" /></message>
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