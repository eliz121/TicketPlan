<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modificar Cliente</title>
</head>
<body>
	<h1>Modificar Cliente</h1>
	<form method="POST" action="GestionarClientesController?ruta=guardarExistente">
		<!--<label for="txtId">UsuarioId</label>-->
		<input type="hidden" name="UsuarioId" id="txtId" value="${cliente.usuarioId}" /> 
		<label for="txtCorreo">Correo</label>
		<input type="email" name="correo" id="txtCorreo" value="${cliente.correo}" /> 
		<label for="txtClave">Contraseña</label> 
		<input type="password" name="txtClave" id="txtClave" value="${cliente.contraseña}" /> 
		<label for="txtCelular">Celular</label>
		<input type="number" name="txtCelular" id="txtCelular" value="${cliente.celular}" />
		<label for="txtDireccion">Dirección</label> 
		<input type="text"name="txtDireccion" id="txtDireccion" value="${cliente.direccion}" /> 
		<label for="txtCedula">Cédula</label>
		<input type="text" name="txtCedula" id="txtCedula" value="${cliente.cedula}"> 
		<label for="txtNombre">Nombre</label> 
		<input type="text" name="txtNombre" id="txtNombre" value="${cliente.nombre}"> <br>
		<br> <input type="submit" value="Guardar" />
	</form>
</body>
</html>