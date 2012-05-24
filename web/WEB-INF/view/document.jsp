<%-- 
    Document   : document
    Created on : May 10, 2012, 08:23:32 AM
    Author     : Luis Salazar <bp.lusv@gmail.com>
--%>
<c:choose>
    <c:when test="${requestScope.createDocumentError == false}">
        <h3 class="notification success">Document successfully created.</h3>
    </c:when>
    <c:when test="${requestScope.updateDocumentError == true}">
        <h3 class="notification success">Something went wrong, can't update document.</h3>
    </c:when>
    <c:when test="${requestScope.updateDocumentError == false}">
        <h3 class="notification success">Document successfully updated.</h3>
    </c:when>
</c:choose>
<form action="<c:url value="/doUpdateDocument" />" id="documentForm" onsubmit="return updateDocumentData();"  method="post">
    <input type="hidden" name="document" value="${document.id}" />
    <h2 id="title"><span>Document:&nbsp;<span style="color: #222;">${document.name}</span></span></h2>
    <input type="hidden" id="data" name="data" value="${document.data}" />
    <div contenteditable="true" id="documentContainer">${document.data}</div>
    <a href="<c:url value="/load" />" id="load" class="button">Load</a>
    <input id="save" type="submit" name="save" value="Save" class="button" />
</form>
<form action="<c:url value="/classify" />" id="conceptForm" method="post">
    <input type="hidden" name="document" value="${document.id}" />
    <input type="hidden" id="name" name="name" value="" />
</form>
