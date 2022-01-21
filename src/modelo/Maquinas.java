/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author juanm
 */
@Entity
@Table(name = "maquinas")
public class Maquinas implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "cod")
    private String cod;
    @Column(name = "baja")
    private boolean baja;

    @ManyToOne
    @JoinColumn(name = "estructurales")
    private Estructurales estructurales;

    @OneToMany(mappedBy = "maquinas", cascade = CascadeType.ALL)
    private List<Medidas> medidas;

    @OneToMany(mappedBy = "maquinas", cascade = CascadeType.REMOVE)
    private List<Pedidos> pedidos;

    public Maquinas() {
    }

    public Maquinas(String nombre, String cod, boolean baja, Estructurales estructurales, List<Medidas> medidas) {
        this.nombre = nombre;
        this.cod = cod;
        this.baja = baja;
        this.estructurales = estructurales;
        this.medidas = medidas;
    }

    public Maquinas(String nombre, String cod) {
        this.nombre = nombre;
        this.cod = cod;
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

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public Estructurales getEstructurales() {
        return estructurales;
    }

    public void setEstructurales(Estructurales estructurales) {
        this.estructurales = estructurales;
    }

    public List<Medidas> getMedidas() {
        return medidas;
    }

    public void setMedidas(List<Medidas> medidas) {
        this.medidas = medidas;
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
