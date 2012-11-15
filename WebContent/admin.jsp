<%@page import="java.sql.ResultSet"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<jsp:useBean id="userBean" class="servlet.UserBean" scope="session" />
<jsp:useBean id="adminBean" class="servlet.AdminBean" scope="session" />
<h2>Administration Panel</h2>
<p> Welcome <jsp:getProperty name="userBean" property="username" /> </p>
<form name="mainForm" method="post" action="OnlineStore">
Vous etes maintenant connecte
<input type="hidden" name="action" value="logout">
<input type="submit" value="Logout">
</form>

<div class="adminMenu" style="margin-left: 5%; margin-right: 5%;  border-color: rgb(128,128,128); border-style: solid; background: rgb(220,220,220); width: 90%;">
Users:
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
		while(rs.next())
		{
%>
	<tr>
		<td><%= rs("userName") %></td>
		<td><%= rs("login") %></td>
	</tr>	
<%
		}
	}
%>
</table>
</div>