/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author juanm
 */
@Entity
@Table(name = "estructurales")
public class Estructurales implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "tel")
    private String tel;
    @Column(name = "cel")
    private String cel;
    @Column(name = "cuit")
    private String cuit;
    @Column(name = "clave_de_seguridad")
    private String claveSeguridad;
    @Column(name = "codigo_postal")
    private String codPostal;
    @Column(name = "ciudad")
    private String ciudad;
    @Column(name = "provincia")
    private String provincia;

    @OneToOne
    @JoinColumn(name = "caja_chica")
    private CajaChica cajaChica;

    @OneToMany(mappedBy = "estructurales", cascade = CascadeType.REMOVE)
    private List<Maquinas> maquina;

    @OneToMany(mappedBy = "estructurales", cascade = CascadeType.REMOVE)
    private List<Cliente> cliente;

    @OneToMany(mappedBy = "estructurales", cascade = CascadeType.REMOVE)
    private List<Material> materiales;

    @OneToMany(mappedBy = "estructurales", cascade = CascadeType.REMOVE)
    private List<Compra> compras;

    @OneToMany(mappedBy = "estructurales", cascade = CascadeType.REMOVE)
    private List<Pedidos> pedidos;

    @OneToMany(mappedBy = "estructurales", cascade = CascadeType.REMOVE)
    private List<PagoDeServicio> pagoDeServicio;

    public Estructurales() {
    }

    public Estructurales(String nombre, String direccion, String tel, String cuit, String claveSeguridad, String ciudad, String provincia) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.tel = tel;
        this.cuit = cuit;
        this.claveSeguridad = claveSeguridad;
        this.ciudad = ciudad;
        this.provincia = provincia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCel() {
        return cel;
    }

    public void setCel(String cel) {
        this.cel = cel;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClaveSeguridad() {
        return claveSeguridad;
    }

    public void setClaveSeguridad(String claveSeguridad) {
        this.claveSeguridad = claveSeguridad;
    }

    public String getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(String codPostal) {
        this.codPostal = codPostal;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public List<Maquinas> getMaquina() {
        return maquina;
    }

    public void setMaquina(List<Maquinas> maquina) {
        this.maquina = maquina;
    }

    public CajaChica getCajaChica() {
        return cajaChica;
    }

    public void setCajaChica(CajaChica cajaChica) {
        this.cajaChica = cajaChica;
    }

    public List<Cliente> getCliente() {
        return cliente;
    }

    public void setCliente(List<Cliente> cliente) {
        this.cliente = cliente;
    }

    public List<Material> getMateriales() {
        return materiales;
    }

    public void setMateriales(List<Material> materiales) {
        this.materiales = materiales;
    }

    public List<Compra> getCompras() {
        return compras;
    }

    public void setCompras(List<Compra> compras) {
        this.compras = compras;
    }

    public List<Pedidos> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedidos> pedidos) {
        this.pedidos = pedidos;
    }

    public List<PagoDeServicio> getPagoDeServicio() {
        return pagoDeServicio;
    }

    public void setPagoDeServicio(List<PagoDeServicio> pagoDeServicio) {
        this.pagoDeServicio = pagoDeServicio;
    }

    
}
