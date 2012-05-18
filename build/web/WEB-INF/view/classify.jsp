 <%-- 
    Document   : classify
    Created on : May 14, 2012, 10:12:22 AM
    Author     : Luis Salazar <bp.lusv@gmail.com>
--%>
            <c:choose>
                <c:when test="${requestScope.createConceptError == true}">
                    <h2 style="color:#f00;">Create concept error!</h2>
                </c:when>
                <c:when test="${requestScope.createConceptError == false}">
                    <h2 style="color:#0f0;">Concept Saved!</h2>
                </c:when>
                <c:when test="${requestScope.updateConceptError == true}">
                    <h2 style="color:#f00;">Update concept error!</h2>
                </c:when>
                <c:when test="${requestScope.updateConceptError == false}">
                    <h2 style="color:#0f0;">Concept Updated!</h2>
                </c:when>
            </c:choose>
            <form id="classifyForm" action="<c:url value="${requestScope.submitAction}" />" method="post">
                <input type="hidden" id="concept" name="concept" value="${param.co}" />
                <input type="hidden" id="name" name="name" value="${requestScope.concept.name}" />
                <input type="hidden" id="document" name="document" value="${requestScope.concept.document.id}" />
                <h2 id="title">Concept: <span style="color: #222;">${requestScope.concept.name}</span></h2>
                <div id="definitionTop">
                    <div>
                        <div id="definitionTopLeft">
                            <div>
                                <div><label>Document:</label></div>
                                <div><h3 class="info">${requestScope.concept.document.name}</h3></div>
                            </div>
                            <div>
                                <div><label id="classificationLabel" for="classification">Classification:</label></div>
                                <div>
                                    <select id="classification" name="classification">
                                        <c:forEach var="classification" items="${classifications}">
                                            <option value="${classification.id}" ${requestScope.concept.definition.classification.id == classification.id ? 'selected="selected"' : ''}>${classification.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div>
                                <div><label id="categoryLabel" for="category">Category:</label></div>
                                <div>
                                    <select id="category" name="category">
                                        <c:forEach var="category" items="${categories}">
                                            <option value="${category.id}" ${requestScope.concept.definition.category.id == category.id ? 'selected="selected"' : ''}>${category.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div>
                        <div id="definitionTopRight">
                            <label>Last Edited:</label>
                            <h3 class="info">Luis</h3>
                            <h3 class="info">26/Apr/2012</h3>
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