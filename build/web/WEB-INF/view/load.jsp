<%-- 
    Document   : load
    Created on : May 16, 2012, 10:15:22 AM
    Author     : Luis Salazar <bp.lusv@gmail.com>
--%>

            <form action="<c:url value="/loadDocument" />" id="loadForm" method="post">
                <div class="">
                    <label for="document" id="documentLabel">Document: </label>
                    <select id="document" name="document">
                        <option value="1">Document 1</option>
                        <option value="2">Document 2</option>
                        <option value="3">Document 3</option>
                    </select>
                </div>
                <input id="load" type="submit" name="load" value="Load" class="button" />
            </form>
