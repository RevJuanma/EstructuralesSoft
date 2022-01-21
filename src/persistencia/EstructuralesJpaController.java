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
import modelo.Maquinas;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modelo.Cliente;
import modelo.Material;
import modelo.Compra;
import modelo.Estructurales;
import modelo.Pedidos;
import modelo.PagoDeServicio;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author juanm
 */
public class EstructuralesJpaController implements Serializable {

    public EstructuralesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estructurales estructurales) {
        if (estructurales.getMaquina() == null) {
            estructurales.setMaquina(new ArrayList<Maquinas>());
        }
        if (estructurales.getCliente() == null) {
            estructurales.setCliente(new ArrayList<Cliente>());
        }
        if (estructurales.getMateriales() == null) {
            estructurales.setMateriales(new ArrayList<Material>());
        }
        if (estructurales.getCompras() == null) {
            estructurales.setCompras(new ArrayList<Compra>());
        }
        if (estructurales.getPedidos() == null) {
            estructurales.setPedidos(new ArrayList<Pedidos>());
        }
        if (estructurales.getPagoDeServicio() == null) {
            estructurales.setPagoDeServicio(new ArrayList<PagoDeServicio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Maquinas> attachedMaquina = new ArrayList<Maquinas>();
            for (Maquinas maquinaMaquinasToAttach : estructurales.getMaquina()) {
                maquinaMaquinasToAttach = em.getReference(maquinaMaquinasToAttach.getClass(), maquinaMaquinasToAttach.getId());
                attachedMaquina.add(maquinaMaquinasToAttach);
            }
            estructurales.setMaquina(attachedMaquina);
            List<Cliente> attachedCliente = new ArrayList<Cliente>();
            for (Cliente clienteClienteToAttach : estructurales.getCliente()) {
                clienteClienteToAttach = em.getReference(clienteClienteToAttach.getClass(), clienteClienteToAttach.getId());
                attachedCliente.add(clienteClienteToAttach);
            }
            estructurales.setCliente(attachedCliente);
            List<Material> attachedMateriales = new ArrayList<Material>();
            for (Material materialesMaterialToAttach : estructurales.getMateriales()) {
                materialesMaterialToAttach = em.getReference(materialesMaterialToAttach.getClass(), materialesMaterialToAttach.getId());
                attachedMateriales.add(materialesMaterialToAttach);
            }
            estructurales.setMateriales(attachedMateriales);
            List<Compra> attachedCompras = new ArrayList<Compra>();
            for (Compra comprasCompraToAttach : estructurales.getCompras()) {
                comprasCompraToAttach = em.getReference(comprasCompraToAttach.getClass(), comprasCompraToAttach.getId());
                attachedCompras.add(comprasCompraToAttach);
            }
            estructurales.setCompras(attachedCompras);
            List<Pedidos> attachedPedidos = new ArrayList<Pedidos>();
            for (Pedidos pedidosPedidosToAttach : estructurales.getPedidos()) {
                pedidosPedidosToAttach = em.getReference(pedidosPedidosToAttach.getClass(), pedidosPedidosToAttach.getId());
                attachedPedidos.add(pedidosPedidosToAttach);
            }
            estructurales.setPedidos(attachedPedidos);
            List<PagoDeServicio> attachedPagoDeServicio = new ArrayList<PagoDeServicio>();
            for (PagoDeServicio pagoDeServicioPagoDeServicioToAttach : estructurales.getPagoDeServicio()) {
                pagoDeServicioPagoDeServicioToAttach = em.getReference(pagoDeServicioPagoDeServicioToAttach.getClass(), pagoDeServicioPagoDeServicioToAttach.getId());
                attachedPagoDeServicio.add(pagoDeServicioPagoDeServicioToAttach);
            }
            estructurales.setPagoDeServicio(attachedPagoDeServicio);
            em.persist(estructurales);
            for (Maquinas maquinaMaquinas : estructurales.getMaquina()) {
                Estructurales oldEstructuralesOfMaquinaMaquinas = maquinaMaquinas.getEstructurales();
                maquinaMaquinas.setEstructurales(estructurales);
                maquinaMaquinas = em.merge(maquinaMaquinas);
                if (oldEstructuralesOfMaquinaMaquinas != null) {
                    oldEstructuralesOfMaquinaMaquinas.getMaquina().remove(maquinaMaquinas);
                    oldEstructuralesOfMaquinaMaquinas = em.merge(oldEstructuralesOfMaquinaMaquinas);
                }
            }
            for (Cliente clienteCliente : estructurales.getCliente()) {
                Estructurales oldEstructuralesOfClienteCliente = clienteCliente.getEstructurales();
                clienteCliente.setEstructurales(estructurales);
                clienteCliente = em.merge(clienteCliente);
                if (oldEstructuralesOfClienteCliente != null) {
                    oldEstructuralesOfClienteCliente.getCliente().remove(clienteCliente);
                    oldEstructuralesOfClienteCliente = em.merge(oldEstructuralesOfClienteCliente);
                }
            }
            for (Material materialesMaterial : estructurales.getMateriales()) {
                Estructurales oldEstructuralesOfMaterialesMaterial = materialesMaterial.getEstructurales();
                materialesMaterial.setEstructurales(estructurales);
                materialesMaterial = em.merge(materialesMaterial);
                if (oldEstructuralesOfMaterialesMaterial != null) {
                    oldEstructuralesOfMaterialesMaterial.getMateriales().remove(materialesMaterial);
                    oldEstructuralesOfMaterialesMaterial = em.merge(oldEstructuralesOfMaterialesMaterial);
                }
            }
            for (Compra comprasCompra : estructurales.getCompras()) {
                Estructurales oldEstructuralesOfComprasCompra = comprasCompra.getEstructurales();
                comprasCompra.setEstructurales(estructurales);
                comprasCompra = em.merge(comprasCompra);
                if (oldEstructuralesOfComprasCompra != null) {
                    oldEstructuralesOfComprasCompra.getCompras().remove(comprasCompra);
                    oldEstructuralesOfComprasCompra = em.merge(oldEstructuralesOfComprasCompra);
                }
            }
            for (Pedidos pedidosPedidos : estructurales.getPedidos()) {
                Estructurales oldEstructuralesOfPedidosPedidos = pedidosPedidos.getEstructurales();
                pedidosPedidos.setEstructurales(estructurales);
                pedidosPedidos = em.merge(pedidosPedidos);
                if (oldEstructuralesOfPedidosPedidos != null) {
                    oldEstructuralesOfPedidosPedidos.getPedidos().remove(pedidosPedidos);
                    oldEstructuralesOfPedidosPedidos = em.merge(oldEstructuralesOfPedidosPedidos);
                }
            }
            for (PagoDeServicio pagoDeServicioPagoDeServicio : estructurales.getPagoDeServicio()) {
                Estructurales oldEstructuralesOfPagoDeServicioPagoDeServicio = pagoDeServicioPagoDeServicio.getEstructurales();
                pagoDeServicioPagoDeServicio.setEstructurales(estructurales);
                pagoDeServicioPagoDeServicio = em.merge(pagoDeServicioPagoDeServicio);
                if (oldEstructuralesOfPagoDeServicioPagoDeServicio != null) {
                    oldEstructuralesOfPagoDeServicioPagoDeServicio.getPagoDeServicio().remove(pagoDeServicioPagoDeServicio);
                    oldEstructuralesOfPagoDeServicioPagoDeServicio = em.merge(oldEstructuralesOfPagoDeServicioPagoDeServicio);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estructurales estructurales) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estructurales persistentEstructurales = em.find(Estructurales.class, estructurales.getId());
            List<Maquinas> maquinaOld = persistentEstructurales.getMaquina();
            List<Maquinas> maquinaNew = estructurales.getMaquina();
            List<Cliente> clienteOld = persistentEstructurales.getCliente();
            List<Cliente> clienteNew = estructurales.getCliente();
            List<Material> materialesOld = persistentEstructurales.getMateriales();
            List<Material> materialesNew = estructurales.getMateriales();
            List<Compra> comprasOld = persistentEstructurales.getCompras();
            List<Compra> comprasNew = estructurales.getCompras();
            List<Pedidos> pedidosOld = persistentEstructurales.getPedidos();
            List<Pedidos> pedidosNew = estructurales.getPedidos();
            List<PagoDeServicio> pagoDeServicioOld = persistentEstructurales.getPagoDeServicio();
            List<PagoDeServicio> pagoDeServicioNew = estructurales.getPagoDeServicio();
            List<Maquinas> attachedMaquinaNew = new ArrayList<Maquinas>();
            for (Maquinas maquinaNewMaquinasToAttach : maquinaNew) {
                maquinaNewMaquinasToAttach = em.getReference(maquinaNewMaquinasToAttach.getClass(), maquinaNewMaquinasToAttach.getId());
                attachedMaquinaNew.add(maquinaNewMaquinasToAttach);
            }
            maquinaNew = attachedMaquinaNew;
            estructurales.setMaquina(maquinaNew);
            List<Cliente> attachedClienteNew = new ArrayList<Cliente>();
            for (Cliente clienteNewClienteToAttach : clienteNew) {
                clienteNewClienteToAttach = em.getReference(clienteNewClienteToAttach.getClass(), clienteNewClienteToAttach.getId());
                attachedClienteNew.add(clienteNewClienteToAttach);
            }
            clienteNew = attachedClienteNew;
            estructurales.setCliente(clienteNew);
            List<Material> attachedMaterialesNew = new ArrayList<Material>();
            for (Material materialesNewMaterialToAttach : materialesNew) {
                materialesNewMaterialToAttach = em.getReference(materialesNewMaterialToAttach.getClass(), materialesNewMaterialToAttach.getId());
                attachedMaterialesNew.add(materialesNewMaterialToAttach);
            }
            materialesNew = attachedMaterialesNew;
            estructurales.setMateriales(materialesNew);
            List<Compra> attachedComprasNew = new ArrayList<Compra>();
            for (Compra comprasNewCompraToAttach : comprasNew) {
                comprasNewCompraToAttach = em.getReference(comprasNewCompraToAttach.getClass(), comprasNewCompraToAttach.getId());
                attachedComprasNew.add(comprasNewCompraToAttach);
            }
            comprasNew = attachedComprasNew;
            estructurales.setCompras(comprasNew);
            List<Pedidos> attachedPedidosNew = new ArrayList<Pedidos>();
            for (Pedidos pedidosNewPedidosToAttach : pedidosNew) {
                pedidosNewPedidosToAttach = em.getReference(pedidosNewPedidosToAttach.getClass(), pedidosNewPedidosToAttach.getId());
                attachedPedidosNew.add(pedidosNewPedidosToAttach);
            }
            pedidosNew = attachedPedidosNew;
            estructurales.setPedidos(pedidosNew);
            List<PagoDeServicio> attachedPagoDeServicioNew = new ArrayList<PagoDeServicio>();
            for (PagoDeServicio pagoDeServicioNewPagoDeServicioToAttach : pagoDeServicioNew) {
                pagoDeServicioNewPagoDeServicioToAttach = em.getReference(pagoDeServicioNewPagoDeServicioToAttach.getClass(), pagoDeServicioNewPagoDeServicioToAttach.getId());
                attachedPagoDeServicioNew.add(pagoDeServicioNewPagoDeServicioToAttach);
            }
            pagoDeServicioNew = attachedPagoDeServicioNew;
            estructurales.setPagoDeServicio(pagoDeServicioNew);
            estructurales = em.merge(estructurales);
            for (Maquinas maquinaOldMaquinas : maquinaOld) {
                if (!maquinaNew.contains(maquinaOldMaquinas)) {
                    maquinaOldMaquinas.setEstructurales(null);
                    maquinaOldMaquinas = em.merge(maquinaOldMaquinas);
                }
            }
            for (Maquinas maquinaNewMaquinas : maquinaNew) {
                if (!maquinaOld.contains(maquinaNewMaquinas)) {
                    Estructurales oldEstructuralesOfMaquinaNewMaquinas = maquinaNewMaquinas.getEstructurales();
                    maquinaNewMaquinas.setEstructurales(estructurales);
                    maquinaNewMaquinas = em.merge(maquinaNewMaquinas);
                    if (oldEstructuralesOfMaquinaNewMaquinas != null && !oldEstructuralesOfMaquinaNewMaquinas.equals(estructurales)) {
                        oldEstructuralesOfMaquinaNewMaquinas.getMaquina().remove(maquinaNewMaquinas);
                        oldEstructuralesOfMaquinaNewMaquinas = em.merge(oldEstructuralesOfMaquinaNewMaquinas);
                    }
                }
            }
            for (Cliente clienteOldCliente : clienteOld) {
                if (!clienteNew.contains(clienteOldCliente)) {
                    clienteOldCliente.setEstructurales(null);
                    clienteOldCliente = em.merge(clienteOldCliente);
                }
            }
            for (Cliente clienteNewCliente : clienteNew) {
                if (!clienteOld.contains(clienteNewCliente)) {
                    Estructurales oldEstructuralesOfClienteNewCliente = clienteNewCliente.getEstructurales();
                    clienteNewCliente.setEstructurales(estructurales);
                    clienteNewCliente = em.merge(clienteNewCliente);
                    if (oldEstructuralesOfClienteNewCliente != null && !oldEstructuralesOfClienteNewCliente.equals(estructurales)) {
                        oldEstructuralesOfClienteNewCliente.getCliente().remove(clienteNewCliente);
                        oldEstructuralesOfClienteNewCliente = em.merge(oldEstructuralesOfClienteNewCliente);
                    }
                }
            }
            for (Material materialesOldMaterial : materialesOld) {
                if (!materialesNew.contains(materialesOldMaterial)) {
                    materialesOldMaterial.setEstructurales(null);
                    materialesOldMaterial = em.merge(materialesOldMaterial);
                }
            }
            for (Material materialesNewMaterial : materialesNew) {
                if (!materialesOld.contains(materialesNewMaterial)) {
                    Estructurales oldEstructuralesOfMaterialesNewMaterial = materialesNewMaterial.getEstructurales();
                    materialesNewMaterial.setEstructurales(estructurales);
                    materialesNewMaterial = em.merge(materialesNewMaterial);
                    if (oldEstructuralesOfMaterialesNewMaterial != null && !oldEstructuralesOfMaterialesNewMaterial.equals(estructurales)) {
                        oldEstructuralesOfMaterialesNewMaterial.getMateriales().remove(materialesNewMaterial);
                        oldEstructuralesOfMaterialesNewMaterial = em.merge(oldEstructuralesOfMaterialesNewMaterial);
                    }
                }
            }
            for (Compra comprasOldCompra : comprasOld) {
                if (!comprasNew.contains(comprasOldCompra)) {
                    comprasOldCompra.setEstructurales(null);
                    comprasOldCompra = em.merge(comprasOldCompra);
                }
            }
            for (Compra comprasNewCompra : comprasNew) {
                if (!comprasOld.contains(comprasNewCompra)) {
                    Estructurales oldEstructuralesOfComprasNewCompra = comprasNewCompra.getEstructurales();
                    comprasNewCompra.setEstructurales(estructurales);
                    comprasNewCompra = em.merge(comprasNewCompra);
                    if (oldEstructuralesOfComprasNewCompra != null && !oldEstructuralesOfComprasNewCompra.equals(estructurales)) {
                        oldEstructuralesOfComprasNewCompra.getCompras().remove(comprasNewCompra);
                        oldEstructuralesOfComprasNewCompra = em.merge(oldEstructuralesOfComprasNewCompra);
                    }
                }
            }
            for (Pedidos pedidosOldPedidos : pedidosOld) {
                if (!pedidosNew.contains(pedidosOldPedidos)) {
                    pedidosOldPedidos.setEstructurales(null);
                    pedidosOldPedidos = em.merge(pedidosOldPedidos);
                }
            }
            for (Pedidos pedidosNewPedidos : pedidosNew) {
                if (!pedidosOld.contains(pedidosNewPedidos)) {
                    Estructurales oldEstructuralesOfPedidosNewPedidos = pedidosNewPedidos.getEstructurales();
                    pedidosNewPedidos.setEstructurales(estructurales);
                    pedidosNewPedidos = em.merge(pedidosNewPedidos);
                    if (oldEstructuralesOfPedidosNewPedidos != null && !oldEstructuralesOfPedidosNewPedidos.equals(estructurales)) {
                        oldEstructuralesOfPedidosNewPedidos.getPedidos().remove(pedidosNewPedidos);
                        oldEstructuralesOfPedidosNewPedidos = em.merge(oldEstructuralesOfPedidosNewPedidos);
                    }
                }
            }
            for (PagoDeServicio pagoDeServicioOldPagoDeServicio : pagoDeServicioOld) {
                if (!pagoDeServicioNew.contains(pagoDeServicioOldPagoDeServicio)) {
                    pagoDeServicioOldPagoDeServicio.setEstructurales(null);
                    pagoDeServicioOldPagoDeServicio = em.merge(pagoDeServicioOldPagoDeServicio);
                }
            }
            for (PagoDeServicio pagoDeServicioNewPagoDeServicio : pagoDeServicioNew) {
                if (!pagoDeServicioOld.contains(pagoDeServicioNewPagoDeServicio)) {
                    Estructurales oldEstructuralesOfPagoDeServicioNewPagoDeServicio = pagoDeServicioNewPagoDeServicio.getEstructurales();
                    pagoDeServicioNewPagoDeServicio.setEstructurales(estructurales);
                    pagoDeServicioNewPagoDeServicio = em.merge(pagoDeServicioNewPagoDeServicio);
                    if (oldEstructuralesOfPagoDeServicioNewPagoDeServicio != null && !oldEstructuralesOfPagoDeServicioNewPagoDeServicio.equals(estructurales)) {
                        oldEstructuralesOfPagoDeServicioNewPagoDeServicio.getPagoDeServicio().remove(pagoDeServicioNewPagoDeServicio);
                        oldEstructuralesOfPagoDeServicioNewPagoDeServicio = em.merge(oldEstructuralesOfPagoDeServicioNewPagoDeServicio);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = estructurales.getId();
                if (findEstructurales(id) == null) {
                    throw new NonexistentEntityException("The estructurales with id " + id + " no longer exists.");
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
            Estructurales estructurales;
            try {
                estructurales = em.getReference(Estructurales.class, id);
                estructurales.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estructurales with id " + id + " no longer exists.", enfe);
            }
            List<Maquinas> maquina = estructurales.getMaquina();
            for (Maquinas maquinaMaquinas : maquina) {
                maquinaMaquinas.setEstructurales(null);
                maquinaMaquinas = em.merge(maquinaMaquinas);
            }
            List<Cliente> cliente = estructurales.getCliente();
            for (Cliente clienteCliente : cliente) {
                clienteCliente.setEstructurales(null);
                clienteCliente = em.merge(clienteCliente);
            }
            List<Material> materiales = estructurales.getMateriales();
            for (Material materialesMaterial : materiales) {
                materialesMaterial.setEstructurales(null);
                materialesMaterial = em.merge(materialesMaterial);
            }
            List<Compra> compras = estructurales.getCompras();
            for (Compra comprasCompra : compras) {
                comprasCompra.setEstructurales(null);
                comprasCompra = em.merge(comprasCompra);
            }
            List<Pedidos> pedidos = estructurales.getPedidos();
            for (Pedidos pedidosPedidos : pedidos) {
                pedidosPedidos.setEstructurales(null);
                pedidosPedidos = em.merge(pedidosPedidos);
            }
            List<PagoDeServicio> pagoDeServicio = estructurales.getPagoDeServicio();
            for (PagoDeServicio pagoDeServicioPagoDeServicio : pagoDeServicio) {
                pagoDeServicioPagoDeServicio.setEstructurales(null);
                pagoDeServicioPagoDeServicio = em.merge(pagoDeServicioPagoDeServicio);
            }
            em.remove(estructurales);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estructurales> findEstructuralesEntities() {
        return findEstructuralesEntities(true, -1, -1);
    }

    public List<Estructurales> findEstructuralesEntities(int maxResults, int firstResult) {
        return findEstructuralesEntities(false, maxResults, firstResult);
    }

    private List<Estructurales> findEstructuralesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estructurales.class));
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

    public Estructurales findEstructurales(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estructurales.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstructuralesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estructurales> rt = cq.from(Estructurales.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
