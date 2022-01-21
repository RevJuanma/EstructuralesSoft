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
@Table(name = "metodos_de_pago")
public class MetodoDePago implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "cuotas")
    private boolean cuotas;

    @OneToMany(mappedBy = "metodoDePago")
    private List<Pago> pagos;

    public MetodoDePago() {
    }

    public MetodoDePago(String nombre, boolean cuotas) {
        this.nombre = nombre;
        this.cuotas = cuotas;
    }

    public boolean getCuotas() {
        return cuotas;
    }

    public void setCuotas(boolean cuotas) {
        this.cuotas = cuotas;
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

    public List<Pago> getPagos() {
        return pagos;
    }

    public void setPagos(List<Pago> pagos) {
        this.pagos = pagos;
    }

    @Override
    public String toString() {
        return nombre;
    }

}
