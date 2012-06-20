<%-- 
    Document   : document
    Created on : May 10, 2012, 08:23:32 AM
    Author     : Luis Salazar <bp.lusv@gmail.com>
--%>

            <form action="<c:url value="/doUpdateDocument" />" id="documentForm" method="POST" onsubmit="return updateDocumentData() || controller('doUpdateDocument', $(this).serialize());">
                <input type="hidden" name="document" value="${document.id}" />
                <h2 id="title" class="overflowEllipsis"><fmt:message key="document" />:&nbsp;<span style="color: #222;">${document.name}</span></h2>
                <textarea style="display:none;" id="data" name="data">${document.data}</textarea>
                <div contenteditable="true" id="documentContainer">${documentManager.getTaggedDataByDoc(document.id)}</div>
                <a href="#!nav=loadDocument" id="load" class="button"><fmt:message key="load" /></a>
                <input id="save" type="submit" name="save" value="<fmt:message key="save" />" class="button" />
            </form>
            <form action="<c:url value="/classify" />" id="symbolForm" method="GET" onsubmit="return controller('classify', $(this).serialize());">
                <input type="hidden" name="document" value="${document.id}" />
                <input type="hidden" id="name" name="name" value="" />
            </form>
            <c:choose>
                <c:when test="${requestScope.createDocumentFail == false}">
                    <h3 class="notification success"><fmt:message key="create document success" /></h3>
                </c:when>
                <c:when test="${requestScope.updateDocumentFail == true}">
                    <h3 class="notification fail"><fmt:message key="update document fail" /></h3>
                </c:when>
                <c:when test="${requestScope.updateDocumentFail == false}">
                    <h3 class="notification success"><fmt:message key="update document success" /></h3>
                </c:when>
            </c:choose>
