<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Subir Voucher</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background: white;
        margin: 0;
        padding: 0;
        display: flex;
        align-items: center;
        justify-content: center;
        height: 100vh;
    }

    .contenedor-formulario {
        background-color: white;
        padding: 50px;
        border-radius: 16px;
        box-shadow: 0 0 15px rgba(0,0,0,0.15);
        max-width: 600px;
        width: 90%;
        border: 2px solid black;
    }

    .titulo-formulario {
        text-align: center;
        color: black;
        font-size: 28px;
        margin-bottom: 30px;
    }

    .etiqueta-archivo {
        display: block;
        margin-bottom: 15px;
        font-weight: bold;
        font-size: 18px;
        color: black;
    }

    .input-archivo {
        width: 100%;
        padding: 14px;
        font-size: 16px;
        border-radius: 8px;
        border: 2px solid black;
        background-color: white;
        color: black;
    }

    .boton-enviar {
        background-color: black;
        color: white;
        padding: 16px;
        border: none;
        width: 100%;
        border-radius: 8px;
        font-size: 18px;
        cursor: pointer;
        margin-top: 30px;
        font-weight: bold;
    }

    .boton-enviar:hover {
        background-color: darkgray;
        color: black;
    }
</style>
</head>
<body>

<div class="contenedor-formulario">
    <h2 class="titulo-formulario">Subir Voucher de Pago</h2>

    <form action="${pageContext.request.contextPath}/VoucherServlet?ruta=guardar" 
          method="POST" enctype="multipart/form-data">
          
        <input type="hidden" name="id_orden" value="${id_orden}" />

        <label for="voucher" class="etiqueta-archivo">Selecciona tu voucher</label>
        <input type="file" id="voucher" name="voucher" class="input-archivo" accept=".pdf, .jpg, .png" required />

        <input type="submit" value="Enviar" class="boton-enviar" />
    </form>
</div>

</body>
</html>