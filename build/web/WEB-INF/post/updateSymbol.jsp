<%-- 
    Document   : doSignIn
    Created on : Jun 14, 2012, 7:56:55 PM
    Author     : Luis Salazar <bp.lusv@gmail.com>
--%>

<root>
    <success>${success}</success>
    <message><fmt:message key="update symbol ${success ? 'success' : 'fail'}" /></message>
    <c:if test="${success}">
        <symbol id="${symbol.id}">
            <active>${symbol.active}</active>
            <name>${symbol.name}</name>
            <document>${symbol.document}</document>
            <definition>${symbol.definition}</definition>
            <project>${symbol.project}</project>
            <c:set var="log" value="${symbolFacade.getLastLog(symbol.id)}" />
            <log>
                <user id="${log.user.id}">
                    <name>${log.user.name}</name>
                </user>
                <date><fmt:formatDate value="${log.date}" type="both" dateStyle="MEDIUM" timeZone="GMT-6" /></date>
            </log>
            <comments>
                <c:forEach var="comment" items="${symbol.definition.commentCollection}">
                    <comment id="${comment.id}">
                        <content>${comment.content}</content>
                        <date><fmt:formatDate value="${comment.date}" type="date" dateStyle="medium" timeZone="GMT-6" /></date>
                        <user id="${comment.user.id}">
                            <name>${comment.user.name}</name>
                        </user>
                    </comment>
                </c:forEach>
            </comments>
        </symbol>
    </c:if>
</root>