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
import javax.persistence.Table;

/**
 *
 * @author juanm
 */
@Entity
@Table(name = "stock")
public class Stock implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "cantidad")
    private int cantidad;
    @Column(name = "cantidad_minima")
    private int cantidadMin;
    @Column(name = "ubicacion")
    private String ubicacion;

    public Stock() {
    }

    public Stock(int cantidad, int cantidadMin, String ubicacion) {
        this.cantidad = cantidad;
        this.cantidadMin = cantidadMin;
        this.ubicacion = ubicacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCantidadMin() {
        return cantidadMin;
    }

    public void setCantidadMin(int cantidadMin) {
        this.cantidadMin = cantidadMin;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}
