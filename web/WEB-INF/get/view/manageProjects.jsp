<%-- 
    Document   : manageProjects
    Created on : May 28, 2012, 4:07:53 PM
    Author     : Luis Salazar <bp.lusv@gmail.com>
--%>

<div>
    <h3 class="mpTitle"><fmt:message key="current projects" /></h3> 
    <c:forEach var="project" items="${userFacade.getProjectCollection(user.id)}" varStatus="iter">
        <div class="mpProject" style="background-color:${iter.index % 2 == 0 ? '#fff' : '#f9f9f9'};">
            <h2 class="title overflowEllipsis"><c:out value="${project.name}" /></h2>
            <p><label>Description:</label>&nbsp;<c:out value="${project.description}" /></p>
            <p><label>Owner:</label>&nbsp;<c:out value="${project.owner.name}" /></p>
            <div class="options">
                <c:choose>
                    <c:when test="${user.id == project.owner.id}">
                        <a class="button load" data-project.id="${project.id}"><fmt:message key="load" /></a>
                        <a class="button">Edit</a>
                        <a class="button" href="#!/manageProjectUsers?pj=${project.id}">Users</a>
                        <a class="button red">Delete</a>
                    </c:when>
                    <c:otherwise>
                        <a class="button load" data-project.id="${project.id}"><fmt:message key="load" /></a>
                        <a class="button">Leave</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </c:forEach>
    <%-- 
    <select id="mpProject" name="project" size="11" autofocus>
        <c:forEach var="project" items="${projectFacade.findAll()}" varStatus="iter">
            <option value="${project.id}" ${!empty sessionScope.project.id and sessionScope.project.id == project.id ? 'selected="selected"' : empty sessionScope.project.id and iter.first ? 'selected="selected"' : ''}><c:out value="${project.name}" /></option>
        </c:forEach>
    </select>
    --%>
</div>
<div class="spacer"></div>
<form id="mpCreateForm" action="/post/createProject" method="POST">
    <h3 class="mpTitle"><fmt:message key="create new project" /></h3>
    <div class="mpField">
        <label id="mpNewProjectNameLabel" for="mpNewProjectName"><fmt:message key="name" />:&nbsp;</label>
        <input id="mpNewProjectName" type="text" name="name" maxlength="255" />
    </div>
    <input id="mpCreateProject" type="submit" class="button" value="<fmt:message key="create" />" />
</form>
