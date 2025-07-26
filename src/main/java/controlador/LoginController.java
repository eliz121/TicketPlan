package controlador;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.entidades.Cliente;

//3. Agregamos la anotación de web servlet para que el servidor Tomcat
//reconozca la clase como un servlet
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {

	// 1. Insertamos el SerialID
	private static final long serialVersionUID = 1L;
	// 2. Sobreescribir los métodos doGet y doPost de la clase madre

	// 5. pasamos los parámetros del doGet y doPost a los métodos
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Entró al método doGet del LoginController");
		this.ruteador(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Entró al método doPost del LoginController");
		this.ruteador(req, resp);
	}

	private void ruteador(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String ruta = (req.getParameter("ruta")==null)?"solicitarIngreso":req.getParameter("ruta");
		switch(ruta) {
		case "solicitarIngreso":
			System.out.println("LoginController - solicitarIngreso llamado");
			this.solicitarIngreso(req, resp);
			break;
		case "ingresar":
			 System.out.println("LoginController - ingresar llamado");
			this.ingresar(req, resp);
			break;
		case "cerrarSesion":
			System.out.println("LoginController - cerrarSesion llamado");
			this.cerrarSesion(req, resp);
			break;
		}
	}

	// 4. Según el diseño establecido la clase LoginController tiene los métodos:
	// solicitarIngreso, ingresar y cerrarSesion
	private void solicitarIngreso(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//1.- Obtener los parámetros
		//2.- Hablar con el Modelo
		//3.- Llamar a la vista
		resp.sendRedirect("jsp/Login.jsp");
	}

	private void ingresar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1.- Obtener los parámetros
		String usuario = req.getParameter("usuario_id");
		String clave = req.getParameter("contraseña");
		System.out.println("Usuario recibido en formulario: " + usuario);
	    System.out.println("Clave recibida en formulario: " + clave);
		//2.- Hablar con el Modelo
		Cliente resultado = Cliente.iniciarSesion(usuario, clave);
		//3.- Llamar a la vista
		if(resultado==null) {
			System.out.println("Login fallido para usuario: " + usuario);
			//No se permite ingresar
			resp.sendRedirect("jsp/Login.jsp");
		}else {
			System.out.println("Login exitoso para usuario: " + usuario);
			HttpSession sesionSition = req.getSession();
			sesionSition.setAttribute("autorizado", resultado);
			//Se permite ingresar a la parte privada
			resp.sendRedirect("GestionarClientesController?ruta=listarClientes");

		}
	}

	private void cerrarSesion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1.- Obtener los parámetros
		//2.- Hablar con el Modelo
		//3.- Llamar a la vista
	}

}