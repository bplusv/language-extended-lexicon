<%-- 
    Document   : classifySelectSynonym
    Created on : Jul 13, 2012, 10:03:07 PM
    Author     : lu
--%>
<root>
<c:if test="${!empty symbol}">
<symbol id="${symbol.id}">
    <name><![CDATA[${symbol.name}]]></name>
    <document id="${symbol.document.id}">
        <name><![CDATA[${symbol.document.name}]]></name>
    </document>
    <definition>
        <category>${symbol.definition.category.id}</category>
        <classification>${symbol.definition.classification.id}</classification>
        <notion><![CDATA[${symbol.definition.notion}]]></notion>
        <actualIntention><![CDATA[${symbol.definition.actualIntention}]]></actualIntention>
        <futureIntention><![CDATA[${symbol.definition.futureIntention}]]></futureIntention>
        <comments>
            <c:forEach var="comment" items="${symbolFacade.getCommentCollection(symbol.id)}">
                <comment id="${comment.id}">
                    <content><![CDATA[${comment.content}]]></content>
                    <date><fmt:formatDate value="${comment.date}" type="date" dateStyle="medium" timeZone="GMT-6" /></date>
                    <user id="${comment.user.id}">
                        <name><![CDATA[${comment.user.name}]]></name>
                    </user>
                </comment>
            </c:forEach>
        </comments>
    </definition>
    <c:set var="log" value="${symbolFacade.getLastLog(symbol.id)}" />
    <log>
    <user id="${log.user.id}">
        <name><![CDATA[${log.user.name}]]></name>
    </user>
    <date><fmt:formatDate value="${log.date}" type="both" dateStyle="MEDIUM" timeZone="GMT-6" /></date>
    </log>
    <synonymsGroup>
        <c:forEach var="synonym" items="${symbolFacade.getSynonymsGroup(symbol.id)}">
            <synonym id="${synonym.id}">
                <name><![CDATA[${synonym.name}]]></name>
            </synonym>
        </c:forEach>
    </synonymsGroup>
</symbol>
</c:if>
</root>