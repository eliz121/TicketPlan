package controlador;

import java.io.IOException;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import modelo.entities.ComprobanteDePago;
import modelo.entities.Entrada;
import modelo.jpa.JPAComprobanteDePagoDAO;
import modelo.jpa.JPAEntradaDAO;

@WebServlet("/ComprarEntradaController")
public class ComprarEntradaController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private JPAEntradaDAO entradaDAO;
	private JPAComprobanteDePagoDAO comprobanteDAO;

	@Override
	public void init() throws ServletException {
		super.init();
		entradaDAO = new JPAEntradaDAO();
		comprobanteDAO = new JPAComprobanteDePagoDAO();
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
			case "comprar":
				comprarEntrada(req, resp);
				break;
		}
	}

	private void comprarEntrada(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		int idEntrada = Integer.parseInt(req.getParameter("idEntrada"));
		String precioStr = req.getParameter("precio"); // opcional, si se envía desde el formulario
		double precio = precioStr != null ? Double.parseDouble(precioStr) : 0;

		Entrada entrada = entradaDAO.obtenerDetalleEntrada(idEntrada);

		if (entrada == null) {
			req.setAttribute("mensaje", "La entrada no existe.");
		} else if (!entradaDAO.verificarDisponibilidad(idEntrada)) {
			req.setAttribute("mensaje", "La entrada no está disponible.");
		} else {
			boolean reservado = entradaDAO.reservarEntrada(idEntrada);

			if (reservado) {
				ComprobanteDePago comprobante = new ComprobanteDePago();
				comprobante.setFechaEnvio(new Date().toString());
				comprobante.setMonto(precio > 0 ? precio : entrada.getPrecio());
				comprobante.setEstado(true);
				comprobante.setArchivoComprobante(new byte[0]); // por ahora sin archivo real

				comprobanteDAO.guardarComprobante(comprobante);

				req.setAttribute("mensaje", "Compra realizada con éxito.");
			} else {
				req.setAttribute("mensaje", "No se pudo reservar la entrada.");
			}
		}

		
		int idUsuario = entrada != null ? entrada.getIdCliente() : 0;
		req.setAttribute("entradas", entradaDAO.listarEntradas(idUsuario));
		req.setAttribute("idUsuario", idUsuario);
		req.getRequestDispatcher("/jsp/misEntradas.jsp").forward(req, resp);
	}
}
