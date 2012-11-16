<%@page import="java.sql.ResultSet"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<jsp:useBean id="userBean" class="servlet.UserBean" scope="session" />
<jsp:useBean id="rightTypeBean" class="servlet.RightTypeBean" scope="session" />
<jsp:useBean id="adminBean" class="servlet.AdminBean" scope="session" />

<h2>Administration Panel:</h2>
<%
if(userBean.isUserAdmin())
{
%>
	<p> Welcome <jsp:getProperty name="userBean" property="name" /> </p>
	<form name="mainForm" method="post" action="OnlineStore">
	Vous etes maintenant connecte
	<input type="hidden" name="action" value="logout">
	<input type="submit" value="Logout">
	</form>
	
	<div class="adminMenu" style="margin-left: 5%; margin-right: 5%;  border-color: rgb(128,128,128); border-style: solid; background: rgb(220,220,220); width: 90%;">
	Add new User:
	<form name="mainForm" action="OnlineStore" method="post">
	<input type="hidden" name="action" value="" />
	<br>Name:<input type="text" name="name" value="" />
	</form>
	</div>
<%
}
else
{
%>
	YOU ARE NOT AN ADMIN, GET OUTTA HERE BITCH!
<%
}
%>