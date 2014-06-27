<%-- 
   Document   : classify
   Created on : May 14, 2012, 10:12:22 AM
   Author     : Luis Salazar <bp.lusv@gmail.com>
--%>

<form id="clForm" action="${submitAction}" method="post">
<c:if test="${!empty symbol}">
    <input id="clSymbol" type="hidden" name="symbol" value="${symbol.id}" />
    <%-- <input id="clName" type="hidden" name="name" value="<c:out value="${symbol.name}" />" />--%>
    <input id="clDocumentName" type="hidden" name="documentName" value="<c:out value="${symbol.document.name}" />" />
    <div id="clCommandPanelWrapper">
        <div id="commandPanel">
            <div>
                <a id="clCancelGroup" class="button"><fmt:message key="cancel" /></a>
                <input id="clSaveSymbol" type="submit" class="button" value="<fmt:message key="save" />" />        
                <h2 id="clTitle" class="overflowEllipsis"><fmt:message key="symbol" /></h2>
            </div>
            <center><label id="mpNewProjectNameLabel"><fmt:message key="name" />:&nbsp;</label> <span style="color: #222;"> <a alt="Si se cambia el nombre, el simbolo no será vinculado dentro del documento" class="tooltip"> <input id="clName" type="text" name="name" value="<c:out value="${symbol.name}" />" /> </a> </span> </center>
            <div id="clSynonymsField">
                <c:set var="symbolSynonyms" value="${symbolFacade.getSynonyms(symbol.id)}" />
                <a id="clLeaveGroup" style="display: ${empty symbolSynonyms ? 'none' : 'inline'}"><fmt:message key="leave group" />&nbsp;(-)</a>
                <a id="clChangeGroup"><fmt:message key="change group" />&nbsp;(+)</a>
                <label id="clSynonymsGroupLabel" class="overflowEllipsis">
                    <fmt:message key="synonyms group" />
                    &nbsp;(
                    <span id="clSynonymsGroup">
                        <c:forEach var="synonym" items="${symbolFacade.getSynonyms(symbol.id)}" varStatus="iter">
                            <a href="#!/classify?sy=${synonym.id}"><c:out value="${synonym.name}" /></a><c:if test="${!iter.last}">,&nbsp;</c:if>
                        </c:forEach>
                    </span>
                    )
                </label>
                <select id="clSynonymsSelect" name="synonym" size="7"></select>
            </div>
        </div>
    </div>
    <div id="clDefinitionTop">
        <div class="left">
            <div class="left">
                <label><fmt:message key="document" />:</label>
                <label id="clCategoryLabel" for="clCategory"><fmt:message key="category" />:</label>
                <label id="clClassificationLabel" for="clClassification"><fmt:message key="classification" />:</label>
            </div>
            <div class="right">
                <h3  <h3id="clDocumentTitle" class="clInfo overflowEllipsis"><c:out value="${symbol.document.name}" /></h3>
                <select id="clCategory" name="category">
                    <c:forEach var="category" items="${categories}" varStatus="iter">
                        <option value="${category.id}" ${symbol.definition.category.id == category.id ? 'selected="selected"' : ''}><fmt:message key="${category.name}" /></option>
                    </c:forEach>
                </select>
                <select id="clClassification" name="classification">
                    <c:forEach var="classification" items="${classifications}">
                        <option value="${classification.id}" ${symbol.definition.classification.id == classification.id ? 'selected="selected"' : ''}><fmt:message key="${classification.name}" /></option>
                    </c:forEach>
                </select>                
            </div>
        </div>
        <div class="right" style="visibility: ${empty log ? 'hidden' : 'visible'}">
            <label><fmt:message key="last edited" />:</label>
            <h3 id="clLogUserName" class="clInfo overflowEllipsis"><c:out value="${log.user.name}" /></h3>
            <h3 id="clLogDate" class="clInfo"><fmt:formatDate value="${log.date}" type="both" dateStyle="MEDIUM" timeZone="GMT-6" /></h3>
        </div>
    </div>
    <div id="clDefinitionBottom">
        <div class="clDefinitionField">
            <label id="clNotionLabel" for="clNotion" class="tab"><fmt:message key="notion" /></label>
            <textarea id="clNotion" class="symbolicEditor" name="notion" maxlength="32767"><c:out value="${symbol.definition.notion}" /></textarea>
        </div>
        <div id="clIntentionFields">
            <div class="clDefinitionField Left">
                <label id="clActualIntentionLabel" for="clActualIntention" class="tab"><fmt:message key="actual intention" /></label>
                <textarea id="clActualIntention" class="symbolicEditor" name="actualIntention" maxlength="32767"><c:out value="${symbol.definition.actualIntention}" /></textarea>
            </div>
            <div class="clDefinitionField Right">
                <label id="clFutureIntentionLabel" for="clFutureIntention" class="tab"><fmt:message key="future intention" /></label>
                <textarea id="clFutureIntention" class="symbolicEditor" name="futureIntention" maxlength="32767"><c:out value="${symbol.definition.futureIntention}" /></textarea>
            </div>
            <div style="clear:both;"></div>
        </div>
        <div class="clDefinitionField">
            <label id="clCommentsLabel" for="clComments" class="tab"><fmt:message key="comments" /></label>
            <div id="clCommentsField">
                <div id="clNewCommentField">
                    <div class="left">
                        <span class="overflowEllipsis"><c:out value="${user.name}" />:</span>
                    </div>
                    <div class="right">
                        <textarea id="clNewComment" class="symbolicEditor" name="newComment" maxlength="32767"></textarea>
                    </div>
                    <div style="clear:both;"></div>
                </div>
                <div id="clCommentsToggle">
                    <a id="clShowComments">&#9660;&nbsp;<fmt:message key="show comments" /></a>
                    <a id="clHideComments">&#9650;&nbsp;<fmt:message key="hide comments" /></a>
                </div>
                <ul id="clComments">
                    <c:forEach var="comment" items="${symbolFacade.getCommentCollection(symbol.id)}" varStatus="iter">
                        <li style="background-color:${iter.index % 2 == 0 ? '#fff' : '#f9f9f9'};">
                            <div class="left">
                                <span class="overflowEllipsis"><c:out value="${comment.user.name}" />:</span>
                                <span><fmt:formatDate value="${comment.date}" type="date" dateStyle="medium" timeZone="GMT-6" /><span>
                            </div>
                            <div class="right">${projectFacade.tagSymbols(comment.content)}</div>
                            <div style="clear:both;"></div>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</c:if>
</form>