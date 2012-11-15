<h2>Bienvenue Sur le site des serviettes souples</h2>
<p> Vous pouvez vous connecter avec le portail suivant </p>
<div class="connectMenu" style="margin-left: 20%; margin-right: 20%;  border-color: rgb(128,128,128); border-style: solid; background: rgb(220,220,220); width: 60%;">
	<jsp:useBean id="userBean" class="servlet.UserBean" scope="session" />	
	<form name="mainForm" method="post" action="OnlineStore">
	<br>Username:<br><input type="text" name="username">
	<br>Password:<br><input type="password" name="password">
	<input type="hidden" name="action" value="login">
	<br><input type="submit" value="Login">
	</form>
	<br><a href="OnlineStore?action=subscribe">Subscribe</a>
</div>