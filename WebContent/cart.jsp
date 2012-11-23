<%@page import="java.sql.ResultSet"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<jsp:useBean id="userBean" class="servlet.UserBean" scope="session" />
<jsp:useBean id="cartBean" class ="servlet.CartBean" scope="session" />

<h2>User Panel</h2>
	<p> Welcome <jsp:getProperty name="userBean" property="name" /> </p>
	<form name="mainForm" method="post" action="OnlineStore">
	<input type="hidden" name="action" value="" />
	<input type="button" value="Logout" onclick="document.forms['mainForm'].elements['action'].value='logout';document.forms['mainForm'].submit();">
	</form>

	<div class="userMenu" style="margin-left: 5%; margin-right: 5%;  background: rgb(220,220,220); width: 90%;">
		<table class="cartTable">
			<tr>
				<td style="border-color: rgb(180,180,180); border-style: solid; background: rgb(240,240,240); width: 70%;">
					<table>
					<%
					ResultSet rs = cartBean.getProducts();
					if(rs != null)
					{
						do
						{
					%>
						<tr>
							<td><%= rs.getString("productName") %></td>
						<tr>
							<td><%= rs.getString("description") %></td>
							<td><%= rs.getString("price") %></td>
							<td>
								<select name="quantity<%= rs.getString("productId") %>">
								
							<%
								int productQty = cartBean.getQuantity(Integer.parseInt(rs.getString("productId")));
								String selected = "";
								for(int i=0;i<=cartBean.getStockQuantity(Integer.parseInt(rs.getString("productId")));i++)
								{ 
									selected = "";
									if( i == productQty)
										selected = "selected";
							%>
									<option value="<%= i %>" <%= selected %>> <%= i %> </option>
							<%		
								}
							%>
								</select>
							</td>
						</tr>
					<%
						}while(rs.next());
					}
					%>
					</table>	
				</td>
				<td style="border-color: rgb(180,180,180); border-style: solid; background: rgb(240,240,240); width: 30%;">
					<table>
					
					<%
					int total=0;
					int subTotal=0;
					Boolean emptyCart=true;
					rs.first();
					if(rs != null)
					{
						do
						{
							int productQty = cartBean.getQuantity(Integer.parseInt(rs.getString("productId")));
							subTotal = productQty*Integer.parseInt(rs.getString("price"));	
							
							if(productQty >=1)
							{
								emptyCart = false;
								total += subTotal;
					%>
						<tr>
							<td><%= rs.getString("productName") %></td>
							<td><%= subTotal %></td>
						</tr>
						<%
							}
						}while(rs.next());
					}
					if(emptyCart)
					{
						out.println("<tr><td colspan=2>Your Cart is Empty</td>");
					}
					%>
						<tr>
							<td>TOTAL:</td>
							<td><%= total %></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	<br><span style="text-align: left;"></span><input type="button" onclick="document.forms['mainForm'].elements['action'].value='addUser';document.forms['mainForm'].submit();" value="Add User"/></span>

	</div>