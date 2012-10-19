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
<div>
    <h3 class="mpTitle"><fmt:message key="current projects" /></h3>
    <ul id="mpProjectsList">
        <c:set var="projects" value="${userFacade.getProjectCollection(user.id)}" />
        <c:forEach var="project" items="${projects}" varStatus="iter">
            <c:set var="isOwner" value="${user.id == project.owner.id}" />
            <li class="${sessionScope.project.id == project.id ? 'rowSelected' : iter.index % 2 == 0 ? 'rowEven' : 'rowOdd'}">
                <a class="clear ${user.id == project.owner.id ? 'remove' : 'leave'}" data-project.id="${project.id}" data-project.name="<c:out value="${project.name}" />">&#215;</a>
                <h2 class="title overflowEllipsis"><c:out value="${project.name}" /></h2>
                <h3 class="title overflowEllipsis">
                    <label><fmt:message key="owner" />:&nbsp;</label>
                    <span><c:out value="${project.owner.name}" /></span>
                </h3>
                <p class="description">
                    <label><fmt:message key="description" />:&nbsp;</label>
                    <span><c:out value="${project.description}" /></span>
                </p>
                <div class="options">
                    <a class="button load" data-project.id="${project.id}"><fmt:message key="load" /></a>
                    <c:choose>
                        <c:when test="${isOwner}">
                            <a class="button edit"><fmt:message key="edit" /></a>
                            <a class="button" href="#!/manageProjectUsers?pj=${project.id}"><fmt:message key="users" /></a>
                        </c:when>
                    </c:choose>
                </div>
            </li>
        </c:forEach>
    <c:if test="${empty projects}" >
        <h1 id="mpEmptyProjectsListMessage"><fmt:message key="it's lonely here" />...</h1>
    </c:if>
    </ul>
</div>