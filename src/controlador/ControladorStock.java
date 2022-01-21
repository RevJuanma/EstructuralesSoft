/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.List;
import modelo.Maquinas;
import modelo.Medidas;
import modelo.UnidadDeMedida;
import persistencia.ControladorPersistencia;
import persistencia.MaquinasJpaController;
import persistencia.MedidasJpaController;
import persistencia.UnidadDeMedidaJpaController;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author juanm
 */
public class ControladorStock extends ControladorPersistencia {

    private UnidadDeMedidaJpaController unidMedJpa;
    private MaquinasJpaController maquinaJpa;
    private MedidasJpaController medidaJpa;

    public ControladorStock() {
        this.unidMedJpa = new UnidadDeMedidaJpaController(getEmf());
        this.maquinaJpa = new MaquinasJpaController(getEmf());
        this.medidaJpa = new MedidasJpaController(getEmf());
    }

    //----------------------------------------------------- UNIDAD MEDIDA --------------------------------------------------------//

    public UnidadDeMedidaJpaController getUnidadDeMedidaJpa() {
        return unidMedJpa;
    }

    public void setUnidadDeMedidaJpa(UnidadDeMedidaJpaController unidMedJpa) {
        this.unidMedJpa = unidMedJpa;
    }

    public UnidadDeMedida findUnidadDeMedida(int id) {
        return unidMedJpa.findUnidadDeMedida(id);
    }
    
    public UnidadDeMedida findUnidadDeMedidaNombre(String nombre) {
        return unidMedJpa.findUnidMedNombre(nombre);
    }

    public List<UnidadDeMedida> findAllUnidadDeMedida() {
        return unidMedJpa.findUnidadDeMedidaEntities();
    }
   

    public void destroyUnidadDeMedida(int id) throws NonexistentEntityException {
        unidMedJpa.destroy(id);
    }

    public void newUnidMedida(String nombre) throws Exception {

        UnidadDeMedida um = new UnidadDeMedida(nombre);

        if (unidMedJpa.findUnidMedNombre(nombre) != null) {
            throw new Exception("La Unidad de Medida ya está registrada");
        }

        this.unidMedJpa.create(um);

    }
    
    public UnidadDeMedida editUnidadDeMedida(int id, String nombre) throws Exception {

        UnidadDeMedida um = unidMedJpa.findUnidadDeMedida(id);
        if (unidMedJpa.findUnidMedNombre(nombre) != null) {
            throw new Exception("La Unidad de Medida ya está registrada");
        }
        um.setNombre(nombre);

        this.unidMedJpa.edit(um);

        return um;
    }

    //----------------------------------------------------- MEDIDA --------------------------------------------------------//
    public MedidasJpaController getMedidasJpa() {
        return medidaJpa;
    }

    public void setMedidasJpa(MedidasJpaController medidaJpa) {
        this.medidaJpa = medidaJpa;
    }

    public Medidas findMedidas(int id) {
        return medidaJpa.findMedidas(id);
    }

    public List<Medidas> findAllMedidas() {
        return medidaJpa.findMedidasEntities();
    }
    
    public List<Medidas> findAllMedidasMaquina(int id) {
        return medidaJpa.findAllMedidasMaquina(id);
    }

    public void destroyMedidas(int id) throws NonexistentEntityException {
        medidaJpa.destroy(id);
    }

    public void newMedidas(String nombre, String cantidad, Maquinas maquina, UnidadDeMedida unidMed) throws Exception {
        Medidas medida = new Medidas(nombre, cantidad, maquina, unidMed);
        this.medidaJpa.create(medida);
    }

    //----------------------------------------------------- MAQUINAS --------------------------------------------------------//
   
    public MaquinasJpaController getMaquinasJpa() {
        return maquinaJpa;
    }

    public void setMaquinasJpa(MaquinasJpaController maquinaJpa) {
        this.maquinaJpa = maquinaJpa;
    }

    public Maquinas findMaquinas(int id) {
        return maquinaJpa.findMaquinas(id);
    }
    
    public Maquinas findMaquinaCod(String cod) {
        return maquinaJpa.findMaquinaCod(cod);
    }

    public List<Maquinas> findAllMaquinas() {
        return maquinaJpa.findMaquinasEntities();
    }

    public void destroyMaquinas(int id) throws NonexistentEntityException {
        maquinaJpa.destroy(id);
    }

    public Maquinas newMaquina(String nombre, String cod) throws Exception {
        Maquinas maquina = new Maquinas(nombre, cod);

        if (maquinaJpa.findMaquinaCod(cod) != null) {
            throw new Exception("La Máquina ya está registrada");
        }

        this.maquinaJpa.create(maquina);
        
        return maquina;
    }
}
