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
import modelo.Material;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modelo.MarcaMaterial;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author juanm
 */
public class MarcaMaterialJpaController implements Serializable {

    public MarcaMaterialJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MarcaMaterial marcaMaterial) {
        if (marcaMaterial.getMateriales() == null) {
            marcaMaterial.setMateriales(new ArrayList<Material>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Material> attachedMateriales = new ArrayList<Material>();
            for (Material materialesMaterialToAttach : marcaMaterial.getMateriales()) {
                materialesMaterialToAttach = em.getReference(materialesMaterialToAttach.getClass(), materialesMaterialToAttach.getId());
                attachedMateriales.add(materialesMaterialToAttach);
            }
            marcaMaterial.setMateriales(attachedMateriales);
            em.persist(marcaMaterial);
            for (Material materialesMaterial : marcaMaterial.getMateriales()) {
                MarcaMaterial oldMarcaMaterialOfMaterialesMaterial = materialesMaterial.getMarcaMaterial();
                materialesMaterial.setMarcaMaterial(marcaMaterial);
                materialesMaterial = em.merge(materialesMaterial);
                if (oldMarcaMaterialOfMaterialesMaterial != null) {
                    oldMarcaMaterialOfMaterialesMaterial.getMateriales().remove(materialesMaterial);
                    oldMarcaMaterialOfMaterialesMaterial = em.merge(oldMarcaMaterialOfMaterialesMaterial);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MarcaMaterial marcaMaterial) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MarcaMaterial persistentMarcaMaterial = em.find(MarcaMaterial.class, marcaMaterial.getId());
            List<Material> materialesOld = persistentMarcaMaterial.getMateriales();
            List<Material> materialesNew = marcaMaterial.getMateriales();
            List<Material> attachedMaterialesNew = new ArrayList<Material>();
            for (Material materialesNewMaterialToAttach : materialesNew) {
                materialesNewMaterialToAttach = em.getReference(materialesNewMaterialToAttach.getClass(), materialesNewMaterialToAttach.getId());
                attachedMaterialesNew.add(materialesNewMaterialToAttach);
            }
            materialesNew = attachedMaterialesNew;
            marcaMaterial.setMateriales(materialesNew);
            marcaMaterial = em.merge(marcaMaterial);
            for (Material materialesOldMaterial : materialesOld) {
                if (!materialesNew.contains(materialesOldMaterial)) {
                    materialesOldMaterial.setMarcaMaterial(null);
                    materialesOldMaterial = em.merge(materialesOldMaterial);
                }
            }
            for (Material materialesNewMaterial : materialesNew) {
                if (!materialesOld.contains(materialesNewMaterial)) {
                    MarcaMaterial oldMarcaMaterialOfMaterialesNewMaterial = materialesNewMaterial.getMarcaMaterial();
                    materialesNewMaterial.setMarcaMaterial(marcaMaterial);
                    materialesNewMaterial = em.merge(materialesNewMaterial);
                    if (oldMarcaMaterialOfMaterialesNewMaterial != null && !oldMarcaMaterialOfMaterialesNewMaterial.equals(marcaMaterial)) {
                        oldMarcaMaterialOfMaterialesNewMaterial.getMateriales().remove(materialesNewMaterial);
                        oldMarcaMaterialOfMaterialesNewMaterial = em.merge(oldMarcaMaterialOfMaterialesNewMaterial);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = marcaMaterial.getId();
                if (findMarcaMaterial(id) == null) {
                    throw new NonexistentEntityException("The marcaMaterial with id " + id + " no longer exists.");
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
            MarcaMaterial marcaMaterial;
            try {
                marcaMaterial = em.getReference(MarcaMaterial.class, id);
                marcaMaterial.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The marcaMaterial with id " + id + " no longer exists.", enfe);
            }
            List<Material> materiales = marcaMaterial.getMateriales();
            for (Material materialesMaterial : materiales) {
                materialesMaterial.setMarcaMaterial(null);
                materialesMaterial = em.merge(materialesMaterial);
            }
            em.remove(marcaMaterial);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MarcaMaterial> findMarcaMaterialEntities() {
        return findMarcaMaterialEntities(true, -1, -1);
    }

    public List<MarcaMaterial> findMarcaMaterialEntities(int maxResults, int firstResult) {
        return findMarcaMaterialEntities(false, maxResults, firstResult);
    }

    private List<MarcaMaterial> findMarcaMaterialEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MarcaMaterial.class));
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

    public MarcaMaterial findMarcaMaterial(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MarcaMaterial.class, id);
        } finally {
            em.close();
        }
    }

    public int getMarcaMaterialCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MarcaMaterial> rt = cq.from(MarcaMaterial.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
