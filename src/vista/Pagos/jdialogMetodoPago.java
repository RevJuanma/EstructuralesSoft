/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.Pagos;

import controlador.ControladorPrincipal;
import javax.swing.JOptionPane;
import vista.Pedido.ifrmPedido;

/**
 *
 * @author juanm
 */
public class jdialogMetodoPago extends javax.swing.JDialog {

    ControladorPrincipal cp;
    ifrmPedido vp;
    String opcion;

    /**
     * Creates new form jdialogMetodoPago
     */
    public jdialogMetodoPago(java.awt.Frame parent, boolean modal, ControladorPrincipal cp, ifrmPedido vp, boolean select) {
        super(parent, modal);
        initComponents();
        this.cp = cp;
        this.vp = vp;
        setLocationRelativeTo(null);
        btnSeleccionar.setVisible(select);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnModificar = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        pnlControl = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        chkCuotas = new javax.swing.JCheckBox();
        scpMetodoPago = new javax.swing.JScrollPane();
        tblMetodoPago = new javax.swing.JTable();
        lblNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        btnSeleccionar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnModificar.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnBorrar.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnBorrar.setText("Borrar");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });

        pnlControl.setBackground(new java.awt.Color(255, 102, 102));
        pnlControl.setOpaque(false);

        btnGuardar.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlControlLayout = new javax.swing.GroupLayout(pnlControl);
        pnlControl.setLayout(pnlControlLayout);
        pnlControlLayout.setHorizontalGroup(
            pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlControlLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGuardar)
                .addContainerGap())
        );
        pnlControlLayout.setVerticalGroup(
            pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlControlLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addContainerGap())
        );

        chkCuotas.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        chkCuotas.setText("Cuotas");

        tblMetodoPago = new javax.swing.JTable(){
            public boolean isCellEditable(int r, int c){
                return false;
            }
        };
        tblMetodoPago.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        tblMetodoPago.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre"
            }
        ));
        tblMetodoPago.getTableHeader().setReorderingAllowed(false);
        tblMetodoPago.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
                tblMetodoPagoAncestorMoved(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        tblMetodoPago.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMetodoPagoMouseClicked(evt);
            }
        });
        scpMetodoPago.setViewportView(tblMetodoPago);

        lblNombre.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lblNombre.setText("Nombre");

        txtNombre.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        btnSeleccionar.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnSeleccionar.setText("Seleccionar");
        btnSeleccionar.setEnabled(false);
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });

        btnNuevo.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlControl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(scpMetodoPago, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNombre)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chkCuotas))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSeleccionar))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnNuevo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnModificar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBorrar)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(scpMetodoPago, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblNombre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSeleccionar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(chkCuotas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        opcion = "nuevo";
        txtNombre.setText("");
        txtNombre.setEnabled(true);
        chkCuotas.setEnabled(true);

        btnGuardar.setEnabled(true);

        chkCuotas.setSelected(false);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        if (tblMetodoPago.getSelectedRow() > -1 && tblMetodoPago.getSelectedRowCount() == 1) {
            int seleccionado = (int) tblMetodoPago.getValueAt(tblMetodoPago.getSelectedRow(), 0);
            /*
            MetodoDePago mp = this.cp.getCpago().traerMetodo(seleccionado);

            if (mp != null) {
                UtilVista.cargarCombo(cp.getCpago().traerMetodos(), vp.cmbMetodoPago);
                vp.cmbMetodoPago.getModel().setSelectedItem(mp);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Error al traer los datos");
            }
            */
        }
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void tblMetodoPagoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMetodoPagoMouseClicked
        if (tblMetodoPago.getSelectedRow() > -1 && tblMetodoPago.getSelectedRowCount() == 1) {
            int seleccionado = (int) tblMetodoPago.getValueAt(tblMetodoPago.getSelectedRow(), 0);
            /*
            //
            MetodoDePago metodoP = this.cp.getCpago().traerMetodo(seleccionado);

            if (metodoP != null) {

                txtNombre.setText(metodoP.getNombre());
                btnModificar.setEnabled(true);
                btnBorrar.setEnabled(true);
                btnSeleccionar.setEnabled(true);

                if (metodoP.getCuotas() == true) {
                    chkCuotas.setSelected(true);
                } else {
                    chkCuotas.setSelected(false);
                }

                txtNombre.setEnabled(false);
                chkCuotas.setEnabled(false);
                btnGuardar.setEnabled(false);

            } else {
                JOptionPane.showMessageDialog(null, "Error al traer los datos");
            }
            */        }
    }//GEN-LAST:event_tblMetodoPagoMouseClicked

    private void tblMetodoPagoAncestorMoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tblMetodoPagoAncestorMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_tblMetodoPagoAncestorMoved

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (opcion.equals("nuevo")) {
            if (txtNombre.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "No puede haber campos vacíos");
                txtNombre.requestFocus();
            } else {
                /*
                try {
                    MetodoDePago metodoP = new MetodoDePago();
                    if (chkCuotas.isSelected()) {
                        metodoP = cp.getCpago().nuevoMetodoP(txtNombre.getText(), true);
                    } else {
                        metodoP = cp.getCpago().nuevoMetodoP(txtNombre.getText(), false);
                    }
                    JOptionPane.showMessageDialog(null, "El Método de Pago se registró con éxito ");
                    UtilVista.cargarCombo(cp.getCpago().traerMetodos(), vp.cmbMetodoPago);
                    vp.cmbMetodoPago.setSelectedItem(metodoP);

                    estadoInicial();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
                */
            }
        } else if (opcion.equals("modificar")) {
            if (tblMetodoPago.getSelectedRow() > -1 && tblMetodoPago.getSelectedRowCount() == 1) {
                int seleccionado = (int) tblMetodoPago.getValueAt(tblMetodoPago.getSelectedRow(), 0);
                if (txtNombre.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No puede haber campos vacios");
                    txtNombre.requestFocus();
                } else {
                    /*
                    try {
                        if (chkCuotas.isSelected()) {
                            cp.getCpago().editarMetodo(seleccionado, txtNombre.getText(), true);
                        } else {
                            cp.getCpago().editarMetodo(seleccionado, txtNombre.getText(), false);
                            System.out.println("FAlso");
                        }
                        JOptionPane.showMessageDialog(null, "El Método de Pago se modificó con éxito ");
                        UtilVista.cargarCombo(cp.getCpago().traerMetodos(), vp.cmbMetodoPago);
                        estadoInicial();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                    */
                }
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed

        try {
            if (tblMetodoPago.getSelectedRow() > -1 && tblMetodoPago.getSelectedRowCount() == 1) {
                int seleccionado = (int) tblMetodoPago.getValueAt(tblMetodoPago.getSelectedRow(), 0);

                int seleccion = JOptionPane.showConfirmDialog(rootPane, "¿Esta seguro que desea eliminar este Método de Pago?", "input", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (seleccion == JOptionPane.YES_OPTION) {
                    /*
                    cp.getCpago().borrarMetodo(seleccionado);
                    JOptionPane.showMessageDialog(rootPane, "El Método de Pago se eliminó exitosamente");
                    UtilVista.cargarCombo(cp.getCpago().traerMetodos(), vp.cmbMetodoPago);
                    estadoInicial();
                    */                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Seleccione un elemento de la Tabla");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        opcion = "modificar";
        txtNombre.setEnabled(true);
        txtNombre.requestFocus();
        chkCuotas.setEnabled(true);

        btnGuardar.setEnabled(true);

        chkCuotas.setSelected(false);
    }//GEN-LAST:event_btnModificarActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JCheckBox chkCuotas;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JPanel pnlControl;
    private javax.swing.JScrollPane scpMetodoPago;
    private javax.swing.JTable tblMetodoPago;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
