<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Eliminar Cliente</title>
</head>
<body>
	<h1>¿Estás seguro de eliminar este cliente?</h1>
	<form method="POST" action="GestionarClientesController?ruta=eliminarCliente">
		<!-- ID oculto para que se envíe al servidor -->
		<input type="hidden" name="txtId" value="${cliente.usuarioId}" />

		<p><strong>Correo:</strong> ${cliente.correo}</p>
		<p><strong>Celular:</strong> ${cliente.celular}</p>
		<p><strong>Dirección:</strong> ${cliente.direccion}</p>
		<p><strong>Cédula:</strong> ${cliente.cedula}</p>
		<p><strong>Nombre:</strong> ${cliente.nombre}</p>

		<input type="submit" value="Eliminar Cliente" />
		<a href="GestionarClientesController?ruta=listar">Cancelar</a>
	</form>
</body>
</html>
