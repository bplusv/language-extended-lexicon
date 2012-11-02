<%-- 
    Document   : projectSymbols
    Created on : Aug 13, 2012, 1:10:12 PM
    Author     : lu
--%>

<root>
<symbols>
    <c:forEach var="symbol" items="${projectFacade.getSymbolCollection(project.id)}">
        <symbol id="${symbol.id}">
            <name><![CDATA[${symbol.name}]]></name>
        </symbol>
    </c:forEach>
</symbols>
</root>
