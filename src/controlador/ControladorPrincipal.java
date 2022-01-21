/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import vista.frmPrincipal;

public class ControladorPrincipal {

    ControladorLogin cl;
    ControladorCliente cc;
    ControladorStock cs;

    public ControladorPrincipal() {
        cl = new ControladorLogin();
        cc = new ControladorCliente();
        cs = new ControladorStock();
    }

    public static void main(String[] args) {
        ControladorPrincipal cp = new ControladorPrincipal();

        frmPrincipal inicio = new frmPrincipal(cp);
        inicio.setVisible(true);
    }

    public void abrirVentana(JInternalFrame ventana, JDesktopPane escritorio) {

        escritorio.removeAll();

        try {
            escritorio.add(ventana);
            ventana.setMaximum(true);
            ventana.show();

        } catch (Exception e) {
        }
    }

    public ControladorLogin getCl() {
        return cl;
    }

    public ControladorCliente getCc() {
        return cc;
    }

    public ControladorStock getCs() {
        return cs;
    }
}
