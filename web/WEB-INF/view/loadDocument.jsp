<%-- 
    Document   : load
    Created on : May 16, 2012, 10:15:22 AM
    Author     : Luis Salazar <bp.lusv@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

            <form  id="loadForm"  action="<c:url value="/doLoadDocument" />" method="post">
                <h3 class="title"><fmt:message key="load document" /></h3>
                    <select id="document" name="document" size="18">
                        <c:forEach var="document" items="${project.getDocumentCollection()}">
                            <option value="${document.id}" ${sessionScope.document.id == document.id ? 'selected="selected"' : ''}>${document.name}</option>
                        </c:forEach>
                    </select>
                <input id="load" type="submit" name="load" value="<fmt:message key="load" />" class="button" />
            </form>
            <form id="newForm" action="<c:url value="/doCreateDocument" />" method="post">
                <h3 class="title"><fmt:message key="new document" /></h3>
                <div class="loadField">
                    <label for="name" id="nameLabel"><fmt:message key="name" />:&nbsp;</label>
                    <input type="text" maxlength="255" id="name" name="name" />
                </div>
                <input type="submit" id="save" name="save" value="<fmt:message key="create" />" class="button" />
            </form>
            <c:choose>
                <c:when test="${requestScope.createProjectFail == false}">
                    <h3 class="notification success"><fmt:message key="create project success" /></h3>
                </c:when>
                <c:when test="${requestScope.loadDocumentFail == true}">
                    <h3 class="notification fail"><fmt:message key="load document fail" /></h3>
                </c:when>
                <c:when test="${requestScope.createDocumentFail == true}">
                    <h3 class="notification fail"><fmt:message key="create document fail" /></h3>
                </c:when>
            </c:choose>
            <div style="clear:both"></div>