<%-- 
    Document   : loadProject
    Created on : May 28, 2012, 4:07:53 PM
    Author     : Luis Salazar <bp.lusv@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

            <form  id="loadForm"  action="<c:url value="/doLoadProject" />" method="post">
                <h3 class="title"><fmt:message key="load project" /></h3>
                    <select id="project" name="project" size="11">
                        <c:forEach var="project" items="${projectFacade.findAll()}">
                            <option value="${project.id}" ${sessionScope.project.id == project.id ? 'selected="selected"' : ''}>${project.name}</option>
                        </c:forEach>
                    </select>
                <input id="load" type="submit" name="load" value="<fmt:message key="load" />" class="button" />
            </form>
            <div class="spacer"></div>
            <form id="newForm" action="<c:url value="/doCreateProject" />" method="post">
                <h3 class="title"><fmt:message key="new project" /></h3>
                <div class="loadField">
                    <label for="name" id="nameLabel"><fmt:message key="name" />:&nbsp;</label>
                    <input type="text" maxlength="255" id="name" name="name" />
                </div>
                <input type="submit" id="save" name="save" value="<fmt:message key="create" />" class="button" />
            </form>
            <c:choose>
                <c:when test="${requestScope.loadProjectFail == true}">
                    <h3 class="notification fail"><fmt:message key="load project fail" /></h3>
                </c:when>
                <c:when test="${requestScope.createProjectFail == true}">
                    <h3 class="notification fail"><fmt:message key="create project fail" /></h3>
                </c:when>
            </c:choose>
            <div style="clear:both"></div>
