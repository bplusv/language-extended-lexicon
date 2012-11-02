<%-- 
    Document   : classifyShowSymbols
    Created on : Jul 13, 2012, 1:30:37 PM
    Author     : lu
--%>

<root>
<symbols>
    <c:forEach items="${projectFacade.getSymbolCollection(project.id)}" var="synonym">
        <symbol id="${synonym.id}">
            <name><![CDATA[${synonym.name}]]></name>
        </symbol>
    </c:forEach>
</symbols>
</root>