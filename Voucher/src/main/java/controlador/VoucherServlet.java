package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import modelo.JPA.impl.JPAComprobanteDePagoDAO;
import modelo.dao.ComprobanteDAO;
import modelo.entities.*;
import java.sql.Connection;
import java.util.Map;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.io.File;
import jakarta.servlet.annotation.MultipartConfig;
import config_voucher.*;

import javax.naming.InitialContext;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;


@WebServlet("/VoucherServlet")
@MultipartConfig
public class VoucherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.ruteador(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.ruteador(request, response);
	}

	private void ruteador (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String ruta = (request.getParameter("ruta")== null )? "subirVoucher": request.getParameter("ruta");
		switch (ruta) {
		case "subirVoucher":
			this.subirVoucher(request,response);
			break;
		case "guardar":
			this.guardar(request, response);
			break;
		}
		
	}
	private void subirVoucher(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
	
		
        request.getRequestDispatcher("vistas/SubirVoucher.jsp").forward(request, response);

        
		
	}
	 private void guardar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        // Obtener el archivo del formulario
	        Part archivoVoucher = request.getPart("voucher");

	        // Verifica si el archivo es nulo o tiene un tamaño mayor a 0
	        if (archivoVoucher != null && archivoVoucher.getSize() > 0) {
	            try {
	                // Crear un archivo temporal para almacenar el contenido del Part
	                String submittedFileName = archivoVoucher.getSubmittedFileName();
	                File tempFile = File.createTempFile("voucher_", submittedFileName);

	                // Escribir el contenido del Part al archivo temporal
	                archivoVoucher.write(tempFile.getAbsolutePath());

	                // Subir el archivo a Cloudinary
	                Cloudinary cloudinary = CloudinaryConfig.getCloudinary();
	                Map<String, Object> uploadResult = cloudinary.uploader().upload(tempFile, ObjectUtils.asMap(
	                    "resource_type", "auto" // Detecta automáticamente si es imagen o PDF
	                ));
	                String archivoUrl = (String) uploadResult.get("secure_url");

	                // Eliminar el archivo temporal
	                tempFile.delete();

	                // Guardar la información en la base de datos
	                ComprobanteDAO comprobanteDAO = new JPAComprobanteDePagoDAO();
	                ComprobanteDePago comprobanteDePago = new ComprobanteDePago(
	                    1, 
	                    "17-07-2025",
	                    15.25,
	                    "pendiente",
	                    archivoUrl
	                );

	                boolean resultado = comprobanteDAO.guardarComprobante(comprobanteDePago);

	                // Enviar respuesta según el resultado
	                if (resultado) {
	                    request.setAttribute("exitoso", "El archivo se ha enviado con éxito");
	                    request.getRequestDispatcher("vistas/exitoso.jsp").forward(request, response);
	                } else {
	                    request.setAttribute("error", "Ha ocurrido un error al guardar en la base de datos");
	                    request.getRequestDispatcher("vistas/error.jsp").forward(request, response);
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	                request.setAttribute("error", "Error al subir el archivo: " + e.getMessage());
	                request.getRequestDispatcher("vistas/error.jsp").forward(request, response);
	            }
	        } else {
	            request.setAttribute("error", "No se ha seleccionado ningún archivo");
	            request.getRequestDispatcher("vistas/error.jsp").forward(request, response);
	        }
	    }
}
