package servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


//La anotación @WebServlet indica que la clase se compartará como un servlet
@WebServlet("/HolaMundo")
						//La clase hereda de la clase HttpServlet
public class MiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	@Override
	public void init() throws ServletException {
		System.out.println("init() llamado - Servlet iniciado");
	}

	@Override
	public void destroy() {
		System.out.println("destroy() llamado - Servlet destruido");
	}



	//Se sobreescribe el método doGet()
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet() llamado - se hizo una petición GET");
		response.setContentType("text/html");
		response.getWriter().println("<h1>Hola desde el método doGet() </h1>");
	}

	//Se sobreescribe el método doPost()
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost() llamado - se hizo una petición POST");
		response.setContentType("text/html");
		response.getWriter().println("<h1>Hola desde el método doPost()</h1>");
	}

}
