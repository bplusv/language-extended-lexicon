<%-- 
    Document   : manageProjectUsers
    Created on : Oct 8, 2012, 2:35:06 PM
    Author     : Luis Salazar <bp.lusv@gmail.com>
--%>

<h3 id="mpuProjectTitle" class="overflowEllipsis"><fmt:message key="users" />&nbsp;-&nbsp;${requestScope.project.name}</h3>
<form id="mpuAddUserForm" action="/post/addProjectUser" method="POST">
    <h3 class="mpuTitle"><fmt:message key="add user" /></h3>
    <input type="hidden" name="project" value="${requestScope.project.id}" />
    <div class="mpuField">
        <label><fmt:message key="username" />:&nbsp;</label>
        <input id="mpuAddUserName" type="text" name="username" />
    </div>
    <input id="mpuAddUser" class="button" type="submit" value="Add" />
</form>
<div class="spacer"></div>
<form id="mpuRemoveUserForm" action="/post/removeProjectUser" method="POST">
    <h3 class="mpuTitle"><fmt:message key="current users" /></h3>
    <input type="hidden" name="project" value="${requestScope.project.id}" />
    <input id="mpuRemoveUserId" type="hidden" name="userId" value="" />
    <ul id="mpuUsersList">
        <c:forEach var="user" items="${projectFacade.getUserCollection(requestScope.project.id)}" varStatus="iter">
            <li style="background-color:${iter.index % 2 == 0 ? '#fff' : '#f9f9f9'};">
                <span class="overflowEllipsis"><c:out value="${user.name}" /></span>
                <a class="removeUser" data-user.id="${user.id}" data-user.name="${user.name}">&#215;</a>
            </li>
        </c:forEach>
    </ul>
</form>