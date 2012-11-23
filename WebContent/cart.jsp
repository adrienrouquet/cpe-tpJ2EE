<%@page import="java.sql.ResultSet"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<jsp:useBean id="userBean" class="servlet.UserBean" scope="session" />
<jsp:useBean id="cartBean" class ="servlet.CartBean" scope="session" />

<h2>User Panel</h2>
	<p> Welcome <jsp:getProperty name="userBean" property="name" /> </p>
	<form name="mainForm" method="post" action="OnlineStore">
	<input type="button" value="Logout" onclick="document.forms['mainForm'].elements['action'].value='logout';document.forms['mainForm'].submit();">
	</form>
	<div class="userMenu" style="margin-left: 5%; margin-right: 5%;  border-color: rgb(128,128,128); border-style: solid; background: rgb(220,220,220); width: 90%;">
		<%
			cartBean.getProducts();
		%>
	</div>
