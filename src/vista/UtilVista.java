/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Cliente;
import modelo.Compra;
import modelo.Cuota;
import modelo.DetalleCompra;
import modelo.Maquinas;
import modelo.MarcaMaterial;
import modelo.MetodoDePago;
import modelo.Movimiento;
import modelo.Pago;
import modelo.PagoDeServicio;
import modelo.Material;
import modelo.Medidas;
import modelo.Pedidos;
import modelo.Servicio;
import modelo.UnidadDeMedida;

/**
 *
 * @author juanm
 */
public abstract class UtilVista {

    public static String fecha(Date fecha) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fechaD = formato.format(fecha);

        return fechaD;
    }

    public static String decimalFormat(double costo) {
        DecimalFormat df = new DecimalFormat(".00");
        String c = df.format(costo);

        return c;
    }

    public static void cargarCombo(List<?> miLista, JComboBox cmbCombo) {
        DefaultComboBoxModel miCombo = new DefaultComboBoxModel();

        if (miLista != null) {
            miCombo.addElement("[SELECCIONAR]");
            for (Object object : miLista) {
                miCombo.addElement(object);
            }
            cmbCombo.setModel(miCombo);
        } else {
            cmbCombo.removeAllItems();
            miCombo.addElement("[SELECCIONAR]");
            cmbCombo.setModel(miCombo);
        }
    }

    public static void cargarTablaCliente(List<Cliente> clientes, JTable tabla) {
        DefaultTableModel miTabla = new DefaultTableModel();
        String cabecera[] = {"Nombre", "Apellido", "DNI", "Celular"};
        miTabla.setColumnIdentifiers(cabecera);
        Object fila[] = new Object[miTabla.getColumnCount()];
        if (clientes != null) {
            for (Cliente c : clientes) {
                fila[0] = c.getNombre();
                fila[1] = c.getApellido();
                fila[2] = c.getDni();
                fila[3] = c.getContacto().getCel();

                miTabla.addRow(fila);
            }
            tabla.setModel(miTabla);
        }
    }

    public static void cargarTablaMaquinas(List<Maquinas> maquinas, JTable tabla) {
        DefaultTableModel miTabla = new DefaultTableModel();
        String cabecera[] = {"Código", "Nombre"};
        miTabla.setColumnIdentifiers(cabecera);
        Object fila[] = new Object[miTabla.getColumnCount()];
        if (maquinas != null) {
            for (Maquinas m : maquinas) {
                fila[0] = m.getCod();
                fila[1] = m.getNombre();
                miTabla.addRow(fila);
            }
            tabla.setModel(miTabla);
        }
    }

    public static void cargarTablaMedidas(List<Medidas> medida, JTable tabla) {
        DefaultTableModel miTabla = new DefaultTableModel();
        String cabecera[] = {"ID", "Nombre", "Cantidad", "U. Medida"};
        miTabla.setColumnIdentifiers(cabecera);
        Object fila[] = new Object[miTabla.getColumnCount()];
        if (medida != null) {
            for (Medidas m : medida) {
                fila[0] = m.getId();
                fila[1] = m.getNombre();
                fila[2] = m.getCantidad();
                fila[3] = m.getUnidadDeMedida().getNombre();
                miTabla.addRow(fila);
            }
            tabla.setModel(miTabla);
        }
    }

    public static void cargarTablaCuotas(List<Cuota> cuotas, JTable tabla) {
        DefaultTableModel miTabla = new DefaultTableModel();
        String cabecera[] = {"ID", "Monto", "Fecha", "Estado"};
        miTabla.setColumnIdentifiers(cabecera);
        Object fila[] = new Object[miTabla.getColumnCount()];

        //int cont = 0;
        if (cuotas != null) {
            for (Cuota c : cuotas) {
                fila[0] = c.getId();

                fila[1] = decimalFormat(c.getImporte());
                if (c.getFecha() == null) {
                    fila[2] = "";
                } else {
                    fila[2] = fecha(c.getFecha());
                }
                fila[3] = c.getEstado();

                //cont += 1;
                miTabla.addRow(fila);
            }
            tabla.setModel(miTabla);
        }
        //return cont;
    }

    public static void cargarTablaMetodoDePago(List<MetodoDePago> metodos, JTable tabla) {
        DefaultTableModel miTabla = new DefaultTableModel();
        String cabecera[] = {"ID", "Nombre"};
        miTabla.setColumnIdentifiers(cabecera);
        Object fila[] = new Object[miTabla.getColumnCount()];

        if (metodos != null) {
            for (MetodoDePago m : metodos) {
                fila[0] = m.getId();
                fila[1] = m.getNombre();

                miTabla.addRow(fila);
            }
            tabla.setModel(miTabla);
        }
    }

    public static void cargarTablaMarcaMaterial(List<MarcaMaterial> marcas, JTable tabla) {
        DefaultTableModel miTabla = new DefaultTableModel();
        String cabecera[] = {"ID", "Nombre"};
        miTabla.setColumnIdentifiers(cabecera);
        Object fila[] = new Object[miTabla.getColumnCount()];

        if (marcas != null) {
            for (MarcaMaterial m : marcas) {
                fila[0] = m.getId();
                fila[1] = m.getNombre();

                miTabla.addRow(fila);
            }
            tabla.setModel(miTabla);
        }
    }

    public static void cargarTablaUnidadDeMedida(List<UnidadDeMedida> um, JTable tabla) {
        DefaultTableModel miTabla = new DefaultTableModel();
        String cabecera[] = {"ID", "Nombre"};
        miTabla.setColumnIdentifiers(cabecera);
        Object fila[] = new Object[miTabla.getColumnCount()];

        if (um != null) {
            for (UnidadDeMedida u : um) {
                fila[0] = u.getId();
                fila[1] = u.getNombre();

                miTabla.addRow(fila);
            }
            tabla.setModel(miTabla);
        }
    }

    public static void cargarTablaServicios(List<Servicio> servicios, JTable tabla) {
        DefaultTableModel miTabla = new DefaultTableModel();
        String cabecera[] = {"ID", "Razón Social"};
        miTabla.setColumnIdentifiers(cabecera);
        Object fila[] = new Object[miTabla.getColumnCount()];

        if (servicios != null) {
            for (Servicio s : servicios) {
                fila[0] = s.getId();
                fila[1] = s.getNombre();

                miTabla.addRow(fila);
            }
            tabla.setModel(miTabla);
        }
    }

    public static void cargarTablaPagoServicio(List<PagoDeServicio> pagop, JTable tabla) {
        DefaultTableModel miTabla = new DefaultTableModel();
        String cabecera[] = {"ID", "Servicio", "Fecha", "Importe"};
        miTabla.setColumnIdentifiers(cabecera);
        Object fila[] = new Object[miTabla.getColumnCount()];

        if (pagop != null) {
            for (PagoDeServicio p : pagop) {
                fila[0] = p.getId();
                fila[1] = p.getServicio();
                fila[2] = fecha(p.getFecha());
                fila[3] = p.getImporte();

                miTabla.addRow(fila);
            }
            tabla.setModel(miTabla);
        }
    }

    public static void cargarNotificaciones(List<Material> productos, JList lista) {
        DefaultListModel ListaNotificaciones = new DefaultListModel();

        if (productos != null) {
            for (Material p : productos) {
                if (p.getStock().getCantidad() <= p.getStock().getCantidadMin()) {
                    ListaNotificaciones.addElement("Quedan " + p.getStock().getCantidad() + " existencias de " + p.getNombre());
                }
            }
            lista.setModel(ListaNotificaciones);
        }
    }

    public static void cargarTablaStock(List<Material> productos, JTable tabla) {
        DefaultTableModel miTabla = new DefaultTableModel();
        String cabecera[] = {"ID", "Nombre", "Tipo", "Cantidad", "Cantidad Minima", "Ubicación"};
        miTabla.setColumnIdentifiers(cabecera);
        Object fila[] = new Object[miTabla.getColumnCount()];

        if (productos != null) {
            for (Material p : productos) {
                fila[0] = p.getId();
                fila[1] = p.getNombre();
                fila[2] = p.getTipo();
                fila[3] = p.getStock().getCantidad();
                fila[4] = p.getStock().getCantidadMin();
                fila[5] = p.getStock().getUbicacion();

                miTabla.addRow(fila);
            }
            tabla.setModel(miTabla);
        }
    }

    public static void cargarTablaCompras(List<Compra> compras, JTable tabla) {
        DefaultTableModel miTabla = new DefaultTableModel();
        String cabecera[] = {"ID", "Fecha", "Total"};
        miTabla.setColumnIdentifiers(cabecera);
        Object fila[] = new Object[miTabla.getColumnCount()];

        if (compras != null) {
            for (Compra c : compras) {
                fila[0] = c.getId();
                fila[1] = fecha(c.getFecha());
                fila[2] = c.getTotal();

                miTabla.addRow(fila);
            }
            tabla.setModel(miTabla);
        }
    }

    public static void cargarTablaDetalles(List<DetalleCompra> detalles, JTable tabla) {
        DefaultTableModel miTabla = new DefaultTableModel();
        String cabecera[] = {"ID", "Producto", "Precio Unitario", "Cantidad"};
        miTabla.setColumnIdentifiers(cabecera);
        Object fila[] = new Object[miTabla.getColumnCount()];

        if (detalles != null) {
            for (DetalleCompra d : detalles) {
                fila[0] = d.getId();
                fila[1] = d.getMateriales().getNombre();
                fila[2] = d.getCosto();
                fila[3] = d.getCantidad();

                miTabla.addRow(fila);
            }
            tabla.setModel(miTabla);
        }
    }

    public static double cargarGastosMov(List<DetalleCompra> compras,
            List<PagoDeServicio> pagoServicios,
            JTable tabla) {

        double gastos = 0;
        DefaultTableModel miTabla = new DefaultTableModel();
        String cabecera[] = {"Fecha", "Tipo", "Nombre", "Importe"};
        miTabla.setColumnIdentifiers(cabecera);
        Object fila[] = new Object[miTabla.getColumnCount()];

        if (compras != null) {
            for (DetalleCompra c : compras) {
                fila[0] = fecha(c.getCompra().getFecha());
                fila[1] = "Compra";
                fila[2] = c.getMateriales().getNombre();
                fila[3] = c.getCosto();

                gastos += c.getCosto();
                miTabla.addRow(fila);
            }
        }

        if (pagoServicios != null) {
            for (PagoDeServicio p : pagoServicios) {
                fila[0] = fecha(p.getFecha());
                fila[1] = "Pago de Servicio";
                fila[2] = p.getServicio().getNombre();
                fila[3] = p.getImporte();

                gastos += p.getImporte();
                miTabla.addRow(fila);
            }
        }

        tabla.setModel(miTabla);
        return gastos;

    }

    public static double cargarIngresosMov(List<Pago> pagos, JTable tabla) {
        DefaultTableModel miTabla = new DefaultTableModel();
        String cabecera[] = {"Fecha", "Tipo", "Nombre", "Importe"};
        miTabla.setColumnIdentifiers(cabecera);
        Object fila[] = new Object[miTabla.getColumnCount()];

        double ingresos = 0;
        if (pagos != null) {
            for (Pago p : pagos) {
                if (p.getMetodoDePago().getCuotas() == false && p.getEstado().equals("Pagado")) {
                    fila[0] = fecha(p.getFecha());
                    fila[1] = p.getMetodoDePago().getNombre();
                    fila[2] = p.getCliente().getApellido() + " " + p.getCliente().getNombre();
                    fila[3] = p.getMontoTotal();

                    ingresos += p.getMontoTotal();
                    miTabla.addRow(fila);
                    /*
                } else if (p.getMetodoDePago().getCuotas() == true && p.getEstado().equals("Pagado")) {
                    fila[0] = fecha(p.getFecha());
                    fila[1] = p.getMetodoDePago().getNombre();
                    fila[2] = p.getCliente().getApellido() + " " + p.getCliente().getNombre();
                    fila[3] = p.getMontoTotal();

                    ingresos += p.getMontoTotal();
                    miTabla.addRow(fila);
                     */
                } else {//if (p.getMetodoDePago().getCuotas() == true && p.getEstado().equals("Pendiente")) {
                    for (Cuota c : p.getCuotas()) {
                        if (c.getEstado().equals("Pagado")) {

                            fila[0] = fecha(c.getFecha());
                            fila[1] = "Cuota / " + p.getMetodoDePago().getNombre();
                            fila[2] = p.getCliente().getApellido() + " " + p.getCliente().getNombre();
                            fila[3] = c.getImporte();

                            ingresos += c.getImporte();
                            miTabla.addRow(fila);
                        }
                    }
                }
            }
        }

        tabla.setModel(miTabla);
        return ingresos;
    }

    public static double cargarComprasMov(List<DetalleCompra> compras, JTable tabla) {
        DefaultTableModel miTabla = new DefaultTableModel();
        String cabecera[] = {"Fecha", "Tipo", "Nombre", "Importe"};
        miTabla.setColumnIdentifiers(cabecera);
        Object fila[] = new Object[miTabla.getColumnCount()];

        double comp = 0;
        if (compras != null) {
            for (DetalleCompra c : compras) {
                fila[0] = fecha(c.getCompra().getFecha());
                fila[1] = "Compra";
                fila[2] = c.getMateriales().getNombre();
                fila[3] = c.getCosto();

                comp += c.getCosto();
                miTabla.addRow(fila);
            }
        }
        tabla.setModel(miTabla);
        return comp;
    }

    public static double cargarCajaChicaMov(List<Movimiento> movimientos, JTable tabla) {
        DefaultTableModel miTabla = new DefaultTableModel();
        String cabecera[] = {"Fecha", "Tipo", "Nombre", "Importe"};
        miTabla.setColumnIdentifiers(cabecera);
        Object fila[] = new Object[miTabla.getColumnCount()];

        double dep = 0, ex = 0;
        if (movimientos != null) {
            for (Movimiento m : movimientos) {
                fila[0] = fecha(m.getFecha());
                fila[1] = "Caja Chica";
                fila[2] = m.getTipo();
                fila[3] = m.getImporte();

                if ("Extracción".equals(m.getTipo())) {
                    ex = ex + m.getImporte();
                } else {
                    dep = dep + m.getImporte();
                }
                miTabla.addRow(fila);

            }
        }
        double cajaC = dep - ex;
        tabla.setModel(miTabla);
        return cajaC;
    }

    public static double cargarServiciosMov(List<PagoDeServicio> pagoServicios, JTable tabla) {

        DefaultTableModel miTabla = new DefaultTableModel();
        String cabecera[] = {"Fecha", "Tipo", "Nombre", "Importe"};
        miTabla.setColumnIdentifiers(cabecera);
        Object fila[] = new Object[miTabla.getColumnCount()];

        double serv = 0;
        if (pagoServicios != null) {
            for (PagoDeServicio p : pagoServicios) {
                fila[0] = fecha(p.getFecha());
                fila[1] = "Pago de Servicio";
                fila[2] = p.getServicio().getNombre();
                fila[3] = p.getImporte();

                serv += p.getImporte();
                miTabla.addRow(fila);
            }
        }
        tabla.setModel(miTabla);
        return serv;
    }

    public static double[] cargarTablaMovimientos(List<DetalleCompra> compras,
            List<Pago> pagos,
            List<Movimiento> movimientos,
            List<PagoDeServicio> pagoServicios,
            JTable tabla) {

        double cierre = 0;
        double gastos = 0;
        double ingresos = 0;

        DefaultTableModel miTabla = new DefaultTableModel();
        String cabecera[] = {"Fecha", "Tipo", "Nombre", "Importe"};
        miTabla.setColumnIdentifiers(cabecera);
        Object fila[] = new Object[miTabla.getColumnCount()];

        if (compras != null) {
            for (DetalleCompra c : compras) {
                fila[0] = fecha(c.getCompra().getFecha());
                fila[1] = "Compra";
                fila[2] = c.getMateriales().getNombre();
                double costo = c.getCosto() * c.getCantidad();
                fila[3] = costo;

                gastos += costo;
                miTabla.addRow(fila);
            }
        }

        if (pagos != null) {
            for (Pago p : pagos) {
                if (p.getMetodoDePago().getCuotas() == false && p.getEstado().equals("Pagado")) {
                    fila[0] = fecha(p.getFecha());
                    fila[1] = p.getMetodoDePago().getNombre();
                    fila[2] = p.getCliente().getApellido() + " " + p.getCliente().getNombre();
                    fila[3] = p.getMontoTotal();

                    ingresos += p.getMontoTotal();
                    miTabla.addRow(fila);
                    /*
                } else if (p.getMetodoDePago().getCuotas() == true && p.getEstado().equals("Pagado")) {
                    fila[0] = fecha(p.getFecha());
                    fila[1] = p.getMetodoDePago().getNombre();
                    fila[2] = p.getCliente().getApellido() + " " + p.getCliente().getNombre();
                    fila[3] = p.getMontoTotal();

                    ingresos += p.getMontoTotal();
                    miTabla.addRow(fila);
                     */
                } else {//if (p.getMetodoDePago().getCuotas() == true && p.getEstado().equals("Pendiente")) {

                    for (Cuota c : p.getCuotas()) {
                        if (c.getEstado().equals("Pagado")) {

                            fila[0] = fecha(c.getFecha());
                            fila[1] = "Cuota / " + p.getMetodoDePago().getNombre();
                            fila[2] = p.getCliente().getApellido() + " " + p.getCliente().getNombre();
                            fila[3] = c.getImporte();

                            ingresos += c.getImporte();
                            miTabla.addRow(fila);
                        }
                    }
                }
            }
        }

        if (movimientos != null) {

            for (Movimiento m : movimientos) {
                fila[0] = fecha(m.getFecha());
                fila[1] = "Caja Chica";
                fila[2] = m.getTipo();
                fila[3] = m.getImporte();

                miTabla.addRow(fila);

            }
        }

        if (pagoServicios != null) {
            for (PagoDeServicio p : pagoServicios) {
                fila[0] = fecha(p.getFecha());
                fila[1] = "Pago de Servicio";
                fila[2] = p.getServicio().getNombre();
                fila[3] = p.getImporte();

                gastos += p.getImporte();
                miTabla.addRow(fila);
            }
        }

        tabla.setModel(miTabla);

        cierre = ingresos - gastos;

        double[] cgi = {cierre, gastos, ingresos};
        return cgi;
    }

}
