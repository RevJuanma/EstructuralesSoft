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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author juanm
 */
@Entity
@Table(name = "servicios")
public class Servicio implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "nombre")
    private String nombre;

    @OneToMany(mappedBy = "servicio")
    private List<PagoDeServicio> pagoDeServicio;

    public Servicio() {
    }

    public Servicio(String nombre) {
        this.nombre = nombre;
    }

    public List<PagoDeServicio> getPagoDeServicio() {
        return pagoDeServicio;
    }

    public void setPagoDeServicio(List<PagoDeServicio> pagoDeServicio) {
        this.pagoDeServicio = pagoDeServicio;
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

    @Override
    public String toString() {
        return nombre;
    }
}
