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
import modelo.Servicio;
import modelo.Estructurales;
import modelo.PagoDeServicio;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author juanm
 */
public class PagoDeServicioJpaController implements Serializable {

    public PagoDeServicioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PagoDeServicio pagoDeServicio) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Servicio servicio = pagoDeServicio.getServicio();
            if (servicio != null) {
                servicio = em.getReference(servicio.getClass(), servicio.getId());
                pagoDeServicio.setServicio(servicio);
            }
            Estructurales estructurales = pagoDeServicio.getEstructurales();
            if (estructurales != null) {
                estructurales = em.getReference(estructurales.getClass(), estructurales.getId());
                pagoDeServicio.setEstructurales(estructurales);
            }
            em.persist(pagoDeServicio);
            if (servicio != null) {
                servicio.getPagoDeServicio().add(pagoDeServicio);
                servicio = em.merge(servicio);
            }
            if (estructurales != null) {
                estructurales.getPagoDeServicio().add(pagoDeServicio);
                estructurales = em.merge(estructurales);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PagoDeServicio pagoDeServicio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PagoDeServicio persistentPagoDeServicio = em.find(PagoDeServicio.class, pagoDeServicio.getId());
            Servicio servicioOld = persistentPagoDeServicio.getServicio();
            Servicio servicioNew = pagoDeServicio.getServicio();
            Estructurales estructuralesOld = persistentPagoDeServicio.getEstructurales();
            Estructurales estructuralesNew = pagoDeServicio.getEstructurales();
            if (servicioNew != null) {
                servicioNew = em.getReference(servicioNew.getClass(), servicioNew.getId());
                pagoDeServicio.setServicio(servicioNew);
            }
            if (estructuralesNew != null) {
                estructuralesNew = em.getReference(estructuralesNew.getClass(), estructuralesNew.getId());
                pagoDeServicio.setEstructurales(estructuralesNew);
            }
            pagoDeServicio = em.merge(pagoDeServicio);
            if (servicioOld != null && !servicioOld.equals(servicioNew)) {
                servicioOld.getPagoDeServicio().remove(pagoDeServicio);
                servicioOld = em.merge(servicioOld);
            }
            if (servicioNew != null && !servicioNew.equals(servicioOld)) {
                servicioNew.getPagoDeServicio().add(pagoDeServicio);
                servicioNew = em.merge(servicioNew);
            }
            if (estructuralesOld != null && !estructuralesOld.equals(estructuralesNew)) {
                estructuralesOld.getPagoDeServicio().remove(pagoDeServicio);
                estructuralesOld = em.merge(estructuralesOld);
            }
            if (estructuralesNew != null && !estructuralesNew.equals(estructuralesOld)) {
                estructuralesNew.getPagoDeServicio().add(pagoDeServicio);
                estructuralesNew = em.merge(estructuralesNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = pagoDeServicio.getId();
                if (findPagoDeServicio(id) == null) {
                    throw new NonexistentEntityException("The pagoDeServicio with id " + id + " no longer exists.");
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
            PagoDeServicio pagoDeServicio;
            try {
                pagoDeServicio = em.getReference(PagoDeServicio.class, id);
                pagoDeServicio.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pagoDeServicio with id " + id + " no longer exists.", enfe);
            }
            Servicio servicio = pagoDeServicio.getServicio();
            if (servicio != null) {
                servicio.getPagoDeServicio().remove(pagoDeServicio);
                servicio = em.merge(servicio);
            }
            Estructurales estructurales = pagoDeServicio.getEstructurales();
            if (estructurales != null) {
                estructurales.getPagoDeServicio().remove(pagoDeServicio);
                estructurales = em.merge(estructurales);
            }
            em.remove(pagoDeServicio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PagoDeServicio> findPagoDeServicioEntities() {
        return findPagoDeServicioEntities(true, -1, -1);
    }

    public List<PagoDeServicio> findPagoDeServicioEntities(int maxResults, int firstResult) {
        return findPagoDeServicioEntities(false, maxResults, firstResult);
    }

    private List<PagoDeServicio> findPagoDeServicioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PagoDeServicio.class));
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

    public PagoDeServicio findPagoDeServicio(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PagoDeServicio.class, id);
        } finally {
            em.close();
        }
    }

    public int getPagoDeServicioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PagoDeServicio> rt = cq.from(PagoDeServicio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
