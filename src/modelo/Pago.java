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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author juanm
 */
@Entity
@Table(name = "pagos")
public class Pago implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "monto_total")
    private double montoTotal;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "estado")
    private String estado;
    @Column(name = "interes")
    private int interes;
    @Column(name = "descuento")
    private double descuento;

    @ManyToOne
    @JoinColumn(name = "cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "metodo_de_pago")
    private MetodoDePago metodoDePago;
    
    @OneToMany(mappedBy = "pago", cascade = CascadeType.REMOVE)
    private List<Cuota> cuotas;
    
    @OneToOne
    @JoinColumn(name = "pedidos")
    private Pedidos pedidos;
    
    public Pago() {
    }

    public Pago(double montoTotal, Date fecha, String estado, int interes, double descuento, Cliente cliente, MetodoDePago metodoDePago) {
        this.montoTotal = montoTotal;
        this.fecha = fecha;
        this.estado = estado;
        this.interes = interes;
        this.descuento = descuento;
        this.cliente = cliente;
        this.metodoDePago = metodoDePago;
    }

    public int getId() {
        return id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getInteres() {
        return interes;
    }

    public void setInteres(int interes) {
        this.interes = interes;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }    

    public void setId(int id) {
        this.id = id;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public MetodoDePago getMetodoDePago() {
        return metodoDePago;
    }

    public void setMetodoDePago(MetodoDePago metodoDePago) {
        this.metodoDePago = metodoDePago;
    }

    public List<Cuota> getCuotas() {
        return cuotas;
    }

    public void setCuotas(List<Cuota> cuotas) {
        this.cuotas = cuotas;
    }

    public Pedidos getPedidos() {
        return pedidos;
    }

    public void setPedidos(Pedidos pedidos) {
        this.pedidos = pedidos;
    }

}
