package modelo;

import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Cliente;
import modelo.Estructurales;
import modelo.Maquinas;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-11-30T12:58:09", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Pedidos.class)
public class Pedidos_ { 

    public static volatile SingularAttribute<Pedidos, Double> senia;
    public static volatile SingularAttribute<Pedidos, Date> fecha;
    public static volatile SingularAttribute<Pedidos, Cliente> cliente;
    public static volatile SingularAttribute<Pedidos, String> estado;
    public static volatile SingularAttribute<Pedidos, String> tienda;
    public static volatile SingularAttribute<Pedidos, String> color;
    public static volatile SingularAttribute<Pedidos, Double> costo;
    public static volatile SingularAttribute<Pedidos, Date> fechaEntrega;
    public static volatile SingularAttribute<Pedidos, Integer> id;
    public static volatile SingularAttribute<Pedidos, Estructurales> estructurales;
    public static volatile SingularAttribute<Pedidos, Maquinas> maquinas;
    public static volatile SingularAttribute<Pedidos, String> detalle;

}