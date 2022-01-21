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
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.Maquinas;
import modelo.Medidas;
import modelo.UnidadDeMedida;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author juanm
 */
public class MedidasJpaController implements Serializable {

    public MedidasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Medidas medidas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Maquinas maquinas = medidas.getMaquinas();
            if (maquinas != null) {
                maquinas = em.getReference(maquinas.getClass(), maquinas.getId());
                medidas.setMaquinas(maquinas);
            }
            UnidadDeMedida unidadDeMedida = medidas.getUnidadDeMedida();
            if (unidadDeMedida != null) {
                unidadDeMedida = em.getReference(unidadDeMedida.getClass(), unidadDeMedida.getId());
                medidas.setUnidadDeMedida(unidadDeMedida);
            }
            em.persist(medidas);
            if (maquinas != null) {
                maquinas.getMedidas().add(medidas);
                maquinas = em.merge(maquinas);
            }
            if (unidadDeMedida != null) {
                unidadDeMedida.getMedidas().add(medidas);
                unidadDeMedida = em.merge(unidadDeMedida);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Medidas medidas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Medidas persistentMedidas = em.find(Medidas.class, medidas.getId());
            Maquinas maquinasOld = persistentMedidas.getMaquinas();
            Maquinas maquinasNew = medidas.getMaquinas();
            UnidadDeMedida unidadDeMedidaOld = persistentMedidas.getUnidadDeMedida();
            UnidadDeMedida unidadDeMedidaNew = medidas.getUnidadDeMedida();
            if (maquinasNew != null) {
                maquinasNew = em.getReference(maquinasNew.getClass(), maquinasNew.getId());
                medidas.setMaquinas(maquinasNew);
            }
            if (unidadDeMedidaNew != null) {
                unidadDeMedidaNew = em.getReference(unidadDeMedidaNew.getClass(), unidadDeMedidaNew.getId());
                medidas.setUnidadDeMedida(unidadDeMedidaNew);
            }
            medidas = em.merge(medidas);
            if (maquinasOld != null && !maquinasOld.equals(maquinasNew)) {
                maquinasOld.getMedidas().remove(medidas);
                maquinasOld = em.merge(maquinasOld);
            }
            if (maquinasNew != null && !maquinasNew.equals(maquinasOld)) {
                maquinasNew.getMedidas().add(medidas);
                maquinasNew = em.merge(maquinasNew);
            }
            if (unidadDeMedidaOld != null && !unidadDeMedidaOld.equals(unidadDeMedidaNew)) {
                unidadDeMedidaOld.getMedidas().remove(medidas);
                unidadDeMedidaOld = em.merge(unidadDeMedidaOld);
            }
            if (unidadDeMedidaNew != null && !unidadDeMedidaNew.equals(unidadDeMedidaOld)) {
                unidadDeMedidaNew.getMedidas().add(medidas);
                unidadDeMedidaNew = em.merge(unidadDeMedidaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = medidas.getId();
                if (findMedidas(id) == null) {
                    throw new NonexistentEntityException("The medidas with id " + id + " no longer exists.");
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
            Medidas medidas;
            try {
                medidas = em.getReference(Medidas.class, id);
                medidas.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The medidas with id " + id + " no longer exists.", enfe);
            }
            Maquinas maquinas = medidas.getMaquinas();
            if (maquinas != null) {
                maquinas.getMedidas().remove(medidas);
                maquinas = em.merge(maquinas);
            }
            UnidadDeMedida unidadDeMedida = medidas.getUnidadDeMedida();
            if (unidadDeMedida != null) {
                unidadDeMedida.getMedidas().remove(medidas);
                unidadDeMedida = em.merge(unidadDeMedida);
            }
            em.remove(medidas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Medidas> findMedidasEntities() {
        return findMedidasEntities(true, -1, -1);
    }

    public List<Medidas> findMedidasEntities(int maxResults, int firstResult) {
        return findMedidasEntities(false, maxResults, firstResult);
    }

    private List<Medidas> findMedidasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Medidas.class));
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

    public Medidas findMedidas(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Medidas.class, id);
        } finally {
            em.close();
        }
    }

    public int getMedidasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Medidas> rt = cq.from(Medidas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Medidas> findAllMedidasMaquina(int id) {
        String sql = "SELECT Object(m) FROM Medidas m WHERE m.maquinas.id= '" + id + "'";
        Query query = getEntityManager().createQuery(sql);
        return query.getResultList();
    }
}
