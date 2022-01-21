/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.Date;
import modelo.Estructurales;
import persistencia.ControladorPersistencia;
import persistencia.EstructuralesJpaController;

/**
 *
 * @author juanm
 */
public class ControladorLogin extends ControladorPersistencia {


    private EstructuralesJpaController estructuralesJpa;
    

    /*
     */
    public ControladorLogin() {

        this.estructuralesJpa = new EstructuralesJpaController(getEmf());
    }

    public EstructuralesJpaController getTallerJpa() {
        return estructuralesJpa;
    }

    public void setEstructuralesJpa(EstructuralesJpaController tallerJpa) {
        this.estructuralesJpa = tallerJpa;
    }

    public void editarEstructurales(String nombre, String direccion, String tel, String cel, String ciudad, String provincia, String codPos, Date iniAct, String cuit, String ingBrutos) throws Exception {

        Estructurales estructurales = this.estructuralesJpa.findEstructurales(1);

        estructurales.setNombre(nombre);
        estructurales.setDireccion(direccion);
        estructurales.setTel(tel);
        estructurales.setCel(cel);
        estructurales.setCiudad(ciudad);
        estructurales.setProvincia(provincia);
        estructurales.setCodPostal(codPos);
        estructurales.setCuit(cuit);

        estructuralesJpa.edit(estructurales);
    }

    public void registrarEstructurales(String nombre, String direc, String tel, String cel, String ciudad, String provincia, String codPos, Date iniAct, String cuit, String claveSeguridad, String ingBrutos) throws Exception {

        Estructurales taller = new Estructurales(nombre, direc, tel, cuit, claveSeguridad, ciudad, provincia);

        taller.setCel(cel);
        taller.setCodPostal(codPos);

        estructuralesJpa.create(taller);
    }

    public void cambiarPass(String pass) throws Exception {

        Estructurales taller = this.estructuralesJpa.findEstructurales(1);

        taller.setClaveSeguridad(pass);

        estructuralesJpa.edit(taller);
    }

}
