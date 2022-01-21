/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.List;
import modelo.Cliente;
import modelo.Contacto;
import modelo.Estructurales;
import modelo.Maquinas;
import modelo.Medidas;
import persistencia.ClienteJpaController;
import persistencia.ContactoJpaController;
import persistencia.ControladorPersistencia;
import persistencia.MaquinasJpaController;
import persistencia.MedidasJpaController;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author juanm
 */
public class ControladorMaquina extends ControladorPersistencia {

    private MaquinasJpaController maquinaJpa;
    private MedidasJpaController medidaJpa;

    public ControladorMaquina() {
        this.maquinaJpa = new MaquinasJpaController(getEmf());
        this.medidaJpa = new MedidasJpaController(getEmf());
    }

    //----------------------------------------------------- CLIENTE --------------------------------------------------------//
    public MaquinasJpaController getMaquinaJpa() {
        return maquinaJpa;
    }

    public void setMaquinaJpa(MaquinasJpaController maquinaJpa) {
        this.maquinaJpa = maquinaJpa;
    }

    public Maquinas findMaquina(int id) {
        return maquinaJpa.findMaquinas(id);
    }

    public List<Maquinas> findAllMaquinas() {
        return maquinaJpa.findMaquinasEntities();
    }

    public void destroyMaquinas(int id) throws NonexistentEntityException {
        maquinaJpa.destroy(id);
    }

    public Maquinas findMaquinaCod(String cod) {
        return maquinaJpa.findMaquinaCod(cod);
    }

    public List<Maquinas> findAllMaquinasCod(String cod, boolean baja) {
        return maquinaJpa.findAllMaquinasCod(cod, baja);
    }

    public List<Maquinas> findAllMaquinasBaja(boolean baja) {
        return maquinaJpa.findAllMaquinasBaja(baja);
    }

    public List<Maquinas> findMaquinasNombre(String nombre, boolean baja) {
        return maquinaJpa.findMaquinasNombre(nombre, baja);
    }

    public void newMaquina(String nombre, String cod, boolean baja, List<Medidas> medidas, Estructurales estructurales) throws Exception {

        Maquinas maquina = new Maquinas(nombre, cod, baja, estructurales, medidas);

        if (maquinaJpa.findMaquinaCod(cod) != null) {
            throw new Exception("La Máquina ya está registrada");
        }
        this.maquinaJpa.create(maquina);
    }

    public void deleteMaquina(int id) throws Exception {

        Maquinas maquina = this.maquinaJpa.findMaquinas(id);

        maquina.setBaja(true);
        maquinaJpa.edit(maquina);

    }

    //----------------------------------------------------- CONTACTO --------------------------------------------------------//
    /*
    public ContactoJpaController getContactoJpa() {
        return contactoJpa;
    }

    public void setContactoJpa(ContactoJpaController contactoJpa) {
        this.contactoJpa = contactoJpa;
    }

    public Contacto findContacto(int id) {
        return contactoJpa.findContacto(id);
    }

    public List<Contacto> findAllContacto() {
        return contactoJpa.findContactoEntities();
    }

    public void destroyContacto(int id) throws NonexistentEntityException {
        contactoJpa.destroy(id);
    }

    public Contacto newContacto(String tel, String cel, String correo, String mercadoLibre, String olx, String ig, String fb) {

        Contacto contacto = new Contacto(tel, cel, correo, mercadoLibre, olx, ig, fb);

        this.contactoJpa.create(contacto);

        return contacto;
    }*/
}
