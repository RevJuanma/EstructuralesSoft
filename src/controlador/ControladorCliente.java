/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.List;
import modelo.Cliente;
import modelo.Contacto;
import persistencia.ClienteJpaController;
import persistencia.ContactoJpaController;
import persistencia.ControladorPersistencia;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author juanm
 */
public class ControladorCliente extends ControladorPersistencia {

    private ClienteJpaController clienteJpa;
    private ContactoJpaController contactoJpa;

    public ControladorCliente() {
        this.clienteJpa = new ClienteJpaController(getEmf());
        this.contactoJpa = new ContactoJpaController(getEmf());
    }

    //----------------------------------------------------- CLIENTE --------------------------------------------------------//
    
    public ClienteJpaController getClienteJpa() {
        return clienteJpa;
    }

    public void setClienteJpa(ClienteJpaController clienteJpa) {
        this.clienteJpa = clienteJpa;
    }

    public Cliente findCliente(int id) {
        return clienteJpa.findCliente(id);
    }

    public List<Cliente> findAllClientes() {
        return clienteJpa.findClienteEntities();
    }

    public void destroyCliente(int id) throws NonexistentEntityException {
        clienteJpa.destroy(id);
    }

    public Cliente findClienteDni(String dni) {
        return clienteJpa.findClienteDni(dni);
    }

    public List<Cliente> findAllClienteDni(String dni, boolean baja) {
        return clienteJpa.findAllClienteDni(dni, baja);
    }

    public List<Cliente> findAllClienteBaja(boolean baja) {
        return clienteJpa.findAllClienteBaja(baja);
    }

    public List<Cliente> findClienteApellido(String apellido, boolean baja) {
        return clienteJpa.findClienteApellido(apellido, baja);
    }

    public void newCliente(String nombre, String apellido, String dni, Contacto contacto) throws Exception {

        Cliente cliente = new Cliente(nombre, apellido, dni, contacto);

        if (clienteJpa.findClienteDni(dni) != null) {
            throw new Exception("El cliente ya está registrado");
        }

        this.clienteJpa.create(cliente);

    }

    public void editCliente(int id, String nombre, String apellido, String dni,
            String tel, String cel, String correo, String mercadoLibre, String olx, String ig, String fb
    ) throws Exception {

        Cliente cliente = this.clienteJpa.findCliente(id);
        Contacto contacto = cliente.getContacto();
        Cliente editCliente = this.clienteJpa.findClienteDni(dni);

        if (editCliente != null) {
            if (editCliente.getId() != cliente.getId()) {
                throw new Exception("El Cliente ya está registrado");
            } else {
                cliente.setNombre(nombre);
                cliente.setApellido(apellido);
                cliente.setDni(dni);
                contacto.setTel(tel);
                contacto.setCel(cel);
                contacto.setCorreo(correo);
                contacto.setMercadoLibre(mercadoLibre);
                contacto.setOlx(olx);
                contacto.setIg(ig);
                contacto.setFb(fb);
                cliente.setContacto(contacto);
                clienteJpa.edit(cliente);
            }
        } else {
            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setDni(dni);
            contacto.setTel(tel);
            contacto.setCel(cel);
            contacto.setCorreo(correo);
            contacto.setMercadoLibre(mercadoLibre);
            contacto.setOlx(olx);
            contacto.setIg(ig);
            contacto.setFb(fb);
            cliente.setContacto(contacto);
            clienteJpa.edit(cliente);
        }
    }

    public void deleteCliente(int id) throws Exception {

        Cliente cliente = this.clienteJpa.findCliente(id);

        cliente.setBaja(true);
        clienteJpa.edit(cliente);

    }

    //----------------------------------------------------- CONTACTO --------------------------------------------------------//
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
    }

}
