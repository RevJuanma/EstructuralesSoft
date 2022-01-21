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
import modelo.Cliente;
import modelo.MetodoDePago;
import modelo.Cuota;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modelo.Pago;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author juanm
 */
public class PagoJpaController implements Serializable {

    public PagoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pago pago) {
        if (pago.getCuotas() == null) {
            pago.setCuotas(new ArrayList<Cuota>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente cliente = pago.getCliente();
            if (cliente != null) {
                cliente = em.getReference(cliente.getClass(), cliente.getId());
                pago.setCliente(cliente);
            }
            MetodoDePago metodoDePago = pago.getMetodoDePago();
            if (metodoDePago != null) {
                metodoDePago = em.getReference(metodoDePago.getClass(), metodoDePago.getId());
                pago.setMetodoDePago(metodoDePago);
            }
            List<Cuota> attachedCuotas = new ArrayList<Cuota>();
            for (Cuota cuotasCuotaToAttach : pago.getCuotas()) {
                cuotasCuotaToAttach = em.getReference(cuotasCuotaToAttach.getClass(), cuotasCuotaToAttach.getId());
                attachedCuotas.add(cuotasCuotaToAttach);
            }
            pago.setCuotas(attachedCuotas);
            em.persist(pago);
            if (cliente != null) {
                cliente.getPagos().add(pago);
                cliente = em.merge(cliente);
            }
            if (metodoDePago != null) {
                metodoDePago.getPagos().add(pago);
                metodoDePago = em.merge(metodoDePago);
            }
            for (Cuota cuotasCuota : pago.getCuotas()) {
                Pago oldPagoOfCuotasCuota = cuotasCuota.getPago();
                cuotasCuota.setPago(pago);
                cuotasCuota = em.merge(cuotasCuota);
                if (oldPagoOfCuotasCuota != null) {
                    oldPagoOfCuotasCuota.getCuotas().remove(cuotasCuota);
                    oldPagoOfCuotasCuota = em.merge(oldPagoOfCuotasCuota);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pago pago) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pago persistentPago = em.find(Pago.class, pago.getId());
            Cliente clienteOld = persistentPago.getCliente();
            Cliente clienteNew = pago.getCliente();
            MetodoDePago metodoDePagoOld = persistentPago.getMetodoDePago();
            MetodoDePago metodoDePagoNew = pago.getMetodoDePago();
            List<Cuota> cuotasOld = persistentPago.getCuotas();
            List<Cuota> cuotasNew = pago.getCuotas();
            if (clienteNew != null) {
                clienteNew = em.getReference(clienteNew.getClass(), clienteNew.getId());
                pago.setCliente(clienteNew);
            }
            if (metodoDePagoNew != null) {
                metodoDePagoNew = em.getReference(metodoDePagoNew.getClass(), metodoDePagoNew.getId());
                pago.setMetodoDePago(metodoDePagoNew);
            }
            List<Cuota> attachedCuotasNew = new ArrayList<Cuota>();
            for (Cuota cuotasNewCuotaToAttach : cuotasNew) {
                cuotasNewCuotaToAttach = em.getReference(cuotasNewCuotaToAttach.getClass(), cuotasNewCuotaToAttach.getId());
                attachedCuotasNew.add(cuotasNewCuotaToAttach);
            }
            cuotasNew = attachedCuotasNew;
            pago.setCuotas(cuotasNew);
            pago = em.merge(pago);
            if (clienteOld != null && !clienteOld.equals(clienteNew)) {
                clienteOld.getPagos().remove(pago);
                clienteOld = em.merge(clienteOld);
            }
            if (clienteNew != null && !clienteNew.equals(clienteOld)) {
                clienteNew.getPagos().add(pago);
                clienteNew = em.merge(clienteNew);
            }
            if (metodoDePagoOld != null && !metodoDePagoOld.equals(metodoDePagoNew)) {
                metodoDePagoOld.getPagos().remove(pago);
                metodoDePagoOld = em.merge(metodoDePagoOld);
            }
            if (metodoDePagoNew != null && !metodoDePagoNew.equals(metodoDePagoOld)) {
                metodoDePagoNew.getPagos().add(pago);
                metodoDePagoNew = em.merge(metodoDePagoNew);
            }
            for (Cuota cuotasOldCuota : cuotasOld) {
                if (!cuotasNew.contains(cuotasOldCuota)) {
                    cuotasOldCuota.setPago(null);
                    cuotasOldCuota = em.merge(cuotasOldCuota);
                }
            }
            for (Cuota cuotasNewCuota : cuotasNew) {
                if (!cuotasOld.contains(cuotasNewCuota)) {
                    Pago oldPagoOfCuotasNewCuota = cuotasNewCuota.getPago();
                    cuotasNewCuota.setPago(pago);
                    cuotasNewCuota = em.merge(cuotasNewCuota);
                    if (oldPagoOfCuotasNewCuota != null && !oldPagoOfCuotasNewCuota.equals(pago)) {
                        oldPagoOfCuotasNewCuota.getCuotas().remove(cuotasNewCuota);
                        oldPagoOfCuotasNewCuota = em.merge(oldPagoOfCuotasNewCuota);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = pago.getId();
                if (findPago(id) == null) {
                    throw new NonexistentEntityException("The pago with id " + id + " no longer exists.");
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
            Pago pago;
            try {
                pago = em.getReference(Pago.class, id);
                pago.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pago with id " + id + " no longer exists.", enfe);
            }
            Cliente cliente = pago.getCliente();
            if (cliente != null) {
                cliente.getPagos().remove(pago);
                cliente = em.merge(cliente);
            }
            MetodoDePago metodoDePago = pago.getMetodoDePago();
            if (metodoDePago != null) {
                metodoDePago.getPagos().remove(pago);
                metodoDePago = em.merge(metodoDePago);
            }
            List<Cuota> cuotas = pago.getCuotas();
            for (Cuota cuotasCuota : cuotas) {
                cuotasCuota.setPago(null);
                cuotasCuota = em.merge(cuotasCuota);
            }
            em.remove(pago);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pago> findPagoEntities() {
        return findPagoEntities(true, -1, -1);
    }

    public List<Pago> findPagoEntities(int maxResults, int firstResult) {
        return findPagoEntities(false, maxResults, firstResult);
    }

    private List<Pago> findPagoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pago.class));
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

    public Pago findPago(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pago.class, id);
        } finally {
            em.close();
        }
    }

    public int getPagoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pago> rt = cq.from(Pago.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
