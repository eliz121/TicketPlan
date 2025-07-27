<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ver entradas en la cuenta personal</title>
</head>
<body>

<h2>Consultar mis entradas</h2>

<form action="<%= request.getContextPath() %>/VerEntradasController" method="post">
    <input type="hidden" name="action" value="ver"/>
    <label for="idUsuario">ID Usuario:</label>
    <input type="number" name="idUsuario" id="idUsuario" required value="<%= request.getAttribute("idUsuario") != null ? request.getAttribute("idUsuario") : "" %>"/>
    <button type="submit">Ver mis entradas</button>
</form>

<hr/>

<%
    java.util.List<modelo.entities.Entrada> entradas = (java.util.List<modelo.entities.Entrada>) request.getAttribute("entradas");
    if (entradas != null && !entradas.isEmpty()) {
        int idUsuario = (Integer) request.getAttribute("idUsuario");
%>
        <h3>Entradas del usuario <%= idUsuario %></h3>
        <ul>
        <% for (modelo.entities.Entrada entrada : entradas) { %>
            <li>
                <strong>Entrada ID:</strong> <%= entrada.getIdEntrada() %> -
                <strong>Evento:</strong> <%= entrada.getIdEvento() %> -
                <strong>Precio:</strong> $<%= entrada.getPrecio() %>

                <form action="<%= request.getContextPath() %>/VerEntradasController" method="post" style="display:inline;">
                    <input type="hidden" name="action" value="detalle"/>
                    <input type="hidden" name="idEntrada" value="<%= entrada.getIdEntrada() %>"/>
                    <button type="submit">Ver detalles</button>
                </form>
            </li>
        <% } %>
        </ul>
<%  } %>

<%
    modelo.entities.Entrada detalle = (modelo.entities.Entrada) request.getAttribute("detalle");
    if (detalle != null) {
%>
    <hr/>
    <h3>Detalle de la Entrada</h3>
    <p><strong>ID Entrada:</strong> <%= detalle.getIdEntrada() %></p>
    <p><strong>Precio:</strong> $<%= detalle.getPrecio() %></p>
    <p><strong>Tipo:</strong> <%= detalle.getTipo() %></p>
    <p><strong>Estado:</strong> <%= detalle.getEstado() %></p>
    <p><strong>Código QR:</strong> <%= detalle.getCodigoQR() %></p>
    <p><strong>ID Evento:</strong> <%= detalle.getIdEvento() %></p>
    <p><strong>ID Usuario:</strong> <%= detalle.getIdCliente() %></p>
    <button onclick="window.history.back()">Volver</button>
<% } %>

</body>
</html>