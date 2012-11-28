<%@page import="java.sql.ResultSet"%>
<jsp:useBean id="userBean" class="myBeans.UserBean" scope="session" />
<jsp:useBean id="rightTypeBean" class="myBeans.RightTypeBean" scope="session" />
<jsp:useBean id="adminBean" class="myBeans.AdminBean" scope="session" />

<h2>Administration Panel</h2>
<%
if(userBean.isAdmin())
{
%>
<% 
	String action 	= userBean.getAction();
	Boolean edit 	= false;
	Boolean add		= false;
	if(action.equals("editUser"))
		edit = true;
	if(action.equals("addUser"))
		add = true;
	
%>
	<p> Welcome <jsp:getProperty name="userBean" property="name" /> </p>
	<form name="mainForm" method="post" action="OnlineStore">
	Vous etes maintenant connecte
	<input type="button" value="Logout" onclick="document.forms['mainForm'].elements['action'].value='logout';document.forms['mainForm'].submit();">
	
	<div class="adminMenu" style="margin-left: 5%; margin-right: 5%;  border-color: rgb(128,128,128); border-style: solid; background: rgb(220,220,220); width: 90%;">
<% 
	if(add) 
	{
%>
	<jsp:include page="/subscribe.jsp" />
<%
	}
	else
	{
%>

	Users:
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
			<% if(edit)
				{
					if( rs.getString("userId").equals(request.getParameter("userId")))
					{
			%>
						<td><input type="text" name="userName" value="<%= rs.getString("userName") %>" /></td>
						<td><input type="text" name="login" value="<%= rs.getString("login") %>" /></td>
						<td>
							<select name="rightTypeId" />
							<%
								ResultSet rs2 = rightTypeBean.getRightTypes();
								if(rs2 != null)
								{
									do
									{
										String selected = "";
										if(rs.getString("rightTypeId").equals(rs2.getString("id")))
										{
											selected = "SELECTED";
										}
							%>
								<option <%= selected %> value="<%=rs2.getString("id").toString() %>" ><%= rs2.getString("name") %></option>
							<%
									}while(rs2.next());
								}
							%>
							</select>
						</td>
			<%		}
					else
					{
			%>
						<td><%= rs.getString("userName") %></td>
						<td><%= rs.getString("login") %></td>
						<td><%= rs.getString("rightTypeName") %></td>
			<%		
					}
				}
				else
				{
			%>
			<td><%= rs.getString("userName") %></td>
			<td><%= rs.getString("rightTypeName") %></td>
			<%  
				}
				
				if(edit && rs.getString("userId").equals(request.getParameter("userId")))
				{	
			%>
			<td><input type="button" onclick="document.forms['mainForm'].elements['action'].value='editUserSubmit';document.forms['mainForm'].elements['userId'].value='<%= rs.getString("userId") %>';document.forms['mainForm'].submit();" value="Save"/></td>
			<td><input type="button" onclick="document.forms['mainForm'].elements['action'].value='view';document.forms['mainForm'].submit();" value="Cancel"/></td>
			<%
				}
				else
				{
			%>
			<td><input type="button" onclick="document.forms['mainForm'].elements['action'].value='editUser';document.forms['mainForm'].elements['userId'].value='<%= rs.getString("userId") %>';document.forms['mainForm'].submit();" value="Edit"/></td>
			<td><input type="button" onclick="if(confirm('Are you sure you want to delete this user?')){document.forms['mainForm'].elements['action'].value='deleteUserSubmit';document.forms['mainForm'].elements['userId'].value='<%= rs.getString("userId") %>';document.forms['mainForm'].submit();}" value="Delete"/></td>
			<%
				}
			%>
		</tr>	
	<%
			}while(rs.next());
		}
	%>
	</table>
	<br><span style="text-align: left;"></span><input type="button" onclick="document.forms['mainForm'].elements['action'].value='addUser';document.forms['mainForm'].submit();" value="Add User"/></span>
		
<%
	}
%>
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