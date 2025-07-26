<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registrar Cliente</title>
</head>
<body>
	<h1>Registrar Cliente</h1>
	<form method="POST" action="${pageContext.request.contextPath}/GestionarClientesController?ruta=guardarNuevo">
		<!--<label for="txtId">UsuarioId</label>-->
		<input type="hidden" name="txtId" id="txtId"/>
		<label for="txtCorreo">Correo</label>
		<input type="email" name="correo" id="txtCorreo"/>
		<label for="txtClave">Contraseña</label>
		<input type="password" name="txtClave" id="txtClave"/>
		<label for="txtCelular">Celular</label>
		<input type="number" name="txtCelular" id="txtCelular"/>
		<label for="txtDireccion">Dirección</label>
		<input type="text" name="txtDireccion" id="txtDireccion"/>
		<label for="txtCedula">Cédula</label>
		<input type="text" name="txtCedula" id="txtCedula">
		<label for="txtNombre">Nombre</label>
		<input type="text" name="txtNombre" id="txtNombre">
		<br><br>
		<input type="submit" value="Guardar"/>
	</form>
</body>
</html>