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
import modelo.Maquinas;
import modelo.Cliente;
import modelo.Estructurales;
import modelo.Pedidos;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author juanm
 */
public class PedidosJpaController implements Serializable {

    public PedidosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pedidos pedidos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Maquinas maquinas = pedidos.getMaquinas();
            if (maquinas != null) {
                maquinas = em.getReference(maquinas.getClass(), maquinas.getId());
                pedidos.setMaquinas(maquinas);
            }
            Cliente cliente = pedidos.getCliente();
            if (cliente != null) {
                cliente = em.getReference(cliente.getClass(), cliente.getId());
                pedidos.setCliente(cliente);
            }
            Estructurales estructurales = pedidos.getEstructurales();
            if (estructurales != null) {
                estructurales = em.getReference(estructurales.getClass(), estructurales.getId());
                pedidos.setEstructurales(estructurales);
            }
            em.persist(pedidos);
            if (maquinas != null) {
                maquinas.getPedidos().add(pedidos);
                maquinas = em.merge(maquinas);
            }
            if (cliente != null) {
                cliente.getPedidos().add(pedidos);
                cliente = em.merge(cliente);
            }
            if (estructurales != null) {
                estructurales.getPedidos().add(pedidos);
                estructurales = em.merge(estructurales);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pedidos pedidos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pedidos persistentPedidos = em.find(Pedidos.class, pedidos.getId());
            Maquinas maquinasOld = persistentPedidos.getMaquinas();
            Maquinas maquinasNew = pedidos.getMaquinas();
            Cliente clienteOld = persistentPedidos.getCliente();
            Cliente clienteNew = pedidos.getCliente();
            Estructurales estructuralesOld = persistentPedidos.getEstructurales();
            Estructurales estructuralesNew = pedidos.getEstructurales();
            if (maquinasNew != null) {
                maquinasNew = em.getReference(maquinasNew.getClass(), maquinasNew.getId());
                pedidos.setMaquinas(maquinasNew);
            }
            if (clienteNew != null) {
                clienteNew = em.getReference(clienteNew.getClass(), clienteNew.getId());
                pedidos.setCliente(clienteNew);
            }
            if (estructuralesNew != null) {
                estructuralesNew = em.getReference(estructuralesNew.getClass(), estructuralesNew.getId());
                pedidos.setEstructurales(estructuralesNew);
            }
            pedidos = em.merge(pedidos);
            if (maquinasOld != null && !maquinasOld.equals(maquinasNew)) {
                maquinasOld.getPedidos().remove(pedidos);
                maquinasOld = em.merge(maquinasOld);
            }
            if (maquinasNew != null && !maquinasNew.equals(maquinasOld)) {
                maquinasNew.getPedidos().add(pedidos);
                maquinasNew = em.merge(maquinasNew);
            }
            if (clienteOld != null && !clienteOld.equals(clienteNew)) {
                clienteOld.getPedidos().remove(pedidos);
                clienteOld = em.merge(clienteOld);
            }
            if (clienteNew != null && !clienteNew.equals(clienteOld)) {
                clienteNew.getPedidos().add(pedidos);
                clienteNew = em.merge(clienteNew);
            }
            if (estructuralesOld != null && !estructuralesOld.equals(estructuralesNew)) {
                estructuralesOld.getPedidos().remove(pedidos);
                estructuralesOld = em.merge(estructuralesOld);
            }
            if (estructuralesNew != null && !estructuralesNew.equals(estructuralesOld)) {
                estructuralesNew.getPedidos().add(pedidos);
                estructuralesNew = em.merge(estructuralesNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = pedidos.getId();
                if (findPedidos(id) == null) {
                    throw new NonexistentEntityException("The pedidos with id " + id + " no longer exists.");
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
            Pedidos pedidos;
            try {
                pedidos = em.getReference(Pedidos.class, id);
                pedidos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pedidos with id " + id + " no longer exists.", enfe);
            }
            Maquinas maquinas = pedidos.getMaquinas();
            if (maquinas != null) {
                maquinas.getPedidos().remove(pedidos);
                maquinas = em.merge(maquinas);
            }
            Cliente cliente = pedidos.getCliente();
            if (cliente != null) {
                cliente.getPedidos().remove(pedidos);
                cliente = em.merge(cliente);
            }
            Estructurales estructurales = pedidos.getEstructurales();
            if (estructurales != null) {
                estructurales.getPedidos().remove(pedidos);
                estructurales = em.merge(estructurales);
            }
            em.remove(pedidos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pedidos> findPedidosEntities() {
        return findPedidosEntities(true, -1, -1);
    }

    public List<Pedidos> findPedidosEntities(int maxResults, int firstResult) {
        return findPedidosEntities(false, maxResults, firstResult);
    }

    private List<Pedidos> findPedidosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pedidos.class));
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

    public Pedidos findPedidos(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pedidos.class, id);
        } finally {
            em.close();
        }
    }

    public int getPedidosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pedidos> rt = cq.from(Pedidos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
