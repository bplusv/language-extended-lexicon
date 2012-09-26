<%-- 
    Document   : manageDocuments
    Created on : May 16, 2012, 10:15:22 AM
    Author     : Luis Salazar <bp.lusv@gmail.com>
--%>

<form id="mdLoadForm" action="/post/loadDocument" method="POST">
    <h3 class="mdTitle"><fmt:message key="load document" /></h3>
    <select id="mdDocument" name="document" size="18" autofocus>
        <c:forEach var="document" items="${projectFacade.getDocumentCollection(project.id)}" varStatus="iter">
            <option value="${document.id}" ${!empty sessionScope.document.id and sessionScope.document.id == document.id ? 'selected="selected"' : empty sessionScope.document.id and iter.first ? 'selected="selected"' : ''}><c:out value="${document.name}" /></option>
        </c:forEach>
    </select>
    <input id="mdLoadDocument" type="submit" class="button" value="<fmt:message key="load" />" />
</form>
<form id="mdCreateForm" action="/post/createDocument" method="POST">
    <h3 class="mdTitle"><fmt:message key="new document" /></h3>
    <div class="mdField">
        <label id="mdNameLabel" for="mdName"><fmt:message key="name" />:&nbsp;</label>
        <input id="mdName" type="text" name="name" maxlength="255" />
    </div>
    <input type="submit" id="mdCreateDocument" class="button" value="<fmt:message key="create" />" />
</form>
<div style="clear:both"></div>