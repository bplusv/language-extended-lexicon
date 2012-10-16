<%-- 
    Document   : manageProjects
    Created on : May 28, 2012, 4:07:53 PM
    Author     : Luis Salazar <bp.lusv@gmail.com>
--%>

<div>
    <h3 class="mpTitle"><fmt:message key="current projects" /></h3>
    <ul id="mpProjectsList">
        <c:set var="projects" value="${userFacade.getProjectCollection(user.id)}" />
        <c:forEach var="project" items="${projects}" varStatus="iter">
            <li class="mpProject ${sessionScope.project.id == project.id ? 'rowSelected' : iter.index % 2 == 0 ? 'rowEven' : 'rowOdd'}">
                <h2 class="title overflowEllipsis"><c:out value="${project.name}" /></h2>
                <h3 class="title "><fmt:message key="owner" />:&nbsp;<c:out value="${project.owner.name}" /></h3>
                <h3 class="description"><label><fmt:message key="description" />:</label>&nbsp;<c:out value="${project.description}" /></h3>
                <div class="options">
                    <c:if test="${sessionScope.project.id != project.id}" >
                        <a class="button load" data-project.id="${project.id}"><fmt:message key="load" /></a>
                    </c:if>
                    <c:choose>
                        <c:when test="${user.id == project.owner.id}">
                            <a class="button"><fmt:message key="edit" /></a>
                            <a class="button" href="#!/manageProjectUsers?pj=${project.id}"><fmt:message key="users" /></a>
                            <a class="button red"><fmt:message key="remove" /></a>
                        </c:when>
                        <c:otherwise>
                            <a class="button leave red" data-project.id="${project.id}"><fmt:message key="leave" /></a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </li>
        </c:forEach>
    <c:if test="${empty projects}" >
        <h1 id="mpEmptyProjectsListMessage"><fmt:message key="it's lonely here" />...</h1>
    </c:if>
    </ul>
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
