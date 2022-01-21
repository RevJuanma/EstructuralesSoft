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
import modelo.Movimiento;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modelo.CajaChica;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author juanm
 */
public class CajaChicaJpaController implements Serializable {

    public CajaChicaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CajaChica cajaChica) {
        if (cajaChica.getMovimientos() == null) {
            cajaChica.setMovimientos(new ArrayList<Movimiento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Movimiento> attachedMovimientos = new ArrayList<Movimiento>();
            for (Movimiento movimientosMovimientoToAttach : cajaChica.getMovimientos()) {
                movimientosMovimientoToAttach = em.getReference(movimientosMovimientoToAttach.getClass(), movimientosMovimientoToAttach.getId());
                attachedMovimientos.add(movimientosMovimientoToAttach);
            }
            cajaChica.setMovimientos(attachedMovimientos);
            em.persist(cajaChica);
            for (Movimiento movimientosMovimiento : cajaChica.getMovimientos()) {
                CajaChica oldCajaChicaOfMovimientosMovimiento = movimientosMovimiento.getCajaChica();
                movimientosMovimiento.setCajaChica(cajaChica);
                movimientosMovimiento = em.merge(movimientosMovimiento);
                if (oldCajaChicaOfMovimientosMovimiento != null) {
                    oldCajaChicaOfMovimientosMovimiento.getMovimientos().remove(movimientosMovimiento);
                    oldCajaChicaOfMovimientosMovimiento = em.merge(oldCajaChicaOfMovimientosMovimiento);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CajaChica cajaChica) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CajaChica persistentCajaChica = em.find(CajaChica.class, cajaChica.getId());
            List<Movimiento> movimientosOld = persistentCajaChica.getMovimientos();
            List<Movimiento> movimientosNew = cajaChica.getMovimientos();
            List<Movimiento> attachedMovimientosNew = new ArrayList<Movimiento>();
            for (Movimiento movimientosNewMovimientoToAttach : movimientosNew) {
                movimientosNewMovimientoToAttach = em.getReference(movimientosNewMovimientoToAttach.getClass(), movimientosNewMovimientoToAttach.getId());
                attachedMovimientosNew.add(movimientosNewMovimientoToAttach);
            }
            movimientosNew = attachedMovimientosNew;
            cajaChica.setMovimientos(movimientosNew);
            cajaChica = em.merge(cajaChica);
            for (Movimiento movimientosOldMovimiento : movimientosOld) {
                if (!movimientosNew.contains(movimientosOldMovimiento)) {
                    movimientosOldMovimiento.setCajaChica(null);
                    movimientosOldMovimiento = em.merge(movimientosOldMovimiento);
                }
            }
            for (Movimiento movimientosNewMovimiento : movimientosNew) {
                if (!movimientosOld.contains(movimientosNewMovimiento)) {
                    CajaChica oldCajaChicaOfMovimientosNewMovimiento = movimientosNewMovimiento.getCajaChica();
                    movimientosNewMovimiento.setCajaChica(cajaChica);
                    movimientosNewMovimiento = em.merge(movimientosNewMovimiento);
                    if (oldCajaChicaOfMovimientosNewMovimiento != null && !oldCajaChicaOfMovimientosNewMovimiento.equals(cajaChica)) {
                        oldCajaChicaOfMovimientosNewMovimiento.getMovimientos().remove(movimientosNewMovimiento);
                        oldCajaChicaOfMovimientosNewMovimiento = em.merge(oldCajaChicaOfMovimientosNewMovimiento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = cajaChica.getId();
                if (findCajaChica(id) == null) {
                    throw new NonexistentEntityException("The cajaChica with id " + id + " no longer exists.");
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
            CajaChica cajaChica;
            try {
                cajaChica = em.getReference(CajaChica.class, id);
                cajaChica.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cajaChica with id " + id + " no longer exists.", enfe);
            }
            List<Movimiento> movimientos = cajaChica.getMovimientos();
            for (Movimiento movimientosMovimiento : movimientos) {
                movimientosMovimiento.setCajaChica(null);
                movimientosMovimiento = em.merge(movimientosMovimiento);
            }
            em.remove(cajaChica);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CajaChica> findCajaChicaEntities() {
        return findCajaChicaEntities(true, -1, -1);
    }

    public List<CajaChica> findCajaChicaEntities(int maxResults, int firstResult) {
        return findCajaChicaEntities(false, maxResults, firstResult);
    }

    private List<CajaChica> findCajaChicaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CajaChica.class));
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

    public CajaChica findCajaChica(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CajaChica.class, id);
        } finally {
            em.close();
        }
    }

    public int getCajaChicaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CajaChica> rt = cq.from(CajaChica.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
