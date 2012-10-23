<%-- 
    Document   : manageDocuments
    Created on : May 16, 2012, 10:15:22 AM
    Author     : Luis Salazar <bp.lusv@gmail.com>
--%>

<form id="mdCreateForm" action="/post/createDocument" method="post">
    <h3 class="mdTitle"><fmt:message key="new document" /></h3>
    <div class="mdField">
        <label id="mdNameLabel" for="mdName"><fmt:message key="name" />:&nbsp;</label>
        <input id="mdName" type="text" name="name" maxlength="255" />
    </div>
    <input type="submit" id="mdCreateDocument" class="button" value="<fmt:message key="create" />" />
</form>
<div class="spacer"></div>
<div>
    <h3 class="mpTitle"><fmt:message key="current documents" /></h3>
    <ul id="mdDocumentsList">
        <c:set var="documents" value="${projectFacade.getDocumentCollection(project.id)}" />
        <c:forEach var="document" items="${documents}" varStatus="iter">
            <%--<c:set var="isOwner" value="${user.id == project.owner.id}" />--%>
            <li class="${sessionScope.document.id == document.id ? 'rowSelected' : iter.index % 2 == 0 ? 'rowEven' : 'rowOdd'}">
                <form action="/lel/post/updateDocumentDescriptors" method="post">
                    <input type="hidden" name="document" value="${document.id}" />
                    <a class="clear" data-document.id="${document.id}" data-document.name="<c:out value="${document.name}" />">&#215;</a>
                    <h2 class="title overflowEllipsis noneditable"><c:out value="${document.name}" /></h2>
                    <input class="titleEdit editable" type="text" name="name" value="<c:out value="${document.name}" />" />
                    <div class="options">
                        <a class="button load" data-document.id="${document.id}"><fmt:message key="load" /></a>
                        <a class="button edit"><fmt:message key="edit" /></a>
                    </div>
                    <div class="saveConfirmation">
                        <a class="button cancelSave"><fmt:message key="cancel" /></a>
                        <input class="button save" type="submit" value="<fmt:message key="save" />" />
                    </div>
                </form>
            </li>
        </c:forEach>
    <c:if test="${empty documents}" >
        <h1 id="mdEmptyDocumentsListMessage"><fmt:message key="it's lonely here" />...</h1>
    </c:if>
    </ul>
</div>

<%--
<form id="mdLoadForm" action="/post/loadDocument" method="post">
    <h3 class="mdTitle"><fmt:message key="load document" /></h3>
    <select id="mdDocument" name="document" size="18" autofocus>
        <c:forEach var="document" items="${projectFacade.getDocumentCollection(project.id)}" varStatus="iter">
            <option value="${document.id}" ${!empty sessionScope.document.id and sessionScope.document.id == document.id ? 'selected="selected"' : empty sessionScope.document.id and iter.first ? 'selected="selected"' : ''}><c:out value="${document.name}" /></option>
        </c:forEach>
    </select>
    <input id="mdLoadDocument" type="submit" class="button" value="<fmt:message key="load" />" />
</form>
--%>