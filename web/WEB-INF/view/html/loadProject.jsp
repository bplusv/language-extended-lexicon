<%-- 
    Document   : loadProject
    Created on : May 28, 2012, 4:07:53 PM
    Author     : Luis Salazar <bp.lusv@gmail.com>
--%>

            <form  id="loadForm"  action="<c:url value="/doLoadProject" />" method="POST" onsubmit="return controller('doLoadProject', $(this).serialize());">
                <h3 class="title"><fmt:message key="load project" /></h3>
                    <select id="project" name="project" size="11">
                        <c:forEach var="project" items="${projectFacade.findAll()}">
                            <option value="${project.id}" ${sessionScope.project.id == project.id ? 'selected="selected"' : ''}>${project.name}</option>
                        </c:forEach>
                    </select>
                <input id="load" type="submit" name="load" value="<fmt:message key="load" />" class="button" />
            </form>
            <div class="spacer"></div>
            <form id="newForm" action="<c:url value="/doCreateProject" />" method="post" onsubmit="return controller('doCreateProject', $(this).serialize());">
                <h3 class="title"><fmt:message key="new project" /></h3>
                <div class="loadField">
                    <label for="name" id="nameLabel"><fmt:message key="name" />:&nbsp;</label>
                    <input type="text" maxlength="255" id="name" name="name" />
                </div>
                <input type="submit" id="save" name="save" value="<fmt:message key="create" />" class="button" />
            </form>
            <div style="clear:both"></div>
