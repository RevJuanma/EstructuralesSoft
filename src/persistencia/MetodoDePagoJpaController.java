/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.Pago;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modelo.MetodoDePago;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author juanm
 */
public class MetodoDePagoJpaController implements Serializable {

    public MetodoDePagoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MetodoDePago metodoDePago) {
        if (metodoDePago.getPagos() == null) {
            metodoDePago.setPagos(new ArrayList<Pago>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Pago> attachedPagos = new ArrayList<Pago>();
            for (Pago pagosPagoToAttach : metodoDePago.getPagos()) {
                pagosPagoToAttach = em.getReference(pagosPagoToAttach.getClass(), pagosPagoToAttach.getId());
                attachedPagos.add(pagosPagoToAttach);
            }
            metodoDePago.setPagos(attachedPagos);
            em.persist(metodoDePago);
            for (Pago pagosPago : metodoDePago.getPagos()) {
                MetodoDePago oldMetodoDePagoOfPagosPago = pagosPago.getMetodoDePago();
                pagosPago.setMetodoDePago(metodoDePago);
                pagosPago = em.merge(pagosPago);
                if (oldMetodoDePagoOfPagosPago != null) {
                    oldMetodoDePagoOfPagosPago.getPagos().remove(pagosPago);
                    oldMetodoDePagoOfPagosPago = em.merge(oldMetodoDePagoOfPagosPago);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MetodoDePago metodoDePago) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MetodoDePago persistentMetodoDePago = em.find(MetodoDePago.class, metodoDePago.getId());
            List<Pago> pagosOld = persistentMetodoDePago.getPagos();
            List<Pago> pagosNew = metodoDePago.getPagos();
            List<Pago> attachedPagosNew = new ArrayList<Pago>();
            for (Pago pagosNewPagoToAttach : pagosNew) {
                pagosNewPagoToAttach = em.getReference(pagosNewPagoToAttach.getClass(), pagosNewPagoToAttach.getId());
                attachedPagosNew.add(pagosNewPagoToAttach);
            }
            pagosNew = attachedPagosNew;
            metodoDePago.setPagos(pagosNew);
            metodoDePago = em.merge(metodoDePago);
            for (Pago pagosOldPago : pagosOld) {
                if (!pagosNew.contains(pagosOldPago)) {
                    pagosOldPago.setMetodoDePago(null);
                    pagosOldPago = em.merge(pagosOldPago);
                }
            }
            for (Pago pagosNewPago : pagosNew) {
                if (!pagosOld.contains(pagosNewPago)) {
                    MetodoDePago oldMetodoDePagoOfPagosNewPago = pagosNewPago.getMetodoDePago();
                    pagosNewPago.setMetodoDePago(metodoDePago);
                    pagosNewPago = em.merge(pagosNewPago);
                    if (oldMetodoDePagoOfPagosNewPago != null && !oldMetodoDePagoOfPagosNewPago.equals(metodoDePago)) {
                        oldMetodoDePagoOfPagosNewPago.getPagos().remove(pagosNewPago);
                        oldMetodoDePagoOfPagosNewPago = em.merge(oldMetodoDePagoOfPagosNewPago);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = metodoDePago.getId();
                if (findMetodoDePago(id) == null) {
                    throw new NonexistentEntityException("The metodoDePago with id " + id + " no longer exists.");
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
            MetodoDePago metodoDePago;
            try {
                metodoDePago = em.getReference(MetodoDePago.class, id);
                metodoDePago.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The metodoDePago with id " + id + " no longer exists.", enfe);
            }
            List<Pago> pagos = metodoDePago.getPagos();
            for (Pago pagosPago : pagos) {
                pagosPago.setMetodoDePago(null);
                pagosPago = em.merge(pagosPago);
            }
            em.remove(metodoDePago);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MetodoDePago> findMetodoDePagoEntities() {
        return findMetodoDePagoEntities(true, -1, -1);
    }

    public List<MetodoDePago> findMetodoDePagoEntities(int maxResults, int firstResult) {
        return findMetodoDePagoEntities(false, maxResults, firstResult);
    }

    private List<MetodoDePago> findMetodoDePagoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MetodoDePago.class));
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

    public MetodoDePago findMetodoDePago(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MetodoDePago.class, id);
        } finally {
            em.close();
        }
    }

    public int getMetodoDePagoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MetodoDePago> rt = cq.from(MetodoDePago.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
