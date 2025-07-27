package modelo.jpa;

import modelo.dao.ComprobanteDAO;
import modelo.entities.ComprobanteDePago;

import jakarta.persistence.*;
import java.util.List;

public class JPAComprobanteDePagoDAO implements ComprobanteDAO {

    private EntityManager em;

    public JPAComprobanteDePagoDAO() {
        em = Persistence.createEntityManagerFactory("default").createEntityManager();
    }

    @Override
    public List<ComprobanteDePago> listarComprobantes(int comprobanteId) {
        Query q = em.createQuery("SELECT c FROM ComprobanteDePago c WHERE c.comprobanteId = :id");
        q.setParameter("id", comprobanteId);
        return q.getResultList();
    }

    @Override
    public byte[] getArchivoComprobante(int comprobanteId) {
        ComprobanteDePago c = em.find(ComprobanteDePago.class, comprobanteId);
        return (c != null) ? c.getArchivoComprobante() : null;
    }

    @Override
    public boolean getEstadoComprobante(int comprobanteId) {
        ComprobanteDePago c = em.find(ComprobanteDePago.class, comprobanteId);
        return (c != null) && c.isEstado();
    }

    @Override
    public void guardarComprobante(ComprobanteDePago comprobante) {
        em.getTransaction().begin();
        em.persist(comprobante);
        em.getTransaction().commit();
    }
}
