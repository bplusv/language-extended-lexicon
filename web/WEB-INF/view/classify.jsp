<form action="createConcept" method="post">
    <h1 id="">Concept: ${currentConcept}</h2>
    <h2>Document: ${currentDocument.name}</h2>
    <div class="">
        <label id="classificationLabel" for="classification">Classification:</label>
        <select id="classification" name="classification">
            <option value="1">class1</option>
            <option value="2">class2</option>
            <option value="3">class3</option>
        </select>
    </div>
    <div class="">
        <label id="categoryLabel" for="category">Category:</label>
        <select id="category" name="category">
            <option value="1">category1</option>
            <option value="2">category2</option>
            <option value="3">category3</option>
        </select>
    </div>
    
    <input type="hidden" id="name" name="name" value="${currentConcept}" />

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