<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Listar Clientes</title>
</head>
<body>
	<div>
		<a href="./GestionarClientesController?ruta=crearCliente">Nuevo</a>
	</div>
	<table>
		<thead>
			<tr>
				<th>UsuarioId</th>
				<th>correo</th>
				<th>contraseña</th>
				<th>celular</th>
				<th>dirección</th>
				<th>cédula</th>
				<th>nombre</th>
				<th>Acciones</th>
			</tr>
		</thead>
		<tbody>
					<!-- Con expressions iteramos sobre los clientes -->
			<c:forEach items="${clientes}" var="cliente">
				<tr>
					<td>${cliente.usuarioId}</td>
					<td>${cliente.correo}</td>
					<td>${cliente.contraseña}</td>
					<td>${cliente.celular}</td>
					<td>${cliente.direccion}</td>
					<td>${cliente.cedula}</td>
					<td>${cliente.nombre}</td>
					<td><a href="GestionarClientesController?ruta=modificarCliente&UsuarioId=${cliente.usuarioId}">Actualizar</a> | 
					<a href="GestionarClientesController?ruta=eliminarCliente&UsuarioId=${cliente.usuarioId}">Eliminar</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>