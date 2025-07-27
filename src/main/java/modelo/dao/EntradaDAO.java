package modelo.dao;

import modelo.entities.Entrada;
import java.util.List;

public interface EntradaDAO {
    List<Entrada> listarEntradas(int idCliente);
    Entrada obtenerDetalleEntrada(int idEntrada);
    List<Entrada> listarEntradasEvento(int idEvento);
    boolean verificarDisponibilidad(int idEntrada);
    boolean reservarEntrada(int idEntrada);
}
