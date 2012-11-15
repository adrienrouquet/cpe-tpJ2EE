<div class="connectMenu" style="display: block; border-color: rgb(128,128,128); border-style: solid;">
	<jsp:useBean id="userBean" class="servlet.UserBean" scope="session" />	
	<form name="mainForm" method="post" action="MaServiette3">
	<br>Username:<input type="text" name="username">
	<br>Password:<input type="password" name="password">
	<input type="hidden" name="action" value="login">
	<br><input type="submit" value="se connecter">
	</form>
	<br><a href="MaServiette3?action=subscribe">Subscribe</a>
</div>