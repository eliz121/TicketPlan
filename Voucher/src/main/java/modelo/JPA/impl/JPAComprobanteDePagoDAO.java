package modelo.JPA.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import modelo.dao.ComprobanteDAO;
import modelo.entities.ComprobanteDePago;

public class JPAComprobanteDePagoDAO implements ComprobanteDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("comprobante");

    @Override
    public boolean guardarComprobante(ComprobanteDePago comprobante) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(comprobante);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();  // Para depuración, puedes cambiarlo por logging si lo prefieres
            return false;
        } finally {
            em.close();
        }
    }
}
