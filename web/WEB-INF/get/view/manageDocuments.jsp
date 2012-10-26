<%-- 
    Document   : manageDocuments
    Created on : May 16, 2012, 10:15:22 AM
    Author     : Luis Salazar <bp.lusv@gmail.com>
--%>

<div id="mdCommandPanelWrapper">
    <div id="commandPanel">
        <h3 class="mdTitle"><fmt:message key="new document" /></h3>
        <form id="mdCreateForm" action="/post/createDocument" method="post">
            <div class="mdField">
                <label id="mdNameLabel" for="mdName"><fmt:message key="name" />:&nbsp;</label>
                <input id="mdName" type="text" name="name" maxlength="255" />
            </div>
            <input type="submit" id="mdCreateDocument" class="button" value="<fmt:message key="create" />" />
        </form>
    </div>
</div>
<div>
    <h3 class="mpTitle"><fmt:message key="current documents" /></h3>
    <ul id="mdDocumentsList">
        <c:set var="documents" value="${projectFacade.getDocumentCollection(project.id)}" />
        <c:forEach var="document" items="${documents}" varStatus="iter">
            <li class="${sessionScope.document.id == document.id ? 'rowSelected' : iter.index % 2 == 0 ? 'rowEven' : 'rowOdd'}">
                <form action="/post/updateDocumentDescriptors" method="post">
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
    </ul>
</div>