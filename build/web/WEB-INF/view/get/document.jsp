<%-- 
    Document   : document
    Created on : May 10, 2012, 08:23:32 AM
    Author     : Luis Salazar <bp.lusv@gmail.com>
--%>

            <form id="dcUpdateForm" action="<c:url value="/do/updateDocument" />" method="POST" onsubmit="return updateDocumentContent();">
                <input id="dcDocument" type="hidden" name="document" value="${document.id}" />
                <h2 id="dcTitle" class="overflowEllipsis"><fmt:message key="document" />:&nbsp;<span style="color: #222;">${document.name}</span></h2>
                <textarea id="dcDocumentContent" name="content">${document.content}</textarea>
                <div id="dcDocumentContainer" contenteditable="true">${documentFacade.getTaggedContent(document.id)}</div>
                <a id="dcLoadDocument" class="button" href="#!/loadDocument"><fmt:message key="load" /></a>
                <input id="dcDoUpdateDocument" type="submit" name="save" class="button" value="<fmt:message key="save" />" />
            </form>