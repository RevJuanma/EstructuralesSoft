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
import modelo.Cuota;
import modelo.Pago;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author juanm
 */
public class CuotaJpaController implements Serializable {

    public CuotaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cuota cuota) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pago pago = cuota.getPago();
            if (pago != null) {
                pago = em.getReference(pago.getClass(), pago.getId());
                cuota.setPago(pago);
            }
            em.persist(cuota);
            if (pago != null) {
                pago.getCuotas().add(cuota);
                pago = em.merge(pago);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cuota cuota) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cuota persistentCuota = em.find(Cuota.class, cuota.getId());
            Pago pagoOld = persistentCuota.getPago();
            Pago pagoNew = cuota.getPago();
            if (pagoNew != null) {
                pagoNew = em.getReference(pagoNew.getClass(), pagoNew.getId());
                cuota.setPago(pagoNew);
            }
            cuota = em.merge(cuota);
            if (pagoOld != null && !pagoOld.equals(pagoNew)) {
                pagoOld.getCuotas().remove(cuota);
                pagoOld = em.merge(pagoOld);
            }
            if (pagoNew != null && !pagoNew.equals(pagoOld)) {
                pagoNew.getCuotas().add(cuota);
                pagoNew = em.merge(pagoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = cuota.getId();
                if (findCuota(id) == null) {
                    throw new NonexistentEntityException("The cuota with id " + id + " no longer exists.");
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
            Cuota cuota;
            try {
                cuota = em.getReference(Cuota.class, id);
                cuota.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cuota with id " + id + " no longer exists.", enfe);
            }
            Pago pago = cuota.getPago();
            if (pago != null) {
                pago.getCuotas().remove(cuota);
                pago = em.merge(pago);
            }
            em.remove(cuota);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cuota> findCuotaEntities() {
        return findCuotaEntities(true, -1, -1);
    }

    public List<Cuota> findCuotaEntities(int maxResults, int firstResult) {
        return findCuotaEntities(false, maxResults, firstResult);
    }

    private List<Cuota> findCuotaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cuota.class));
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

    public Cuota findCuota(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cuota.class, id);
        } finally {
            em.close();
        }
    }

    public int getCuotaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cuota> rt = cq.from(Cuota.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
