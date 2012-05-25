 <%-- 
    Document   : classify
    Created on : May 14, 2012, 10:12:22 AM
    Author     : Luis Salazar <bp.lusv@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

            <c:choose>
                <c:when test="${createConceptError == true}">
                    <h3 class="notification error">Something went wrong, can't create new concept.</h3>
                </c:when>
                <c:when test="${createConceptError == false}">
                    <h3 class="notification success">Concept created successfully.</h3>
                </c:when>
                <c:when test="${updateConceptError == true}">
                    <h3 class="notification error">Something went wrong, can't update existing concept.</h3>
                </c:when>
                <c:when test="${updateConceptError == false}">
                    <h3 class="notification success">Concept updated successfully.</h3>
                </c:when>
            </c:choose>
            <form id="classifyForm" action="<c:url value="${submitAction}" />" method="post">
                <input type="hidden" id="concept" name="concept" value="${concept.id}" />
                <input type="hidden" id="name" name="name" value="${concept.name}" />
                <input type="hidden" id="document" name="document" value="${concept.document.id}" />
                <h2 id="title">Concept: <span style="color: #222;">${concept.name}</span></h2>
                <div id="definitionTop">
                    <div>
                        <div id="definitionTopLeft">
                            <div>
                                <div><label>Document:</label></div>
                                <div><h3 class="info">${concept.document.name}</h3></div>
                            </div>
                            <div>
                                <div><label id="classificationLabel" for="classification">Classification:</label></div>
                                <div>
                                    <select id="classification" name="classification">
                                        <c:forEach var="classification" items="${classifications}">
                                            <option value="${classification.id}" ${concept.definition.classification.id == classification.id ? 'selected="selected"' : ''}>${classification.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div>
                                <div><label id="categoryLabel" for="category">Category:</label></div>
                                <div>
                                    <select id="category" name="category">
                                        <c:forEach var="category" items="${categories}">
                                            <option value="${category.id}" ${concept.definition.category.id == category.id ? 'selected="selected"' : ''}>${category.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div>
                        <div id="definitionTopRight">
                            <c:if test="${log != null}">
                                <label>Last Edited By:</label>
                                <h3 class="info">${log.user.name}</h3>
                                <h3 class="info"><fmt:formatDate value="${log.date}" pattern="MM/dd/yyyy hh:mm a" timeZone="GMT-6" /></h3>
                            </c:if>
                        </div>
                    </div>
                </div>
                <div id="definitionBottom">
                    <div class="definitionField">
                        <div class="fixPadding">
                            <label for="notion" class="tab">Concept Notion</label>
                            <textarea id="notion" name="notion">${concept.definition.notion}</textarea>
                        </div>
                    </div>
                    <div class="definitionField" style="width: 48%; float: left;">
                        <div class="fixPadding">
                            <label for="actualIntention" class="tab">Actual Intention</label>
                            <textarea id="actualIntention" name="actualIntention">${concept.definition.actualIntention}</textarea>
                        </div>
                    </div>
                    <div class="definitionField" style="width: 48%; float: right;">
                        <div class="fixPadding"> 
                            <label for="futureIntention" class="tab">Future Intention</label>
                            <textarea id="futureIntention" name="futureIntention">${concept.definition.futureIntention}</textarea>
                        </div>
                    </div>
                    <div style="clear:both;"></div>
                    <div class="definitionField">
                        <div class="fixPadding">
                            <label for="comments" class="tab">Comments</label>
                            <textarea id="comments" name="comments">${concept.definition.comments}</textarea>
                        </div>
                    </div>
                </div>
                <input type="submit" name="save" value="Save" id="save" class="button" />
            </form>