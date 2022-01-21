/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.CajaChica;
import modelo.Movimiento;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author juanm
 */
public class MovimientoJpaController implements Serializable {

    public MovimientoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Movimiento movimiento) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CajaChica cajaChica = movimiento.getCajaChica();
            if (cajaChica != null) {
                cajaChica = em.getReference(cajaChica.getClass(), cajaChica.getId());
                movimiento.setCajaChica(cajaChica);
            }
            em.persist(movimiento);
            if (cajaChica != null) {
                cajaChica.getMovimientos().add(movimiento);
                cajaChica = em.merge(cajaChica);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Movimiento movimiento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Movimiento persistentMovimiento = em.find(Movimiento.class, movimiento.getId());
            CajaChica cajaChicaOld = persistentMovimiento.getCajaChica();
            CajaChica cajaChicaNew = movimiento.getCajaChica();
            if (cajaChicaNew != null) {
                cajaChicaNew = em.getReference(cajaChicaNew.getClass(), cajaChicaNew.getId());
                movimiento.setCajaChica(cajaChicaNew);
            }
            movimiento = em.merge(movimiento);
            if (cajaChicaOld != null && !cajaChicaOld.equals(cajaChicaNew)) {
                cajaChicaOld.getMovimientos().remove(movimiento);
                cajaChicaOld = em.merge(cajaChicaOld);
            }
            if (cajaChicaNew != null && !cajaChicaNew.equals(cajaChicaOld)) {
                cajaChicaNew.getMovimientos().add(movimiento);
                cajaChicaNew = em.merge(cajaChicaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = movimiento.getId();
                if (findMovimiento(id) == null) {
                    throw new NonexistentEntityException("The movimiento with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Movimiento movimiento;
            try {
                movimiento = em.getReference(Movimiento.class, id);
                movimiento.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The movimiento with id " + id + " no longer exists.", enfe);
            }
            CajaChica cajaChica = movimiento.getCajaChica();
            if (cajaChica != null) {
                cajaChica.getMovimientos().remove(movimiento);
                cajaChica = em.merge(cajaChica);
            }
            em.remove(movimiento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Movimiento> findMovimientoEntities() {
        return findMovimientoEntities(true, -1, -1);
    }

    public List<Movimiento> findMovimientoEntities(int maxResults, int firstResult) {
        return findMovimientoEntities(false, maxResults, firstResult);
    }

    private List<Movimiento> findMovimientoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Movimiento.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Movimiento findMovimiento(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Movimiento.class, id);
        } finally {
            em.close();
        }
    }

    public int getMovimientoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Movimiento> rt = cq.from(Movimiento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
