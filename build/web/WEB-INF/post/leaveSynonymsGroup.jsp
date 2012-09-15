<%-- 
    Document   : leaveSynonymsGroup
    Created on : Aug 30, 2012, 11:27:42 AM
    Author     : lu
--%>

<root>
    <success>${success}</success>
    <message><fmt:message key="leave synonyms group ${success ? 'success' : 'fail'}" /></message>
    <c:if test="${success}">
        <symbol id="${symbol.id}">
            <active>${symbol.active}</active>
            <name><![CDATA[${symbol.name}]]></name>
            <document>${symbol.document}</document>
            <definition>${symbol.definition}</definition>
            <project>${symbol.project}</project>
            <c:set var="log" value="${symbolFacade.getLastLog(symbol.id)}" />
            <log>
                <user id="${log.user.id}">
                    <name><![CDATA[${log.user.name}]]></name>
                </user>
                <date><fmt:formatDate value="${log.date}" type="both" dateStyle="MEDIUM" timeZone="GMT-6" /></date>
            </log>
        </symbol>
    </c:if>
</root>