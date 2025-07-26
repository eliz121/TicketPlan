<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--1. Primero se necesita importar la libreria CORE del JSTL -->
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Ticket Plan</title>
</head>
<body>
	<form method="post" action="${pageContext.request.contextPath}/LoginController?ruta=ingresar">
		<fieldset>
			<legend>Login</legend>
			<label>Usuario:</label><br>
			<input type="text" name="usuario_id"/>
			<br><br>
			<label>Password</label><br>
			<input type="password" name="contraseña"/>
			<br><br>
			<input type="submit" value="Ingresar"/>
		</fieldset>
	</form>
	<p>Context Path: ${pageContext.request.contextPath}</p>
</body>
</html>