package modelo.dao;

import java.util.List;

import modelo.entities.Entrada;

public interface EntradaDAO {
	
	public List<Entrada> listarEntradas (int idUsuario);
	
	public Entrada obtenerDetalleEntrada (int idEntrada);
}
