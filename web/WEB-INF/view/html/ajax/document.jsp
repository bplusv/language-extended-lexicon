<%-- 
    Document   : document
    Created on : May 10, 2012, 08:23:32 AM
    Author     : Luis Salazar <bp.lusv@gmail.com>
--%>

            <form id="dcUpdateForm" action="<c:url value="/ajax/doUpdateDocument" />" method="POST" onsubmit="return updateDocumentData();">
                <input id="dcUfDocument" type="hidden" name="document" value="${document.id}" />
                <h2 id="dcTitle" class="overflowEllipsis"><fmt:message key="document" />:&nbsp;<span style="color: #222;">${document.name}</span></h2>
                <textarea id="dcDocumentData" style="display:none;" name="data">${document.data}</textarea>
                <div id="dcDocumentContainer" contenteditable="true">${documentManager.getTaggedDataByDoc(document.id)}</div>
                <a id="dcLoadDocument" class="button" href="#!/loadDocument"><fmt:message key="load" /></a>
                <input id="dcDoUpdateDocument" type="submit" name="save" class="button" value="<fmt:message key="save" />" />
            </form>
            <form id="dcSymbolForm" action="<c:url value="/ajax/classify" />" method="GET">
                <input id="dcSfDocument" type="hidden" name="document" value="${document.id}" />
                <input id="dcSymbolName" type="hidden" name="symbolName" value="" />
            </form>
