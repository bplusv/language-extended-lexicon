<%-- 
    Document   : load
    Created on : May 16, 2012, 10:15:22 AM
    Author     : Luis Salazar <bp.lusv@gmail.com>
--%>

            <form id="ldForm" action="<c:url value="/doLoadDocument" />" method="POST">
                <h3 class="ldTitle"><fmt:message key="load document" /></h3>
                    <select id="ldDocument" name="document" size="18">
                        <c:forEach var="document" items="${projectManager.getProjectDocuments(project.id)}">
                            <option value="${document.id}" ${sessionScope.document.id == document.id ? 'selected="selected"' : ''}>${document.name}</option>
                        </c:forEach>
                    </select>
                <input id="ldDoLoadDocument" type="submit" class="button" value="<fmt:message key="load" />" />
            </form>
            <form id="ldNewForm" action="<c:url value="/doCreateDocument" />" method="POST">
                <h3 class="ldTitle"><fmt:message key="new document" /></h3>
                <div class="ldField">
                    <label id="ldNameLabel" for="ldName"><fmt:message key="name" />:&nbsp;</label>
                    <input id="ldName" type="text" name="documentName" maxlength="255" />
                </div>
                <input type="submit" id="ldDoCreateDocument" class="button" value="<fmt:message key="create" />" />
            </form>
            <div style="clear:both"></div>