 <%-- 
    Document   : classify
    Created on : May 14, 2012, 10:12:22 AM
    Author     : Luis Salazar <bp.lusv@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

            <form id="classifyForm" action="<c:url value="${submitAction}" />" method="post">
                <input type="hidden" id="symbol" name="symbol" value="${symbol.id}" />
                <input type="hidden" id="name" name="name" value="${symbol.name}" />
                <input type="hidden" id="document" name="document" value="${symbol.document.id}" />
                <h2 id="title" class="overflowEllipsis"><fmt:message key="symbol" />:&nbsp;<span style="color: #222;">${symbol.name}</span></h2>
                <div id="definitionTop">
                    <div>
                        <div id="definitionTopLeft">
                            <div>
                                <div><label><fmt:message key="document" />:</label></div>
                                <div><h3 class="info overflowEllipsis">${symbol.document.name}</h3></div>
                            </div>
                            <div id="categoryField">
                                <div><label id="categoryLabel" for="category"><fmt:message key="category" />:</label></div>
                                <div>
                                    <select id="category" name="category">
                                        <c:forEach var="category" items="${categories}" varStatus="iter">
                                            <c:if test="${iter.first and category.id == 1 and empty symbol.id}">
                                                <c:set var="generalSelected" value="${true}" />
                                            </c:if>
                                            <option value="${category.id}" ${symbol.definition.category.id == category.id ? 'selected="selected"' : ''}><fmt:message key="${category.name}" /></option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div id="classificationField" style="display: ${symbol.definition.category.id == 1 or generalSelected ? 'none' : 'block'};">
                                <div><label id="classificationLabel" for="classification"><fmt:message key="classification" />:</label></div>
                                <div>
                                    <select id="classification" name="classification">
                                        <c:forEach var="classification" items="${classifications}">
                                            <option value="${classification.id}" ${symbol.definition.classification.id == classification.id ? 'selected="selected"' : ''}><fmt:message key="${classification.name}" /></option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div>
                        <div id="definitionTopRight">
                            <c:if test="${!empty log}">
                                <label><fmt:message key="last edited" />:</label>
                                <h3 class="info overflowEllipsis">${log.user.name}</h3>
                                <h3 class="info"><fmt:formatDate value="${log.date}" type="both" dateStyle="MEDIUM" timeZone="GMT-6" /></h3>
                            </c:if>
                        </div>
                    </div>
                </div>
                <div id="definitionBottom">
                    <div class="definitionField">
                        <div class="fixPadding">
                            <label for="notion" class="tab"><fmt:message key="notion" /></label>
                            <textarea maxlength="32767" id="notion" name="notion">${symbol.definition.notion}</textarea>
                        </div>
                    </div>
                    <div id="intentionFields" style="display: ${symbol.definition.category.id == 1 or generalSelected ? 'none' : 'block'};">
                        <div class="definitionField" style="width: 48%; float: left;">
                            <div class="fixPadding">
                                <label for="actualIntention" class="tab"><fmt:message key="actual intention" /></label>
                                <textarea maxlength="32767" id="actualIntention" name="actualIntention">${symbol.definition.actualIntention}</textarea>
                            </div>
                        </div>
                        <div class="definitionField" style="width: 48%; float: right;">
                            <div class="fixPadding"> 
                                <label for="futureIntention" class="tab"><fmt:message key="future intention" /></label>
                                <textarea maxlength="32767" id="futureIntention" name="futureIntention">${symbol.definition.futureIntention}</textarea>
                            </div>
                        </div>
                        <div style="clear:both;"></div>
                    </div>
                    <div class="definitionField">
                        <div class="fixPadding">
                            <label for="comments" class="tab"><fmt:message key="comments" /></label>
                            <textarea maxlength="32767" id="comments" name="comments">${symbol.definition.comments}</textarea>
                        </div>
                    </div>
                </div>
                <input type="submit" name="save" value="Save" id="save" class="button" />
            </form>
            <c:choose>
                <c:when test="${createSymbolFail == true}">
                    <h3 class="notification fail"><fmt:message key="create symbol fail" /></h3>
                </c:when>
                <c:when test="${createSymbolFail == false}">
                    <h3 class="notification success"><fmt:message key="create symbol success" /></h3>
                </c:when>
                <c:when test="${updateSymbolFail == true}">
                    <h3 class="notification fail"><fmt:message key="update symbol fail" /></h3>
                </c:when>
                <c:when test="${updateSymbolFail == false}">
                    <h3 class="notification success"><fmt:message key="update symbol success" /></h3>
                </c:when>
            </c:choose>