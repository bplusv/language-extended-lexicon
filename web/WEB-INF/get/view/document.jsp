<%-- 
    Document   : document
    Created on : May 10, 2012, 08:23:32 AM
    Author     : Luis Salazar <bp.lusv@gmail.com>
--%>

<form id="dcUpdateForm" action="/post/updateDocument" method="post">
    <div id="dcCommandPanelWrapper">
        <div id="commandPanel">
            <a id="dcLoadDocument" class="button" href="#!/manageDocuments"><fmt:message key="documents" /></a>
            <input id="dcUpdateDocument" type="submit" name="save" class="button" value="<fmt:message key="save" />" />
            <h2 id="dcTitle" class="overflowEllipsis"><fmt:message key="document" />:&nbsp;<span style="color: #222;"><c:out value="${document.name}" /></span></h2>
        </div>
    </div>
    <textarea id="dcDocumentContent" name="content" class="symbolicEditor"><c:out value="${document.content}" /></textarea>
</form>