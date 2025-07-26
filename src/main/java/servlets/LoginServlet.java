package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		super.init();
		System.out.println("Servlet iniciado");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usuario = request.getParameter("usuario");
		String password = request.getParameter("pass");
		if(usuario.equals("juan")&&password.equals("123")) {
			response(response, "Login OK");
		}else {
			response(response, "Usuario no válido");
		}
	}

	private void response(HttpServletResponse resp, String msg) throws IOException{
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>" + msg + "</h1>");
		out.println("</body>");
		out.println("</html>");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usuario = request.getParameter("usuario");
		String password = request.getParameter("pass");
		if(usuario.equals("juan")&&password.equals("123")) {
			response(response, "Login OK");
		}else {
			response(response, "Usuario no valido");
		}

	}

	@Override
	public void destroy() {
		System.out.println("Servlet destruido");
		super.destroy();
	}
}
