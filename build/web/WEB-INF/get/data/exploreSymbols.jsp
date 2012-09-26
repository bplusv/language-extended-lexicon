<%-- 
    Document   : exploreSymbols
    Created on : Aug 9, 2012, 6:57:30 PM
    Author     : lu
--%>

<root>
<symbols>
    <c:forEach var="symbol" items="${symbolFacade.findByFilters(project.id, param.ca, param.cl, param.sy)}" varStatus="iter">
        <symbol id="${symbol.id}">
            <name><![CDATA[${symbol.name}]]></name>
            <category>
                <name><fmt:message key="${symbol.definition.category.name}" /></name>
            </category>
            <classification>
                <name><fmt:message key="${empty symbol.definition.classification.name ? 'n/a' : symbol.definition.classification.name}" /></name>
            </classification>
            <document>
                <name><![CDATA[${symbol.document.name}]]></name>
            </document>
        </symbol>
    </c:forEach>
</symbols>
</root>