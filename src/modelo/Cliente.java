/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author juanm
 */
@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "dni")
    private String dni;
    @Column(name = "baja")
    private boolean baja;

    @ManyToOne
    @JoinColumn(name = "estructurales")
    private Estructurales estructurales;
    
    @OneToOne
    @JoinColumn(name = "contacto")
    private Contacto contacto;

    @OneToMany(mappedBy = "cliente")
    private List<Pago> pagos;
    
    @OneToMany(mappedBy = "cliente")
    private List<Pedidos> pedidos;

    public Cliente() {
    }

    public Cliente(String nombre, String apellido, String dni, Contacto contacto) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.baja = false;
        this.contacto = contacto;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Estructurales getEstructurales() {
        return estructurales;
    }

    public void setEstructurales(Estructurales estructurales) {
        this.estructurales = estructurales;
    }

    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }

    public List<Pago> getPagos() {
        return pagos;
    }

    public void setPagos(List<Pago> pagos) {
        this.pagos = pagos;
    }   

    public List<Pedidos> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedidos> pedidos) {
        this.pedidos = pedidos;
    }

    public boolean isBaja() {
        return baja;
    }

    public void setBaja(boolean baja) {
        this.baja = baja;
    }

    
}
