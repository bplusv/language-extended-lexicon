<%-- 
    Document   : loadProject
    Created on : May 28, 2012, 4:07:53 PM
    Author     : Luis Salazar <bp.lusv@gmail.com>
--%>

            <form  id="lpLoadForm" action="/post/loadProject" method="POST">
                <h3 class="lpTitle"><fmt:message key="load project" /></h3>
                    <select id="lpProject" name="project" size="11" autofocus>
                        <c:forEach var="project" items="${projectFacade.findAll()}" varStatus="iter">
                            <option value="${project.id}" ${!empty sessionScope.project.id and sessionScope.project.id == project.id ? 'selected="selected"' : empty sessionScope.project.id and iter.first ? 'selected="selected"' : ''}>${project.name}</option>
                        </c:forEach>
                    </select>
                <input id="lpLoadProject" type="submit" class="button" value="<fmt:message key="load" />" />
            </form>
            <div class="spacer"></div>
            <form id="lpNewForm" action="/post/createProject" method="POST">
                <h3 class="lpTitle"><fmt:message key="new project" /></h3>
                <div class="lpField">
                    <label id="lpNameLabel" for="lpName"><fmt:message key="name" />:&nbsp;</label>
                    <input id="lpName" type="text" name="name" maxlength="255" />
                </div>
                <input id="lpCreateProject" type="submit" class="button" value="<fmt:message key="create" />" />
            </form>
            <div style="clear:both"></div>