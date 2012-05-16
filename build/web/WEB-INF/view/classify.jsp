<form action="<c:url value="${requestScope.submitAction}" />" method="post">
    <c:choose>
          <c:when test="${requestScope.createConceptError == true}">
              <h1 style="color:#f00;">Create concept error!</h1>
          </c:when>
          <c:when test="${requestScope.createConceptError == false}">
            <h1 style="color:#0f0;">Concept Saved!</h1>
          </c:when>
          <c:when test="${requestScope.updateConceptError == true}">
              <h1 style="color:#f00;">Update concept error!</h1>
          </c:when>
          <c:when test="${requestScope.updateConceptError == false}">
            <h1 style="color:#0f0;">Concept Updated!</h1>
          </c:when>
    </c:choose>
    <input type="hidden" id="concept" name="concept" value="${param.co}" />
    <h1 id="">Concept: ${requestScope.concept.name}</h2>
    <input type="hidden" id="name" name="name" value="${requestScope.concept.name}" />
    <h2>Document: ${requestScope.concept.document.name}</h2>
    <input type="hidden" id="document" name="document" value="${requestScope.concept.document.id}" />
    <div class="">
        <label id="classificationLabel" for="classification">Classification:</label>
        <select id="classification" name="classification">
            <c:forEach var="classification" items="${classifications}">
                <option value="${classification.id}" ${requestScope.concept.definition.classification.id == classification.id ? 'selected="selected"' : ''}>${classification.name}</option>
            </c:forEach>
        </select>
    </div>
    <div class="">
        <label id="categoryLabel" for="category">Category:</label>
        <select id="category" name="category">
            <c:forEach var="category" items="${categories}">
                <option value="${category.id}" ${requestScope.concept.definition.category.id == category.id ? 'selected="selected"' : ''}>${category.name}</option>
            </c:forEach>
        </select>
    </div>
    <div class="">
        <label for="notion"><strong>Concept Notion:</strong></label>
        <input type="text" id="notion" name="notion" value="${concept.definition.notion}" />
    </div>
    <div class="">
        <label for="actualIntention"><strong>Actual Intention:</strong></label>
        <input type="text" id="actualIntention" name="actualIntention" value="${concept.definition.actualIntention}" />
    </div>
    <div class="">
        <label for="futureIntention"><strong>Future Intention:</strong></label>
        <input type="text" id="futureIntention" name="futureIntention" value="${concept.definition.futureIntention}" />
    </div>
    <div class="">
        <label for="comments"><strong>Comments:</strong></label>
        <input type="text" id="comments" name="comments" value="${concept.definition.comments}" />
    </div>
    <input type="submit" name="save" value="Save" id="save" class="button" />
</form>