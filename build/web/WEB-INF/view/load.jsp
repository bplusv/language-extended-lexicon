<%-- 
    Document   : load
    Created on : May 16, 2012, 10:15:22 AM
    Author     : Luis Salazar <bp.lusv@gmail.com>
--%>
            <c:choose>
                <c:when test="${requestScope.loadDocumentError == true}">
                    <h3 class="notification error">Something went wrong, can't load document.</h3>
                </c:when>
                <c:when test="${requestScope.createDocumentError == true}">
                    <h3 class="notification error">Something went wrong, can't create document.</h3>
                </c:when>
            </c:choose>
            <form  id="loadForm"  action="<c:url value="/doLoadDocument" />" method="post">
                <h3 class="title">Load document</h3>
                    <select id="document" name="document" size="20">
                        <c:forEach var="document" items="${documentFacade.findAll()}">
                            <option value="${document.id}" ${sessionScope.document.id == document.id ? 'selected="selected"' : ''}>${document.name}</option>
                        </c:forEach>
                    </select>
                <input id="load" type="submit" name="load" value="Load" class="button" />
            </form>
            <form id="newForm" action="<c:url value="/doCreateDocument" />" method="post">
                <h3 class="title">New document</h3>
                <div class="loadField">
                    <label for="name" id="nameLabel">Name:&nbsp;</label>
                    <input type="text" id="name" name="name" />
                </div>
                <input type="submit" id="save" name="save" value="Save" class="button" />
            </form>
            <div style="clear:both"></div>