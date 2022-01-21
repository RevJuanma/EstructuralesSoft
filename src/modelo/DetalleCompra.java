/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author juanm
 */
@Entity
@Table(name = "detalles_de_compras")
public class DetalleCompra implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "costo")
    private double costo;
    @Column(name = "cantidad")
    private int cantidad;
    
    @ManyToOne
    @JoinColumn(name="compra")
    private Compra compra;
    
    @OneToOne()
    @JoinColumn(name = "material")
    private Material materiales;

    public DetalleCompra() {
    }

    public DetalleCompra(double costo, int cantidad, Compra compra, Material material) {
        this.costo = costo;
        this.cantidad = cantidad;
        this.compra = compra;
        this.materiales = material;
    }
 
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Material getMateriales() {
        return materiales;
    }

    public void setMateriales(Material materiales) {
        this.materiales = materiales;
    }

    
}
