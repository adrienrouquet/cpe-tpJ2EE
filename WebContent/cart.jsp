<%@page import="java.sql.ResultSet"%>
<jsp:useBean id="userBean" class="myBeans.UserBean" scope="session" />
<jsp:useBean id="cartBean" class ="myBeans.CartBean" scope="session" />

<h2>User Panel</h2>
	<p> Welcome <jsp:getProperty name="userBean" property="name" /> </p>
	<form name="mainForm" method="post" action="OnlineStore">
	<input type="hidden" name="action" value="" />
	<input type="hidden" name="productId" value="0" />
	<input type="hidden" name="productQuantity" value="0" />
	<input type="button" value="Logout" onclick="document.forms['mainForm'].elements['action'].value='logout';document.forms['mainForm'].submit();">
	

	<div class="userMenu" style="margin-left: 5%; margin-right: 5%;  background: rgb(220,220,220); width: 90%;">
		<table class="cartTable">
			<tr>
				<td style="border-color: rgb(180,180,180); border-style: solid; background: rgb(240,240,240); width: 80%;">
					<table>
					<%
					ResultSet rs = cartBean.getProducts();
					if(rs != null)
					{
						do
						{
							int rsProductId = Integer.parseInt(rs.getString("productId"));
							String rsProductName = rs.getString("productName");
							int rsProductQty = cartBean.getQuantity(rsProductId);
							int rsStockQuantity = cartBean.getStockQuantity(rsProductId);
							int rsDelay = cartBean.getDelay(rsProductId);
					%>
						<tr>
							<td><%= rsProductName %></td>
						<tr>
							<td><%= rs.getString("description") %></td>
							<td><%= rs.getString("price") %></td>
							<td>
							<%  
								
								if( rsStockQuantity == 0)
								{
									out.println("Dispo dans: "+rsDelay+" jours");
								}
								else
								{
									
							%>
									<select name="productQuantity_<%= rsProductId %>" onchange="document.forms['mainForm'].elements['action'].value='editCartSubmit';document.forms['mainForm'].elements['productQuantity'].value=this.value;document.forms['mainForm'].elements['productId'].value='<%= rsProductId %>';document.forms['mainForm'].submit();">
									
								<%
									String selected = "";
									for(int i=0;i<=rsStockQuantity;i++)
									{
										selected = "";
										if( i == rsProductQty)
											selected = "selected";
								%>
										<option value="<%= i %>" <%= selected %>> <%= i %> </option>
								<%		
									}
								%>
									</select>
							<%
								}
							%>
							</td>
						</tr>
					<%
						}while(rs.next());
					}
					%>
					</table>	
				</td>
				<td style="border-color: rgb(180,180,180); border-style: solid; background: rgb(240,240,240); width: 20%;">
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
	<br>

	</div>
	</form>