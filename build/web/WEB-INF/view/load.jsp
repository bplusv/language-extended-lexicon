<%-- 
    Document   : load
    Created on : May 16, 2012, 10:15:22 AM
    Author     : Luis Salazar <bp.lusv@gmail.com>
--%>

            <form action="<c:url value="/loadDocument" />" id="loadForm" method="post">
                <c:choose>
                    <c:when test="${requestScope.loadDocumentError == true}">
                        <h2 style="color:#f00;">Load Document error!</h2>
                    </c:when>
                </c:choose>
                <div id="loadTop">
                    <label for="document" id="documentLabel">Document: </label>
                    <select id="document" name="document">
                        <c:forEach var="document" items="${requestScope.documents}">
                            <option value="${document.id}" ${sessionScope.document.id == document.id ? 'selected="selected"' : ''}>${document.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <input id="load" type="submit" name="load" value="Load" class="button" />
            </form>
