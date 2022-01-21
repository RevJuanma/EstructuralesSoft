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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author juanm
 */
@Entity
@Table(name = "pagos_de_servicios")
public class PagoDeServicio implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "importe")
    private double importe;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "comentario")
    private String comentario;

    @ManyToOne
    @JoinColumn(name = "estructurales")
    private Estructurales estructurales;

    @ManyToOne
    @JoinColumn(name = "servicio")
    private Servicio servicio;

    public PagoDeServicio() {
    }

    public PagoDeServicio(double importe, Date fecha, String comentario, Estructurales estructurales, Servicio servicio) {
        this.importe = importe;
        this.fecha = fecha;
        this.comentario = comentario;
        this.estructurales = estructurales;
        this.servicio = servicio;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
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
