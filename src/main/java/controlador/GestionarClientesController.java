package controlador;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.entidades.Cliente;

//3. Agregamos la anotación de web servlet para que el servidor Tomcat
//reconozca la clase como un servle
@WebServlet("/GestionarClientesController")
public class GestionarClientesController extends HttpServlet {
	// 1. Insertamos el SerialID
	private static final long serialVersionUID = 1L;

	// 2. Sobreescribimos los metodos doGet y doPost de la clase madre
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.ruteador(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.ruteador(request, response);
	}

	private void ruteador(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String ruta = (req.getParameter("ruta")==null)?"listarClientes":req.getParameter("ruta");
		switch(ruta) {
		case "listarClientes":
			this.listarClientes(req, resp);
			break;
		case "crearCliente":
			this.crearCliente(req, resp);
			break;
		case "modificarCliente":
			this.modificarCliente(req, resp);
			break;
		case "eliminarCliente":
			this.eliminarCliente(req, resp);
			break;
		case "guardarNuevo":
			this.guardarNuevo(req, resp);
			break;
		case "guardarExistente":
			this.guardarExistente(req, resp);
			break;
		}
	}

	private void listarClientes(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.- Obtener los parámetros

		//2.- Hablar con el Modelo
		List<Cliente> clientes = Cliente.extraerListaClientes();
		//3.- Llamar a la vista
		request.setAttribute("clientes",clientes);
		request.getRequestDispatcher("jsp/ListarClientes.jsp").forward(request, response);
	}

	private void crearCliente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.- Obtener los parámetros
		//2.- Hablar con el Modelo
		//3.- Llamar a la vista
		response.sendRedirect("jsp/RegistrarCliente.jsp");

	}

	private void modificarCliente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.- Obtener los parámetros
		int UsuarioId = Integer.parseInt(request.getParameter("UsuarioId"));
		//2.- Hablar con el Modelo
		Cliente cliente = Cliente.obtenerInformaciónCliente(UsuarioId);
		//3.- Llamar a la vista
		request.setAttribute("cliente", cliente);
		request.getRequestDispatcher("jsp/ModificarCliente.jsp").forward(request, response);
	}

	private void eliminarCliente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int usuarioId = Integer.parseInt(request.getParameter("UsuarioId"));
			boolean eliminado = Cliente.eliminarCliente(usuarioId);

			if (eliminado) {
				response.sendRedirect("GestionarClientesController?ruta=listarClientes");
			} else {
				// Podrías mostrar una página de error si falla
				response.sendRedirect("GestionarClientesController?ruta=listarClientes");
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			response.sendRedirect("GestionarClientesController?ruta=listarClientes");
		}
	}

	private void guardarNuevo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.- Obtener los parámetros
		String correo = request.getParameter("correo");
	    String clave = request.getParameter("txtClave");
	    String celular = request.getParameter("txtCelular");
	    String direccion = request.getParameter("txtDireccion");
	    String cedula = request.getParameter("txtCedula");
	    String nombre = request.getParameter("txtNombre");

	    Cliente cliente = new Cliente(null, correo, clave, celular, direccion, cedula, nombre);
	    //2.- Hablar con el Modelo
	    boolean resultado = Cliente.crearCliente(cliente);
		//3.- Llamar a la vista
	    if(resultado) {
	    	response.sendRedirect("GestionarClientesController?ruta=listarClientes");
	    }else {
	    	response.sendRedirect("GestionarClientesController?ruta=listarClientes");
	    }
	}

	private void guardarExistente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.- Obtener los parámetros
		int UsuarioId = Integer.parseInt(request.getParameter("UsuarioId"));
		String correo = request.getParameter("correo");
	    String clave = request.getParameter("txtClave");
	    String celular = request.getParameter("txtCelular");
	    String direccion = request.getParameter("txtDireccion");
	    String cedula = request.getParameter("txtCedula");
	    String nombre = request.getParameter("txtNombre");

	    Cliente cliente = new Cliente(UsuarioId, correo, clave, celular, direccion, cedula, nombre);
		//2.- Hablar con el Modelo
	    boolean respuesta = Cliente.actualizarCliente(cliente);
		//3.- Llamar a la vista
	    if(respuesta) {
	    	response.sendRedirect("GestionarClientesController?ruta=listarClientes");
	    }else {
	    	response.sendRedirect("GestionarClientesController?ruta=listarClientes");
	    }
	}

}