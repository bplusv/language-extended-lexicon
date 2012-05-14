<form action="createConcept" method="post">
    <c:choose>
          <c:when test="${requestScope.createConceptError == true}">
              <h1 style="color:#f00;">Create concept error!</h1>
          </c:when>
          <c:when test="${requestScope.createConceptError == false}">
            <h1 style="color:#0f0;">Concept Saved!</h1>
          </c:when>
        </c:choose>
    <h1 id="">Concept: ${sessionScope.currentConcept}</h2>
    <h2>Document: ${sessionScope.currentDocument.name}</h2>
    <div class="">
        <label id="classificationLabel" for="classification">Classification:</label>
        <select id="classification" name="classification">
            <c:forEach var="classification" items="${classifications}">
                <option value="${classification.id}" ${param.classification == classification.id ? 'selected="selected"' : ''}>${classification.name}</option>
            </c:forEach>
        </select>
    </div>
    <div class="">
        <label id="categoryLabel" for="category">Category:</label>
        <select id="category" name="category">
            <c:forEach var="category" items="${categories}">
                <option value="${category.id}" ${param.category == category.id ? 'selected="selected"' : ''}>${category.name}</option>
            </c:forEach>
        </select>
    </div>
    
    <div class="">
        <label for="name"><strong>Concept Name:</strong></label>
        <input type="text" id="name" name="name" />
    </div>
    <div class="">
        <label for="notion"><strong>Concept Notion:</strong></label>
        <input type="text" id="notion" name="notion" />
    </div>
    <div class="">
        <label for="actualIntention"><strong>Actual Intention:</strong></label>
        <input type="text" id="actualIntention" name="actualIntention" />
    </div>
    <div class="">
        <label for="futureIntention"><strong>Future Intention:</strong></label>
        <input type="text" id="futureIntention" name="futureIntention" />
    </div>
    <div class="">
        <label for="comments"><strong>Comments:</strong></label>
        <input type="text" id="comments" name="comments" />
    </div>
    <input type="submit" name="save" value="Save" id="save" class="button" />
</form>