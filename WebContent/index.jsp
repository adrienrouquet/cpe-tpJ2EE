<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Les serviettes Souples, votre magasin de serviettes préféré!</title>
		<jsp:useBean id="routerBean" class="servlet.RouterBean" scope="application" />
	</head>
	<body>
		<% 
			if( routerBean.getError() != "")
			{
		%>  
			<div class="error">
		<%
			routerBean.getError();
		 %>
			</div>
		 
		<% 
			}
		%>	
		<div class="body">
			<% 
				if( routerBean.getUrl() != "")  
				{
			%>  
				<jsp:include page="<%= routerBean.getUrl() %>" />
			<% 
				}
			%>
		</div>
	</body>
</html>