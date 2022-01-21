package modelo;

import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Cliente;
import modelo.Cuota;
import modelo.MetodoDePago;
import modelo.Pedidos;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-11-26T17:15:41", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Pago.class)
public class Pago_ { 

    public static volatile SingularAttribute<Pago, Date> fecha;
    public static volatile SingularAttribute<Pago, Cliente> cliente;
    public static volatile SingularAttribute<Pago, MetodoDePago> metodoDePago;
    public static volatile SingularAttribute<Pago, String> estado;
    public static volatile SingularAttribute<Pago, Double> descuento;
    public static volatile SingularAttribute<Pago, Integer> id;
    public static volatile SingularAttribute<Pago, Pedidos> pedidos;
    public static volatile ListAttribute<Pago, Cuota> cuotas;
    public static volatile SingularAttribute<Pago, Double> montoTotal;
    public static volatile SingularAttribute<Pago, Integer> interes;

}