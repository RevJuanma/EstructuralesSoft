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
import modelo.Medidas;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import modelo.UnidadDeMedida;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author juanm
 */
public class UnidadDeMedidaJpaController implements Serializable {

    public UnidadDeMedidaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UnidadDeMedida unidadDeMedida) {
        if (unidadDeMedida.getMedidas() == null) {
            unidadDeMedida.setMedidas(new ArrayList<Medidas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Medidas> attachedMedidas = new ArrayList<Medidas>();
            for (Medidas medidasMedidasToAttach : unidadDeMedida.getMedidas()) {
                medidasMedidasToAttach = em.getReference(medidasMedidasToAttach.getClass(), medidasMedidasToAttach.getId());
                attachedMedidas.add(medidasMedidasToAttach);
            }
            unidadDeMedida.setMedidas(attachedMedidas);
            em.persist(unidadDeMedida);
            for (Medidas medidasMedidas : unidadDeMedida.getMedidas()) {
                UnidadDeMedida oldUnidadDeMedidaOfMedidasMedidas = medidasMedidas.getUnidadDeMedida();
                medidasMedidas.setUnidadDeMedida(unidadDeMedida);
                medidasMedidas = em.merge(medidasMedidas);
                if (oldUnidadDeMedidaOfMedidasMedidas != null) {
                    oldUnidadDeMedidaOfMedidasMedidas.getMedidas().remove(medidasMedidas);
                    oldUnidadDeMedidaOfMedidasMedidas = em.merge(oldUnidadDeMedidaOfMedidasMedidas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UnidadDeMedida unidadDeMedida) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UnidadDeMedida persistentUnidadDeMedida = em.find(UnidadDeMedida.class, unidadDeMedida.getId());
            List<Medidas> medidasOld = persistentUnidadDeMedida.getMedidas();
            List<Medidas> medidasNew = unidadDeMedida.getMedidas();
            List<Medidas> attachedMedidasNew = new ArrayList<Medidas>();
            for (Medidas medidasNewMedidasToAttach : medidasNew) {
                medidasNewMedidasToAttach = em.getReference(medidasNewMedidasToAttach.getClass(), medidasNewMedidasToAttach.getId());
                attachedMedidasNew.add(medidasNewMedidasToAttach);
            }
            medidasNew = attachedMedidasNew;
            unidadDeMedida.setMedidas(medidasNew);
            unidadDeMedida = em.merge(unidadDeMedida);
            for (Medidas medidasOldMedidas : medidasOld) {
                if (!medidasNew.contains(medidasOldMedidas)) {
                    medidasOldMedidas.setUnidadDeMedida(null);
                    medidasOldMedidas = em.merge(medidasOldMedidas);
                }
            }
            for (Medidas medidasNewMedidas : medidasNew) {
                if (!medidasOld.contains(medidasNewMedidas)) {
                    UnidadDeMedida oldUnidadDeMedidaOfMedidasNewMedidas = medidasNewMedidas.getUnidadDeMedida();
                    medidasNewMedidas.setUnidadDeMedida(unidadDeMedida);
                    medidasNewMedidas = em.merge(medidasNewMedidas);
                    if (oldUnidadDeMedidaOfMedidasNewMedidas != null && !oldUnidadDeMedidaOfMedidasNewMedidas.equals(unidadDeMedida)) {
                        oldUnidadDeMedidaOfMedidasNewMedidas.getMedidas().remove(medidasNewMedidas);
                        oldUnidadDeMedidaOfMedidasNewMedidas = em.merge(oldUnidadDeMedidaOfMedidasNewMedidas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = unidadDeMedida.getId();
                if (findUnidadDeMedida(id) == null) {
                    throw new NonexistentEntityException("The unidadDeMedida with id " + id + " no longer exists.");
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
            UnidadDeMedida unidadDeMedida;
            try {
                unidadDeMedida = em.getReference(UnidadDeMedida.class, id);
                unidadDeMedida.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The unidadDeMedida with id " + id + " no longer exists.", enfe);
            }
            List<Medidas> medidas = unidadDeMedida.getMedidas();
            for (Medidas medidasMedidas : medidas) {
                medidasMedidas.setUnidadDeMedida(null);
                medidasMedidas = em.merge(medidasMedidas);
            }
            em.remove(unidadDeMedida);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UnidadDeMedida> findUnidadDeMedidaEntities() {
        return findUnidadDeMedidaEntities(true, -1, -1);
    }

    public List<UnidadDeMedida> findUnidadDeMedidaEntities(int maxResults, int firstResult) {
        return findUnidadDeMedidaEntities(false, maxResults, firstResult);
    }

    private List<UnidadDeMedida> findUnidadDeMedidaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UnidadDeMedida.class));
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

    public UnidadDeMedida findUnidadDeMedida(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UnidadDeMedida.class, id);
        } finally {
            em.close();
        }
    }

    public int getUnidadDeMedidaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UnidadDeMedida> rt = cq.from(UnidadDeMedida.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
        //----------------------------------------------------- UNIDAD MEDIDA --------------------------------------------------------//
    }

    public UnidadDeMedida findUnidMedNombre(String nombre) {
        String sql = "SELECT Object(um) FROM UnidadDeMedida um WHERE um.nombre = '" + nombre + "'";
        try {
            Query query = getEntityManager().createQuery(sql);
            return (UnidadDeMedida) query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
}
