<%-- 
    Document   : loadProject
    Created on : May 28, 2012, 4:07:53 PM
    Author     : Luis Salazar <bp.lusv@gmail.com>
--%>

            <form  id="lpLoadForm" action="<c:url value="/doLoadProject" />" method="POST">
                <h3 class="lpTitle"><fmt:message key="load project" /></h3>
                    <select id="lpProject" name="project" size="11">
                        <c:forEach var="project" items="${projectFacade.findAll()}">
                            <option value="${project.id}" ${sessionScope.project.id == project.id ? 'selected="selected"' : ''}>${project.name}</option>
                        </c:forEach>
                    </select>
                <input id="lpDoLoadProject" type="submit" class="button" value="<fmt:message key="load" />" />
            </form>
            <div class="spacer"></div>
            <form id="lpNewForm" action="<c:url value="/doCreateProject" />" method="POST">
                <h3 class="lpTitle"><fmt:message key="new project" /></h3>
                <div class="lpField">
                    <label id="lpNameLabel" for="lpName"><fmt:message key="name" />:&nbsp;</label>
                    <input id="lpName" type="text" name="projectName" maxlength="255" />
                </div>
                <input id="lpDoCreateProject" type="submit" class="button" value="<fmt:message key="create" />" />
            </form>
            <div style="clear:both"></div>