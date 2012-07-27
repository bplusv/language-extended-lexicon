<%-- 
    Document   : classifyShowSynonyms
    Created on : Jul 13, 2012, 1:30:37 PM
    Author     : lu
--%>

<response>
    <synonyms>
        <c:forEach items="${synonyms}" var="synonym">
            <synonym id="${synonym.id}">
                <name>${synonym.name}</name>
            </synonym>
        </c:forEach>
    </synonyms>
</response>