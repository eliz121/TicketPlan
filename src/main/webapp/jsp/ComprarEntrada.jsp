<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Comprar Entrada</title>
  <link rel="stylesheet" href="estilos.css">
</head>
<body>
<div class="contenedor">
  <div class="column1">
    <img src="${evento.imagen}" alt="${evento.nombre}">
    <h1>${evento.nombre}</h1>
    <p>${evento.descripcion}</p>
  </div>
  <div class="column2">
    <div class="detalles">
      <h2>Entradas</h2>
      <p>Precio unitario: $<span id="precio">${evento.precio}</span></p>
      <div class="cantidad">
        <button type="button" onclick="cambiarCantidad(-1)">-</button>
        <input type="number" id="cantidad" name="cantidad" value="1" min="1" readonly>
        <button type="button" onclick="cambiarCantidad(1)">+</button>
      </div>
      <button class="boton-comprar" onclick="mostrarModal()">Comprar</button>
    </div>
  </div>
</div>

<div id="modalCompra" class="modal">
  <div class="modal-contenido">
    <span class="cerrar" onclick="cerrarModal()">&times;</span>
    <h2>Resumen Compra</h2>
    <p>Total: $<span id="totalCompra"></span></p>
    <form method="post" action="${pageContext.request.contextPath}/comprar">
      <label for="voucher">Número de voucher:</label><br>
      <input type="text" id="voucher" name="voucher" required><br><br>
      <input type="hidden" name="cantidad" id="formCantidad">
      <input type="hidden" name="eventoId" value="${evento.id}">
      <button type="submit">Confirmar</button>
    </form>
  </div>
</div>

<script>
  const precio = parseFloat(document.getElementById("precio").textContent);
  function cambiarCantidad(v) {
    const input = document.getElementById("cantidad");
    let c = parseInt(input.value) + v;
    if (c >=1) input.value = c;
  }
  function mostrarModal(){
    const cantidad = parseInt(document.getElementById("cantidad").value);
    const total = cantidad * precio;
    document.getElementById("totalCompra").textContent = total.toFixed(2);
    document.getElementById("formCantidad").value = cantidad;
    document.getElementById("modalCompra").style.display = "block";
  }
  function cerrarModal(){
    document.getElementById("modalCompra").style.display = "none";
  }
</script>
</body>
</html>
