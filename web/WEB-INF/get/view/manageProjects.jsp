<%-- 
    Document   : manageProjects
    Created on : May 28, 2012, 4:07:53 PM
    Author     : Luis Salazar <bp.lusv@gmail.com>
--%>

<form id="mpCreateForm" action="/post/createProject" method="POST">
    <h3 class="mpTitle"><fmt:message key="create new project" /></h3>
    <div class="mpField">
        <label id="mpNewProjectNameLabel" for="mpNewProjectName"><fmt:message key="name" />:&nbsp;</label>
        <input id="mpNewProjectName" type="text" name="name" maxlength="255" />
    </div>
    <input id="mpCreateProject" type="submit" class="button" value="<fmt:message key="create" />" />
</form>
<div class="spacer"></div>
<form id="mpLoadForm" action="/post/loadProject" method="POST">
    <h3 class="mpTitle"><fmt:message key="load project" /></h3>
    <select id="mpProject" name="project" size="11" autofocus>
        <c:forEach var="project" items="${projectFacade.findAll()}" varStatus="iter">
            <option value="${project.id}" ${!empty sessionScope.project.id and sessionScope.project.id == project.id ? 'selected="selected"' : empty sessionScope.project.id and iter.first ? 'selected="selected"' : ''}><c:out value="${project.name}" /></option>
        </c:forEach>
    </select>
    <input id="mpLoadProject" type="submit" class="button" value="<fmt:message key="load" />" />
</form>