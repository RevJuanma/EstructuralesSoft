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
import modelo.Estructurales;
import modelo.Pago;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import modelo.Cliente;
import modelo.Pedidos;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author juanm
 */
public class ClienteJpaController implements Serializable {

    public ClienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cliente cliente) {
        if (cliente.getPagos() == null) {
            cliente.setPagos(new ArrayList<Pago>());
        }
        if (cliente.getPedidos() == null) {
            cliente.setPedidos(new ArrayList<Pedidos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estructurales estructurales = cliente.getEstructurales();
            if (estructurales != null) {
                estructurales = em.getReference(estructurales.getClass(), estructurales.getId());
                cliente.setEstructurales(estructurales);
            }
            List<Pago> attachedPagos = new ArrayList<Pago>();
            for (Pago pagosPagoToAttach : cliente.getPagos()) {
                pagosPagoToAttach = em.getReference(pagosPagoToAttach.getClass(), pagosPagoToAttach.getId());
                attachedPagos.add(pagosPagoToAttach);
            }
            cliente.setPagos(attachedPagos);
            List<Pedidos> attachedPedidos = new ArrayList<Pedidos>();
            for (Pedidos pedidosPedidosToAttach : cliente.getPedidos()) {
                pedidosPedidosToAttach = em.getReference(pedidosPedidosToAttach.getClass(), pedidosPedidosToAttach.getId());
                attachedPedidos.add(pedidosPedidosToAttach);
            }
            cliente.setPedidos(attachedPedidos);
            em.persist(cliente);
            if (estructurales != null) {
                estructurales.getCliente().add(cliente);
                estructurales = em.merge(estructurales);
            }
            for (Pago pagosPago : cliente.getPagos()) {
                Cliente oldClienteOfPagosPago = pagosPago.getCliente();
                pagosPago.setCliente(cliente);
                pagosPago = em.merge(pagosPago);
                if (oldClienteOfPagosPago != null) {
                    oldClienteOfPagosPago.getPagos().remove(pagosPago);
                    oldClienteOfPagosPago = em.merge(oldClienteOfPagosPago);
                }
            }
            for (Pedidos pedidosPedidos : cliente.getPedidos()) {
                Cliente oldClienteOfPedidosPedidos = pedidosPedidos.getCliente();
                pedidosPedidos.setCliente(cliente);
                pedidosPedidos = em.merge(pedidosPedidos);
                if (oldClienteOfPedidosPedidos != null) {
                    oldClienteOfPedidosPedidos.getPedidos().remove(pedidosPedidos);
                    oldClienteOfPedidosPedidos = em.merge(oldClienteOfPedidosPedidos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cliente cliente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente persistentCliente = em.find(Cliente.class, cliente.getId());
            Estructurales estructuralesOld = persistentCliente.getEstructurales();
            Estructurales estructuralesNew = cliente.getEstructurales();
            List<Pago> pagosOld = persistentCliente.getPagos();
            List<Pago> pagosNew = cliente.getPagos();
            List<Pedidos> pedidosOld = persistentCliente.getPedidos();
            List<Pedidos> pedidosNew = cliente.getPedidos();
            if (estructuralesNew != null) {
                estructuralesNew = em.getReference(estructuralesNew.getClass(), estructuralesNew.getId());
                cliente.setEstructurales(estructuralesNew);
            }
            List<Pago> attachedPagosNew = new ArrayList<Pago>();
            for (Pago pagosNewPagoToAttach : pagosNew) {
                pagosNewPagoToAttach = em.getReference(pagosNewPagoToAttach.getClass(), pagosNewPagoToAttach.getId());
                attachedPagosNew.add(pagosNewPagoToAttach);
            }
            pagosNew = attachedPagosNew;
            cliente.setPagos(pagosNew);
            List<Pedidos> attachedPedidosNew = new ArrayList<Pedidos>();
            for (Pedidos pedidosNewPedidosToAttach : pedidosNew) {
                pedidosNewPedidosToAttach = em.getReference(pedidosNewPedidosToAttach.getClass(), pedidosNewPedidosToAttach.getId());
                attachedPedidosNew.add(pedidosNewPedidosToAttach);
            }
            pedidosNew = attachedPedidosNew;
            cliente.setPedidos(pedidosNew);
            cliente = em.merge(cliente);
            if (estructuralesOld != null && !estructuralesOld.equals(estructuralesNew)) {
                estructuralesOld.getCliente().remove(cliente);
                estructuralesOld = em.merge(estructuralesOld);
            }
            if (estructuralesNew != null && !estructuralesNew.equals(estructuralesOld)) {
                estructuralesNew.getCliente().add(cliente);
                estructuralesNew = em.merge(estructuralesNew);
            }
            for (Pago pagosOldPago : pagosOld) {
                if (!pagosNew.contains(pagosOldPago)) {
                    pagosOldPago.setCliente(null);
                    pagosOldPago = em.merge(pagosOldPago);
                }
            }
            for (Pago pagosNewPago : pagosNew) {
                if (!pagosOld.contains(pagosNewPago)) {
                    Cliente oldClienteOfPagosNewPago = pagosNewPago.getCliente();
                    pagosNewPago.setCliente(cliente);
                    pagosNewPago = em.merge(pagosNewPago);
                    if (oldClienteOfPagosNewPago != null && !oldClienteOfPagosNewPago.equals(cliente)) {
                        oldClienteOfPagosNewPago.getPagos().remove(pagosNewPago);
                        oldClienteOfPagosNewPago = em.merge(oldClienteOfPagosNewPago);
                    }
                }
            }
            for (Pedidos pedidosOldPedidos : pedidosOld) {
                if (!pedidosNew.contains(pedidosOldPedidos)) {
                    pedidosOldPedidos.setCliente(null);
                    pedidosOldPedidos = em.merge(pedidosOldPedidos);
                }
            }
            for (Pedidos pedidosNewPedidos : pedidosNew) {
                if (!pedidosOld.contains(pedidosNewPedidos)) {
                    Cliente oldClienteOfPedidosNewPedidos = pedidosNewPedidos.getCliente();
                    pedidosNewPedidos.setCliente(cliente);
                    pedidosNewPedidos = em.merge(pedidosNewPedidos);
                    if (oldClienteOfPedidosNewPedidos != null && !oldClienteOfPedidosNewPedidos.equals(cliente)) {
                        oldClienteOfPedidosNewPedidos.getPedidos().remove(pedidosNewPedidos);
                        oldClienteOfPedidosNewPedidos = em.merge(oldClienteOfPedidosNewPedidos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = cliente.getId();
                if (findCliente(id) == null) {
                    throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.");
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
            Cliente cliente;
            try {
                cliente = em.getReference(Cliente.class, id);
                cliente.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.", enfe);
            }
            Estructurales estructurales = cliente.getEstructurales();
            if (estructurales != null) {
                estructurales.getCliente().remove(cliente);
                estructurales = em.merge(estructurales);
            }
            List<Pago> pagos = cliente.getPagos();
            for (Pago pagosPago : pagos) {
                pagosPago.setCliente(null);
                pagosPago = em.merge(pagosPago);
            }
            List<Pedidos> pedidos = cliente.getPedidos();
            for (Pedidos pedidosPedidos : pedidos) {
                pedidosPedidos.setCliente(null);
                pedidosPedidos = em.merge(pedidosPedidos);
            }
            em.remove(cliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cliente> findClienteEntities() {
        return findClienteEntities(true, -1, -1);
    }

    public List<Cliente> findClienteEntities(int maxResults, int firstResult) {
        return findClienteEntities(false, maxResults, firstResult);
    }

    private List<Cliente> findClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cliente.class));
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

    public Cliente findCliente(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cliente> rt = cq.from(Cliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    //----------------------------------------------------- CLIENTE --------------------------------------------------------//
    
    public Cliente findClienteDni(String dni) {

        String sql = "SELECT Object(c) FROM Cliente c WHERE c.dni= '" + dni + "'";

        try {
            Query query = getEntityManager().createQuery(sql);
            return (Cliente) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Cliente> findAllClienteDni(String dni, boolean baja) {
        String sql = "SELECT Object(c) FROM Cliente c WHERE c.dni LIKE '%" + dni + "%' AND c.baja = '" + baja + "'";
        Query query = getEntityManager().createQuery(sql);
        return query.getResultList();
    }

    public List<Cliente> findAllClienteBaja(boolean baja) {
        String sql = "SELECT Object(c) FROM Cliente c WHERE c.baja= '" + baja + "'";
        Query query = getEntityManager().createQuery(sql);
        return query.getResultList();
    }

    public List<Cliente> findClienteApellido(String apellido, boolean baja) {
        String sql = "SELECT Object(c) FROM Cliente c WHERE c.apellido LIKE '%" + apellido + "%'AND c.baja = '" + baja + "'";
        Query query = getEntityManager().createQuery(sql);
        return query.getResultList();
    }
}
