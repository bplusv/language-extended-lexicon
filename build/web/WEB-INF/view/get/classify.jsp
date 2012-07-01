 <%-- 
    Document   : classify
    Created on : May 14, 2012, 10:12:22 AM
    Author     : Luis Salazar <bp.lusv@gmail.com>
--%>

            <form id="clForm" action="<c:url value="${submitAction}" />" method="POST">
                <input id="clSymbol" type="hidden" name="symbol" value="${symbol.id}" />
                <input id="clName" type="hidden" name="name" value="${symbol.name}" />
                <input id="clDocument" type="hidden" name="document" value="${symbol.document.id}" />
                <h2 id="clTitle" class="overflowEllipsis"><fmt:message key="symbol" />:&nbsp;<span style="color: #222;">${symbol.name}</span></h2>
                <div id="clSynonyms">
                    <label>Synonyms:&nbsp;</label>
                    <c:forEach var="synonym" items="${symbolFacade.findSynonyms(symbol.id)}" varStatus="iter">
                        <a href="#!/classify?sy=${synonym.id}">${synonym.name}</a><c:if test="${!iter.last}">,&nbsp;</c:if>
                    </c:forEach>  
                </div>
                <select id="clSelectedSynonym" name="selectedSynonym" size="7">
                        <c:forEach var="symbol" items="${projectFacade.getSymbolCollection(project.id)}" varStatus="iter">
                            <option value="${symbol.id}" ${iter.first ? 'selected="selected"' : ''}>${symbol.name}</option>
                        </c:forEach>
                    </select>
                <div id="clDefinitionTop">
                    <div>
                        <div id="clDefinitionTopLeft">
                            <div>
                                <div><label><fmt:message key="document" />:</label></div>
                                <div><h3 class="clInfo overflowEllipsis">${symbol.document.name}</h3></div>
                            </div>
                            <div>
                                <div><label id="clCategoryLabel" for="clCategory"><fmt:message key="category" />:</label></div>
                                <div>
                                    <select id="clCategory" name="category">
                                        <c:forEach var="category" items="${categories}" varStatus="iter">
                                            <c:if test="${iter.first and category.id == 1 and empty symbol.id}">
                                                <c:set var="generalSelected" value="${true}" />
                                            </c:if>
                                            <option value="${category.id}" ${symbol.definition.category.id == category.id ? 'selected="selected"' : ''}><fmt:message key="${category.name}" /></option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div id="clClassificationField" style="display: ${symbol.definition.category.id == 1 or generalSelected ? 'none' : 'block'};">
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
                        <div id="clDefinitionTopRight">
                            <c:if test="${!empty log}">
                                <label><fmt:message key="last edited" />:</label>
                                <h3 class="clInfo overflowEllipsis">${log.user.name}</h3>
                                <h3 class="clInfo"><fmt:formatDate value="${log.date}" type="both" dateStyle="MEDIUM" timeZone="GMT-6" /></h3>
                            </c:if>
                        </div>
                    </div>
                </div>
                <div id="clDefinitionBottom">
                    <div class="clDefinitionField">
                        <div class="clFixPadding">
                            <label id="clNotionLabel" for="clNotion" class="tab"><fmt:message key="notion" /></label>
                            <textarea id="clNotion" name="notion" maxlength="32767">${symbol.definition.notion}</textarea>
                        </div>
                    </div>
                    <div id="clIntentionFields" style="display: ${symbol.definition.category.id == 1 or generalSelected ? 'none' : 'block'};">
                        <div class="clDefinitionField Left">
                            <div class="clFixPadding">
                                <label id="clActualIntentionLabel" for="clActualIntention" class="tab"><fmt:message key="actual intention" /></label>
                                <textarea id="clActualIntention" name="actualIntention" maxlength="32767">${symbol.definition.actualIntention}</textarea>
                            </div>
                        </div>
                        <div class="clDefinitionField Right">
                            <div class="clFixPadding"> 
                                <label id="clFutureIntentionLabel" for="clFutureIntention" class="tab"><fmt:message key="future intention" /></label>
                                <textarea id="clFutureIntention" name="futureIntention" maxlength="32767">${symbol.definition.futureIntention}</textarea>
                            </div>
                        </div>
                        <div style="clear:both;"></div>
                    </div>
                    <div class="clDefinitionField">
                        <div class="clFixPadding">
                            <label id="clCommentsLabel" for="clComments" class="tab"><fmt:message key="comments" /></label>
                            <textarea id="clComments" name="comments" maxlength="32767">${symbol.definition.comments}</textarea>
                        </div>
                    </div>
                </div>
                <input id="clDoSaveSymbol" type="submit" class="button" value="<fmt:message key="save" />" />
            </form>