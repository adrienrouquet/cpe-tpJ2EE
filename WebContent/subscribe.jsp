<%@page import="java.sql.ResultSet"%>
<jsp:useBean id="userBean" class="myBeans.UserBean" scope="session" />
<jsp:useBean id="rightTypeBean" class="myBeans.RightTypeBean" scope="session" />
<jsp:useBean id="adminBean" class="myBeans.AdminBean" scope="session" />

	Add new User:
	<form name="mainForm" action="OnlineStore" method="post">
	<input type="hidden" name="action" value="" />
	<br>Name:<input type="text" name="userName" value="" />
	<br>Login:<input type="text" name="login" value="" />
	<br>Password:<input type="text" name="password" value="" />
	<br><select name="rightTypeId" />
	<%
		ResultSet rs = rightTypeBean.getRightTypes();
		if(rs != null)
		{
			do
			{
				
	%>
		<option value="<%=rs.getString("id").toString() %>" ><%= rs.getString("name") %></option>
	<%
			}while(rs.next());
		}
	%>
	</select>
	<br><input type="button" onclick="document.forms['mainForm'].elements['action'].value='addUserSubmit';document.forms['mainForm'].submit();" value="Add"/>
	<br><input type="button" onclick="document.forms['mainForm'].elements['action'].value='view';document.forms['mainForm'].submit();" value="Cancel"/>
	</form>