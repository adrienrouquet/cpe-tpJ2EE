<%@page import="java.util.ArrayList"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<jsp:useBean id="userBean" class="servlet.UserBean" scope="session" />
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
	ArrayList List = userBean.getUsers();

%>
</table>
</div>