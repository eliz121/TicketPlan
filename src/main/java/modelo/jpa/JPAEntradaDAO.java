package modelo.jpa;

import modelo.dao.EntradaDAO;
import modelo.entities.Entrada;

import jakarta.persistence.*;
import java.util.List;

public class JPAEntradaDAO implements EntradaDAO {

    private EntityManager em;

    public JPAEntradaDAO() {
        em = Persistence.createEntityManagerFactory("default").createEntityManager();
    }

    @Override
    public List<Entrada> listarEntradas(int idCliente) {
        Query q = em.createQuery("SELECT e FROM Entrada e WHERE e.idCliente = :id");
        q.setParameter("id", idCliente);
        return q.getResultList();
    }

    @Override
    public Entrada obtenerDetalleEntrada(int idEntrada) {
        return em.find(Entrada.class, idEntrada);
    }

    @Override
    public List<Entrada> listarEntradasEvento(int idEvento) {
        Query q = em.createQuery("SELECT e FROM Entrada e WHERE e.idEvento = :id");
        q.setParameter("id", idEvento);
        return q.getResultList();
    }

    @Override
    public boolean verificarDisponibilidad(int idEntrada) {
        Entrada e = em.find(Entrada.class, idEntrada);
        return e != null && "disponible".equalsIgnoreCase(e.getEstado());
    }

    @Override
    public boolean reservarEntrada(int idEntrada) {
        Entrada e = em.find(Entrada.class, idEntrada);
        if (e != null && "disponible".equalsIgnoreCase(e.getEstado())) {
            em.getTransaction().begin();
            e.setEstado("reservado");
            em.merge(e);
            em.getTransaction().commit();
            return true;
        }
        return false;
    }
}
