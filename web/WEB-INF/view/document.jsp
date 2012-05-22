<%-- 
    Document   : document
    Created on : May 10, 2012, 08:23:32 AM
    Author     : Luis Salazar <bp.lusv@gmail.com>
--%>
            
            <form action="<c:url value="/doUpdateDocument" />" id="documentForm" method="post">
                
                <h2 id="title"><span>Document: <span style="color: #222;">${document.name}</span></span></h2>
                <div contenteditable="true" id="document" name="document">${document.data}</div>
                <a href="<c:url value="/load" />" id="load" class="button">Load</a>
                <input id="save" type="submit" name="save" value="Save" class="button" />
            </form>
            <form action="<c:url value="/classify" />" id="conceptForm" method="post">
                <input type="hidden" id="document" name="document" value="${document.id}" />
                <input type="hidden" id="name" name="name" value="" />
            </form>
