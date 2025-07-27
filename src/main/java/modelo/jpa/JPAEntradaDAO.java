package modelo.jpa;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import modelo.dao.EntradaDAO;
import modelo.entities.Entrada;

public class JPAEntradaDAO implements EntradaDAO {
	
	private EntityManager em;
	
	public JPAEntradaDAO() {
		em = Persistence.createEntityManagerFactory("persistencia").createEntityManager();
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Entrada> listarEntradas(int idCliente) {
	    String sentenciaJPQL = "SELECT e FROM Entrada e WHERE e.idCliente = :idCliente";
	    Query query = em.createQuery(sentenciaJPQL);
	    query.setParameter("idCliente", idCliente);

	    return (List<Entrada>) query.getResultList();
	}
	
	@Override
	public Entrada obtenerDetalleEntrada(int idEntrada) {
	    return em.find(Entrada.class, idEntrada);
	}
}
