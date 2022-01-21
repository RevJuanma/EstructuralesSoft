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
@Table(name = "movimientos")
public class Movimiento implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "importe")
    private double importe;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "caja_chica")
    private CajaChica cajaChica;

    public Movimiento() {
    }

    public Movimiento(String tipo, double importe, Date fecha, CajaChica cajaChica) {
        this.tipo = tipo;
        this.importe = importe;
        this.fecha = fecha;
        this.cajaChica = cajaChica;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public CajaChica getCajaChica() {
        return cajaChica;
    }

    public void setCajaChica(CajaChica cajaChica) {
        this.cajaChica = cajaChica;
    }


}
