/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author juanm
 */
@Entity
@Table(name = "compras")
public class Compra implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "costo_total")
    private double total;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "estructurales")
    private Estructurales estructurales;


    public Compra() {
    }

    public Compra(double total, Date fecha, Estructurales taller) {
        this.total = total;
        this.fecha = fecha;
        this.estructurales = estructurales;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Estructurales getEstructurales() {
        return estructurales;
    }

    public void setEstructurales(Estructurales estructurales) {
        this.estructurales = estructurales;
    }

    
}
