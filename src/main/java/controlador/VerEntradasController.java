package controlador;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.entities.Entrada;
import modelo.jpa.JPAEntradaDAO;

@WebServlet("/VerEntradasController")
public class VerEntradasController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private JPAEntradaDAO entradaDAO;

	@Override
	public void init() throws ServletException {
		super.init();
		entradaDAO = new JPAEntradaDAO();  
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ruteador(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ruteador(req, resp);
	}

	private void ruteador(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if (action == null) action = "";

		switch (action) {
			case "ver":
				solicitarVerEntradas(req, resp);
				break;
			case "detalle":
				solicitarDetalleEntrada(req, resp);
				break;
		}
	}

	private void solicitarVerEntradas(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int idUsuario = Integer.parseInt(req.getParameter("idUsuario"));

		List<Entrada> lista = entradaDAO.listarEntradas(idUsuario);
		req.setAttribute("entradas", lista);
		req.setAttribute("idUsuario", idUsuario);
		req.getRequestDispatcher("/jsp/misEntradas.jsp").forward(req, resp);
	}

	private void solicitarDetalleEntrada(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int idEntrada = Integer.parseInt(req.getParameter("idEntrada"));

		Entrada entrada = entradaDAO.obtenerDetalleEntrada(idEntrada);
		req.setAttribute("detalle", entrada);
		req.setAttribute("entradas", entradaDAO.listarEntradas(entrada.getIdCliente()));
		req.setAttribute("idUsuario", entrada.getIdCliente());
		req.getRequestDispatcher("/jsp/misEntradas.jsp").forward(req, resp);
	}
}