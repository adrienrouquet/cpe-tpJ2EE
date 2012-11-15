<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Mon magasin de serviettes</title>
		<jsp:useBean id="userBean" class="servlet.UserBean" scope="application" />
		<jsp:useBean id="routerBean" class="servlet.RouterBean" scope="application" />
	</head>
	<body>
		<h1>Les serviettes Souples, votre magasin de serviettes préféré!</h1>
		<div>
			<% 
				if( routerBean.getURL() != "")  
				{
			%>  
			<jsp:include page="<%= routerBean.getURL() %>" />  
			<% 
				} 
			%>  
		</div>
	</body>
</html>