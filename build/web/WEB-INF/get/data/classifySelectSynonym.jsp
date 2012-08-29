<%-- 
    Document   : classifySelectSynonym
    Created on : Jul 13, 2012, 10:03:07 PM
    Author     : lu
--%>

<root>
    <symbol id="${symbol.id}">
        <name>${symbol.name}</name>
        <document id="${symbol.document.id}">
            <name>${symbol.document.name}</name>
        </document>
        <definition>
            <category>${symbol.definition.category.id}</category>
            <classification>${symbol.definition.classification.id}</classification>
            <notion>${symbol.definition.notion}</notion>
            <actualIntention>${symbol.definition.actualIntention}</actualIntention>
            <futureIntention>${symbol.definition.futureIntention}</futureIntention>
            <comments>
                <c:forEach var="comment" items="${symbolFacade.getCommentCollection(symbol.id)}">
                    <comment id="${comment.id}">
                        <content>${comment.content}</content>
                        <date><fmt:formatDate value="${comment.date}" type="date" dateStyle="medium" timeZone="GMT-6" /></date>
                        <user id="${comment.user.id}">
                            <name>${comment.user.name}</name>
                        </user>
                    </comment>
                </c:forEach>
            </comments>
        </definition>
        <c:set var="log" value="${symbolFacade.getLastLog(symbol.id)}" />
        <log>
            <user id="${log.user.id}">
                <name>${log.user.name}</name>
            </user>
            <date><fmt:formatDate value="${log.date}" type="both" dateStyle="MEDIUM" timeZone="GMT-6" /></date>
        </log>
        <synonymsGroup>
            <c:forEach var="synonym" items="${symbolFacade.getSynonymsGroup(symbol.id)}">
                <synonym id="${synonym.id}">
                    <name>${synonym.name}</name>
                </synonym>
            </c:forEach>
        </synonymsGroup>
    </symbol>
</root>