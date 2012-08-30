<%-- 
   Document   : classify
   Created on : May 14, 2012, 10:12:22 AM
   Author     : Luis Salazar <bp.lusv@gmail.com>
--%>

<form id="clForm" action="${submitAction}" method="POST">
    <input id="clSymbol" type="hidden" name="symbol" value="${symbol.id}" />
    <input id="clName" type="hidden" name="name" value="${symbol.name}" />
    <input id="clDocument" type="hidden" name="document" value="${symbol.document.id}" />
    <input id="clDocumentName" type="hidden" name="documentName" value="${symbol.document.name}" />
    <h2 id="clTitle" class="overflowEllipsis"><fmt:message key="symbol" />:&nbsp;<span style="color: #222;">${symbol.name}</span></h2>
    <div id="clSynonymsField">
        <a id="clLeaveGroup"><fmt:message key="leave group" />&nbsp;(-)</a>
        <a id="clChangeGroup"><fmt:message key="change group" />&nbsp;(+)</a>
        <a id="clCancelGroup" class="button"><fmt:message key="cancel" /></a>
        <input id="clSaveGroup" class="button" type="submit" value="<fmt:message key="save" />" />
        <label><fmt:message key="synonyms group" />&nbsp;(</label>
        <span id="clSynonyms" class="overflowEllipsis">
            <c:forEach var="synonym" items="${symbolFacade.getSynonyms(symbol.id)}" varStatus="iter">
                <a href="#!/classify?sy=${synonym.id}">${synonym.name}</a><c:if test="${!iter.last}">,&nbsp;</c:if>
            </c:forEach>
        </span>
        <label>)</label>
        <br />
        <select id="clSynonymsSelect" name="synonym" size="7"></select>
    </div>
    <div id="clDefinitionTop">
        <div>
            <div id="clDefinitionTopLeft">
                <div>
                    <div><label><fmt:message key="document" />:</label></div>
                    <div><h3 id="clDocumentTitle" class="clInfo overflowEllipsis">${symbol.document.name}</h3></div>
                </div>
                <div>
                    <div><label id="clCategoryLabel" for="clCategory"><fmt:message key="category" />:</label></div>
                    <div>
                        <select id="clCategory" name="category">
                            <c:forEach var="category" items="${categories}" varStatus="iter">
                                <option value="${category.id}" ${symbol.definition.category.id == category.id ? 'selected="selected"' : ''}><fmt:message key="${category.name}" /></option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div id="clClassificationField">
                    <div><label id="clClassificationLabel" for="clClassification"><fmt:message key="classification" />:</label></div>
                    <div>
                        <select id="clClassification" name="classification">
                            <c:forEach var="classification" items="${classifications}">
                                <option value="${classification.id}" ${symbol.definition.classification.id == classification.id ? 'selected="selected"' : ''}><fmt:message key="${classification.name}" /></option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
        </div>
        <div>
            <div id="clDefinitionTopRight" style="visibility:${!empty log ? 'visible' : 'hidden'};">
                <label><fmt:message key="last edited" />:</label>
                <h3 id="clLogUserName" class="clInfo overflowEllipsis">${log.user.name}</h3>
                <h3 id="clLogDate" class="clInfo"><fmt:formatDate value="${log.date}" type="both" dateStyle="MEDIUM" timeZone="GMT-6" /></h3>
            </div>
        </div>
    </div>
    <div id="clDefinitionBottom">
        <div class="clDefinitionField">
            <label id="clNotionLabel" for="clNotion" class="tab"><fmt:message key="notion" /></label>
            <textarea id="clNotion" class="symbolicEditor" name="notion" maxlength="32767">${symbol.definition.notion}</textarea>
        </div>
        <div id="clIntentionFields">
            <div class="clDefinitionField Left">
                <label id="clActualIntentionLabel" for="clActualIntention" class="tab"><fmt:message key="actual intention" /></label>
                <textarea id="clActualIntention" class="symbolicEditor" name="actualIntention" maxlength="32767">${symbol.definition.actualIntention}</textarea>
            </div>
            <div class="clDefinitionField Right">
                <label id="clFutureIntentionLabel" for="clFutureIntention" class="tab"><fmt:message key="future intention" /></label>
                <textarea id="clFutureIntention" class="symbolicEditor" name="futureIntention" maxlength="32767">${symbol.definition.futureIntention}</textarea>
            </div>
            <div style="clear:both;"></div>
        </div>
        <div class="clDefinitionField">
            <label id="clCommentsLabel" for="clComments" class="tab"><fmt:message key="comments" /></label>
            <div id="clCommentsField">
                <div id="clNewCommentField">
                    <div class="left">
                        <span class="overflowEllipsis">${user.name}:</span>
                    </div>
                    <div class="right">
                        <textarea id="clNewComment" class="symbolicEditor" name="newComment" maxlength="32767"></textarea>
                    </div>
                    <div style="clear:both;"></div>
                </div>
                <ul id="clComments">
                    <c:set var="projectSymbols" value="${projectFacade.getSymbolCollection(project.id)}" />
                    <c:forEach var="comment" items="${symbolFacade.getCommentCollection(symbol.id)}" varStatus="iter">
                        <li style="background-color:${iter.index % 2 == 0 ? '#fff' : '#f9f9f9'};">
                            <div class="left">
                                <span class="overflowEllipsis">${comment.user.name}:</span>
                                <span><fmt:formatDate value="${comment.date}" type="date" dateStyle="medium" timeZone="GMT-6" /><span>
                            </div>
                            <div class="right">${projectFacade.tagSymbols(comment.content, projectSymbols)}</div>
                            <div style="clear:both;"></div>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
    <input id="clSaveSymbol" type="submit" class="button" value="<fmt:message key="save" />" />
</form>