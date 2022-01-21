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
import modelo.Medidas;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modelo.Maquinas;
import modelo.Pedidos;
import javax.persistence.NoResultException;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author juanm
 */
public class MaquinasJpaController implements Serializable {

    public MaquinasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Maquinas maquinas) {
        if (maquinas.getMedidas() == null) {
            maquinas.setMedidas(new ArrayList<Medidas>());
        }
        if (maquinas.getPedidos() == null) {
            maquinas.setPedidos(new ArrayList<Pedidos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estructurales estructurales = maquinas.getEstructurales();
            if (estructurales != null) {
                estructurales = em.getReference(estructurales.getClass(), estructurales.getId());
                maquinas.setEstructurales(estructurales);
            }
            List<Medidas> attachedMedidas = new ArrayList<Medidas>();
            for (Medidas medidasMedidasToAttach : maquinas.getMedidas()) {
                medidasMedidasToAttach = em.getReference(medidasMedidasToAttach.getClass(), medidasMedidasToAttach.getId());
                attachedMedidas.add(medidasMedidasToAttach);
            }
            maquinas.setMedidas(attachedMedidas);
            List<Pedidos> attachedPedidos = new ArrayList<Pedidos>();
            for (Pedidos pedidosPedidosToAttach : maquinas.getPedidos()) {
                pedidosPedidosToAttach = em.getReference(pedidosPedidosToAttach.getClass(), pedidosPedidosToAttach.getId());
                attachedPedidos.add(pedidosPedidosToAttach);
            }
            maquinas.setPedidos(attachedPedidos);
            em.persist(maquinas);
            if (estructurales != null) {
                estructurales.getMaquina().add(maquinas);
                estructurales = em.merge(estructurales);
            }
            for (Medidas medidasMedidas : maquinas.getMedidas()) {
                Maquinas oldMaquinasOfMedidasMedidas = medidasMedidas.getMaquinas();
                medidasMedidas.setMaquinas(maquinas);
                medidasMedidas = em.merge(medidasMedidas);
                if (oldMaquinasOfMedidasMedidas != null) {
                    oldMaquinasOfMedidasMedidas.getMedidas().remove(medidasMedidas);
                    oldMaquinasOfMedidasMedidas = em.merge(oldMaquinasOfMedidasMedidas);
                }
            }
            for (Pedidos pedidosPedidos : maquinas.getPedidos()) {
                Maquinas oldMaquinasOfPedidosPedidos = pedidosPedidos.getMaquinas();
                pedidosPedidos.setMaquinas(maquinas);
                pedidosPedidos = em.merge(pedidosPedidos);
                if (oldMaquinasOfPedidosPedidos != null) {
                    oldMaquinasOfPedidosPedidos.getPedidos().remove(pedidosPedidos);
                    oldMaquinasOfPedidosPedidos = em.merge(oldMaquinasOfPedidosPedidos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Maquinas maquinas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Maquinas persistentMaquinas = em.find(Maquinas.class, maquinas.getId());
            Estructurales estructuralesOld = persistentMaquinas.getEstructurales();
            Estructurales estructuralesNew = maquinas.getEstructurales();
            List<Medidas> medidasOld = persistentMaquinas.getMedidas();
            List<Medidas> medidasNew = maquinas.getMedidas();
            List<Pedidos> pedidosOld = persistentMaquinas.getPedidos();
            List<Pedidos> pedidosNew = maquinas.getPedidos();
            if (estructuralesNew != null) {
                estructuralesNew = em.getReference(estructuralesNew.getClass(), estructuralesNew.getId());
                maquinas.setEstructurales(estructuralesNew);
            }
            List<Medidas> attachedMedidasNew = new ArrayList<Medidas>();
            for (Medidas medidasNewMedidasToAttach : medidasNew) {
                medidasNewMedidasToAttach = em.getReference(medidasNewMedidasToAttach.getClass(), medidasNewMedidasToAttach.getId());
                attachedMedidasNew.add(medidasNewMedidasToAttach);
            }
            medidasNew = attachedMedidasNew;
            maquinas.setMedidas(medidasNew);
            List<Pedidos> attachedPedidosNew = new ArrayList<Pedidos>();
            for (Pedidos pedidosNewPedidosToAttach : pedidosNew) {
                pedidosNewPedidosToAttach = em.getReference(pedidosNewPedidosToAttach.getClass(), pedidosNewPedidosToAttach.getId());
                attachedPedidosNew.add(pedidosNewPedidosToAttach);
            }
            pedidosNew = attachedPedidosNew;
            maquinas.setPedidos(pedidosNew);
            maquinas = em.merge(maquinas);
            if (estructuralesOld != null && !estructuralesOld.equals(estructuralesNew)) {
                estructuralesOld.getMaquina().remove(maquinas);
                estructuralesOld = em.merge(estructuralesOld);
            }
            if (estructuralesNew != null && !estructuralesNew.equals(estructuralesOld)) {
                estructuralesNew.getMaquina().add(maquinas);
                estructuralesNew = em.merge(estructuralesNew);
            }
            for (Medidas medidasOldMedidas : medidasOld) {
                if (!medidasNew.contains(medidasOldMedidas)) {
                    medidasOldMedidas.setMaquinas(null);
                    medidasOldMedidas = em.merge(medidasOldMedidas);
                }
            }
            for (Medidas medidasNewMedidas : medidasNew) {
                if (!medidasOld.contains(medidasNewMedidas)) {
                    Maquinas oldMaquinasOfMedidasNewMedidas = medidasNewMedidas.getMaquinas();
                    medidasNewMedidas.setMaquinas(maquinas);
                    medidasNewMedidas = em.merge(medidasNewMedidas);
                    if (oldMaquinasOfMedidasNewMedidas != null && !oldMaquinasOfMedidasNewMedidas.equals(maquinas)) {
                        oldMaquinasOfMedidasNewMedidas.getMedidas().remove(medidasNewMedidas);
                        oldMaquinasOfMedidasNewMedidas = em.merge(oldMaquinasOfMedidasNewMedidas);
                    }
                }
            }
            for (Pedidos pedidosOldPedidos : pedidosOld) {
                if (!pedidosNew.contains(pedidosOldPedidos)) {
                    pedidosOldPedidos.setMaquinas(null);
                    pedidosOldPedidos = em.merge(pedidosOldPedidos);
                }
            }
            for (Pedidos pedidosNewPedidos : pedidosNew) {
                if (!pedidosOld.contains(pedidosNewPedidos)) {
                    Maquinas oldMaquinasOfPedidosNewPedidos = pedidosNewPedidos.getMaquinas();
                    pedidosNewPedidos.setMaquinas(maquinas);
                    pedidosNewPedidos = em.merge(pedidosNewPedidos);
                    if (oldMaquinasOfPedidosNewPedidos != null && !oldMaquinasOfPedidosNewPedidos.equals(maquinas)) {
                        oldMaquinasOfPedidosNewPedidos.getPedidos().remove(pedidosNewPedidos);
                        oldMaquinasOfPedidosNewPedidos = em.merge(oldMaquinasOfPedidosNewPedidos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = maquinas.getId();
                if (findMaquinas(id) == null) {
                    throw new NonexistentEntityException("The maquinas with id " + id + " no longer exists.");
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
            Maquinas maquinas;
            try {
                maquinas = em.getReference(Maquinas.class, id);
                maquinas.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The maquinas with id " + id + " no longer exists.", enfe);
            }
            Estructurales estructurales = maquinas.getEstructurales();
            if (estructurales != null) {
                estructurales.getMaquina().remove(maquinas);
                estructurales = em.merge(estructurales);
            }
            List<Medidas> medidas = maquinas.getMedidas();
            for (Medidas medidasMedidas : medidas) {
                medidasMedidas.setMaquinas(null);
                medidasMedidas = em.merge(medidasMedidas);
            }
            List<Pedidos> pedidos = maquinas.getPedidos();
            for (Pedidos pedidosPedidos : pedidos) {
                pedidosPedidos.setMaquinas(null);
                pedidosPedidos = em.merge(pedidosPedidos);
            }
            em.remove(maquinas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Maquinas> findMaquinasEntities() {
        return findMaquinasEntities(true, -1, -1);
    }

    public List<Maquinas> findMaquinasEntities(int maxResults, int firstResult) {
        return findMaquinasEntities(false, maxResults, firstResult);
    }

    private List<Maquinas> findMaquinasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Maquinas.class));
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

    public Maquinas findMaquinas(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Maquinas.class, id);
        } finally {
            em.close();
        }
    }

    public int getMaquinasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Maquinas> rt = cq.from(Maquinas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    //----------------------------------------------------- MAQUINAS --------------------------------------------------------//
    
    public Maquinas findMaquinaCod(String cod) {

        String sql = "SELECT Object(m) FROM Maquinas m WHERE m.cod = '" + cod + "'";

        try {
            Query query = getEntityManager().createQuery(sql);
            return (Maquinas) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Maquinas> findAllMaquinasCod(String cod, boolean baja) {
        String sql = "SELECT Object(m) FROM Maquinas m WHERE m.cod LIKE '%" + cod + "%' AND m.baja = '" + baja + "'";
        Query query = getEntityManager().createQuery(sql);
        return query.getResultList();
    }

    public List<Maquinas> findAllMaquinasBaja(boolean baja) {
        String sql = "SELECT Object(m) FROM Maquinas m WHERE m.baja= '" + baja + "'";
        Query query = getEntityManager().createQuery(sql);
        return query.getResultList();
    }

    public List<Maquinas> findMaquinasNombre(String nombre, boolean baja) {
        String sql = "SELECT Object(m) FROM Maquinas m WHERE m.nombre LIKE '%" + nombre + "%'AND c.baja = '" + baja + "'";
        Query query = getEntityManager().createQuery(sql);
        return query.getResultList();
    }

}
