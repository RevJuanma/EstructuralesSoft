/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author juanm
 */
@Entity
@Table(name = "pedidos")
public class Pedidos implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "estado")
    private String estado;
    @Column(name = "senia")
    private double senia;
    @Column(name = "costo")
    private double costo;
    @Column(name = "tienda")
    private String tienda;
    @Column(name = "fecha")
    private Date fecha;
    @Column(name = "fecha_entrega")
    private Date fechaEntrega;
    @Column(name = "detalle")
    private String detalle;
    @Column(name = "color")
    private String color;

    @ManyToOne
    @JoinColumn(name = "estructurales")
    private Estructurales estructurales;

    @ManyToOne
    @JoinColumn(name = "maquinas")
    private Maquinas maquinas;

    @ManyToOne
    @JoinColumn(name = "cliente")
    private Cliente cliente;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getSenia() {
        return senia;
    }

    public void setSenia(double senia) {
        this.senia = senia;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getTienda() {
        return tienda;
    }

    public void setTienda(String tienda) {
        this.tienda = tienda;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Maquinas getMaquinas() {
        return maquinas;
    }

    public void setMaquinas(Maquinas maquinas) {
        this.maquinas = maquinas;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Estructurales getEstructurales() {
        return estructurales;
    }

    public void setEstructurales(Estructurales estructurales) {
        this.estructurales = estructurales;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    
}
