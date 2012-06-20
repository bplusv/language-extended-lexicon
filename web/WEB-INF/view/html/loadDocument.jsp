<%-- 
    Document   : load
    Created on : May 16, 2012, 10:15:22 AM
    Author     : Luis Salazar <bp.lusv@gmail.com>
--%>

            <form  id="loadForm"  action="<c:url value="/doLoadDocument" />" method="post" onsubmit="return controller('doLoadDocument', $(this).serialize());">
                <h3 class="title"><fmt:message key="load document" /></h3>
                    <select id="document" name="document" size="18">
                        <c:forEach var="document" items="${projectManager.getProjectDocuments(project.id)}">
                            <option value="${document.id}" ${sessionScope.document.id == document.id ? 'selected="selected"' : ''}>${document.name}</option>
                        </c:forEach>
                    </select>
                <input id="load" type="submit" name="load" value="<fmt:message key="load" />" class="button" />
            </form>
            <form id="newForm" action="<c:url value="/doCreateDocument" />" method="post" onsubmit="return controller('doCreateDocument', $(this).serialize());">
                <h3 class="title"><fmt:message key="new document" /></h3>
                <div class="loadField">
                    <label for="name" id="nameLabel"><fmt:message key="name" />:&nbsp;</label>
                    <input type="text" maxlength="255" id="name" name="name" />
                </div>
                <input type="submit" id="save" name="save" value="<fmt:message key="create" />" class="button" />
            </form>
            <div style="clear:both"></div>