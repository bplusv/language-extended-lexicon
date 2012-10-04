<div>
    <c:forEach var="comment" items="${comments}" varStatus="iter">
        <p><c:out value="${comment.content}" /></p>
    </c:forEach>
</div>