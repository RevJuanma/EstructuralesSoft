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
@Table(name = "contacto")
public class Contacto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "tel")
    private String tel;
    @Column(name = "cel")
    private String cel;
    @Column(name = "correo")
    private String correo;
    @Column(name = "mercado_libre")
    private String mercadoLibre;
    @Column(name = "olx")
    private String olx;
    @Column(name = "ig")
    private String ig;
    @Column(name = "fb")
    private String fb;

    public Contacto() {
    }

    public Contacto(String tel, String cel, String correo, String mercadoLibre, String olx, String ig, String fb) {
        this.tel = tel;
        this.cel = cel;
        this.correo = correo;
        this.mercadoLibre = mercadoLibre;
        this.olx = olx;
        this.ig = ig;
        this.fb = fb;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getMercadoLibre() {
        return mercadoLibre;
    }

    public void setMercadoLibre(String mercadoLibre) {
        this.mercadoLibre = mercadoLibre;
    }

    public String getOlx() {
        return olx;
    }

    public void setOlx(String olx) {
        this.olx = olx;
    }

    public String getIg() {
        return ig;
    }

    public void setIg(String ig) {
        this.ig = ig;
    }

    public String getFb() {
        return fb;
    }

    public void setFb(String fb) {
        this.fb = fb;
    }

}
