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
import modelo.Estructurales;
import modelo.MarcaMaterial;
import modelo.Material;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author juanm
 */
public class MaterialJpaController implements Serializable {

    public MaterialJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Material material) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estructurales estructurales = material.getEstructurales();
            if (estructurales != null) {
                estructurales = em.getReference(estructurales.getClass(), estructurales.getId());
                material.setEstructurales(estructurales);
            }
            MarcaMaterial marcaMaterial = material.getMarcaMaterial();
            if (marcaMaterial != null) {
                marcaMaterial = em.getReference(marcaMaterial.getClass(), marcaMaterial.getId());
                material.setMarcaMaterial(marcaMaterial);
            }
            em.persist(material);
            if (estructurales != null) {
                estructurales.getMateriales().add(material);
                estructurales = em.merge(estructurales);
            }
            if (marcaMaterial != null) {
                marcaMaterial.getMateriales().add(material);
                marcaMaterial = em.merge(marcaMaterial);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Material material) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Material persistentMaterial = em.find(Material.class, material.getId());
            Estructurales estructuralesOld = persistentMaterial.getEstructurales();
            Estructurales estructuralesNew = material.getEstructurales();
            MarcaMaterial marcaMaterialOld = persistentMaterial.getMarcaMaterial();
            MarcaMaterial marcaMaterialNew = material.getMarcaMaterial();
            if (estructuralesNew != null) {
                estructuralesNew = em.getReference(estructuralesNew.getClass(), estructuralesNew.getId());
                material.setEstructurales(estructuralesNew);
            }
            if (marcaMaterialNew != null) {
                marcaMaterialNew = em.getReference(marcaMaterialNew.getClass(), marcaMaterialNew.getId());
                material.setMarcaMaterial(marcaMaterialNew);
            }
            material = em.merge(material);
            if (estructuralesOld != null && !estructuralesOld.equals(estructuralesNew)) {
                estructuralesOld.getMateriales().remove(material);
                estructuralesOld = em.merge(estructuralesOld);
            }
            if (estructuralesNew != null && !estructuralesNew.equals(estructuralesOld)) {
                estructuralesNew.getMateriales().add(material);
                estructuralesNew = em.merge(estructuralesNew);
            }
            if (marcaMaterialOld != null && !marcaMaterialOld.equals(marcaMaterialNew)) {
                marcaMaterialOld.getMateriales().remove(material);
                marcaMaterialOld = em.merge(marcaMaterialOld);
            }
            if (marcaMaterialNew != null && !marcaMaterialNew.equals(marcaMaterialOld)) {
                marcaMaterialNew.getMateriales().add(material);
                marcaMaterialNew = em.merge(marcaMaterialNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = material.getId();
                if (findMaterial(id) == null) {
                    throw new NonexistentEntityException("The material with id " + id + " no longer exists.");
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
            Material material;
            try {
                material = em.getReference(Material.class, id);
                material.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The material with id " + id + " no longer exists.", enfe);
            }
            Estructurales estructurales = material.getEstructurales();
            if (estructurales != null) {
                estructurales.getMateriales().remove(material);
                estructurales = em.merge(estructurales);
            }
            MarcaMaterial marcaMaterial = material.getMarcaMaterial();
            if (marcaMaterial != null) {
                marcaMaterial.getMateriales().remove(material);
                marcaMaterial = em.merge(marcaMaterial);
            }
            em.remove(material);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Material> findMaterialEntities() {
        return findMaterialEntities(true, -1, -1);
    }

    public List<Material> findMaterialEntities(int maxResults, int firstResult) {
        return findMaterialEntities(false, maxResults, firstResult);
    }

    private List<Material> findMaterialEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Material.class));
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

    public Material findMaterial(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Material.class, id);
        } finally {
            em.close();
        }
    }

    public int getMaterialCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Material> rt = cq.from(Material.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
