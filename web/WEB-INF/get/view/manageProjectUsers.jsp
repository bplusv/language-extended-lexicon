<%-- 
    Document   : manageProjectUsers
    Created on : Oct 8, 2012, 2:35:06 PM
    Author     : Luis Salazar <bp.lusv@gmail.com>
--%>

<form id="mpuAddUserForm" action="/lel/post/addProjectUser" method="POST">
    <h3 class="mpuTitle">Add user to <c:out value="${project.name}" /></h3>
    <div class="mpuField">
        <label>Username:&nbsp;</label>
        <input id="mpuAddUserUserName" type="text" name="username" />
    </div>
    <input id="mpuAddUser" class="button" type="submit" value="Add" />
</form>
<div class="spacer"></div>
<h3 class="mpuTitle">Current <c:out value="${project.name}" /> users</h3>
<ul id="mpuUsersList">
    <c:forEach var="user" items="${users}" varStatus="iter">
        <li style="background-color:${iter.index % 2 == 0 ? '#fff' : '#f9f9f9'};">
            <span class="overflowEllipsis"><c:out value="${user.name}" /></span>
            <a class="removeUser" href="javascript:;" data-user.id="${user.id}">&#215;</a>
        </li>
    </c:forEach>
</ul>