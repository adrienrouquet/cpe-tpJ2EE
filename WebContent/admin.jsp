<%@page import="java.sql.ResultSet"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<jsp:useBean id="userBean" class="servlet.UserBean" scope="session" />
<jsp:useBean id="adminBean" class="servlet.AdminBean" scope="session" />
<h2>Administration Panel</h2>
<p> Welcome <jsp:getProperty name="userBean" property="name" /> </p>
<form name="mainForm" method="post" action="OnlineStore">
Vous etes maintenant connecte
<input type="hidden" name="action" value="logout">
<input type="submit" value="Logout">
</form>

<div class="adminMenu" style="margin-left: 5%; margin-right: 5%;  border-color: rgb(128,128,128); border-style: solid; background: rgb(220,220,220); width: 90%;">
Users:
<form name="mainForm" action="OnlineStore" method="post">
<input type="hidden" name="action" value="" />
<input type="hidden" name="userId" value="" />
</form>
<table class="userTable">
	<tr>
		<td>Name</td>
		<td>RightType</td>
		<td colspan="2">Action</td>
	</tr>
<%
	ResultSet rs = adminBean.getUsers();
	if(rs != null)
	{
		do
		{
%>
	<tr>
		<td><%= rs.getString("userName") %></td>
		<%= if(userBean.getAction().equals("editUser"))
			{
		%>
		<td><%= rs.getString("login") %></td>
		<td><%= rs.getString("password") %></td>
		<td>
			<select name="rightTypeId" value="<%= rs.getString("rightTypeId") %>" />
			
		</td>
		<%=
			}
		%>
		<td><%= rs.getString("rightTypeName") %></td>
		<td><input type="button" onclick="document.forms['mainForm'].elements['action'].value='editUser';document.forms['mainForm'].elements['userId'].value='<%= rs.getString("userId") %>';" value="Edit"/></td>
		<td><input type="button" onclick="if(confirm('Are you sure you want to delete this user?')){document.forms['mainForm'].elements['action'].value='deleteUser';document.forms['mainForm'].elements['userId'].value='<%= rs.getString("userId") %>';}" value="Delete"/></td>
	</tr>	
<%
		}while(rs.next());
	}
%>
</table>
</div>