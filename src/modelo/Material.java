/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.CascadeType;
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
@Table(name = "material")
public class Material implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "contenido")
    private String contenido;
    @Column(name = "color")
    private String color;

    @ManyToOne
    @JoinColumn(name = "estructurales")
    private Estructurales estructurales;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "stock")
    private Stock stock;

    @ManyToOne
    @JoinColumn(name = "marca_de_material")
    private MarcaMaterial marcaMaterial;

    @ManyToOne
    @JoinColumn(name = "unidad_de_medida")
    private UnidadDeMedida unidadDeMedida;

    public Material() {
    }

    public Material(String nombre, String tipo, String descripcion, String contenido, String color, Stock stock, MarcaMaterial marcap) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.contenido = contenido;
        this.color = color;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Estructurales getEstructurales() {
        return estructurales;
    }

    public void setEstructurales(Estructurales estructurales) {
        this.estructurales = estructurales;
    }

    public MarcaMaterial getMarcaMaterial() {
        return marcaMaterial;
    }

    public void setMarcaMaterial(MarcaMaterial marcaMaterial) {
        this.marcaMaterial = marcaMaterial;
    }

}
