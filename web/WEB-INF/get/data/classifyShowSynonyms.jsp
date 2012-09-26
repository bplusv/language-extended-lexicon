<%-- 
    Document   : classifyShowSynonyms
    Created on : Jul 13, 2012, 1:30:37 PM
    Author     : lu
--%>

<root>
<synonyms>
    <c:forEach items="${projectFacade.getSymbolCollection(project.id)}" var="synonym">
        <synonym id="${synonym.id}">
            <name><![CDATA[${synonym.name}]]></name>
        </synonym>
    </c:forEach>
</synonyms>
</root>