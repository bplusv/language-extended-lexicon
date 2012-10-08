<%-- 
    Document   : manageProjectUsers
    Created on : Oct 8, 2012, 2:35:06 PM
    Author     : Luis Salazar <bp.lusv@gmail.com>
--%>

<form id="mpuAddUserForm" action="/post/addUserToproject" method="POST">
    <label>Add user:</label>
    <input id="mpuAddUserUserName" type="text" name="username" />
    <input class="button" type="submit" value="Add" />
</form>
<h3>Current users:</h3>
<ul>
    <li>luis</li>
    <li>yanet</li>
    <li>solidus</li>
</ul>