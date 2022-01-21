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
import javax.persistence.Table;

/**
 *
 * @author juanm
 */
@Entity
@Table(name = "medidas")
public class Medidas implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "cantidad")
    private String cantidad;

    @ManyToOne()
    @JoinColumn(name = "maquinas")
    private Maquinas maquinas;

    @ManyToOne
    @JoinColumn(name = "unidad_de_medida")
    private UnidadDeMedida unidadDeMedida;
    
    public Medidas() {
    }

    public Medidas(String nombre, String cantidad, Maquinas maquinas, UnidadDeMedida unidadDeMedida) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.maquinas = maquinas;
        this.unidadDeMedida = unidadDeMedida;
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

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public Maquinas getMaquinas() {
        return maquinas;
    }

    public void setMaquinas(Maquinas maquinas) {
        this.maquinas = maquinas;
    }

    public UnidadDeMedida getUnidadDeMedida() {
        return unidadDeMedida;
    }

    public void setUnidadDeMedida(UnidadDeMedida unidadDeMedida) {
        this.unidadDeMedida = unidadDeMedida;
    }
  
}
