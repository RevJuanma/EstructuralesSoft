package modelo;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Contacto;
import modelo.Estructurales;
import modelo.Pago;
import modelo.Pedidos;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-11-26T17:15:41", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Cliente.class)
public class Cliente_ { 

    public static volatile SingularAttribute<Cliente, Contacto> contacto;
    public static volatile SingularAttribute<Cliente, String> apellido;
    public static volatile SingularAttribute<Cliente, Boolean> baja;
    public static volatile SingularAttribute<Cliente, Integer> id;
    public static volatile ListAttribute<Cliente, Pedidos> pedidos;
    public static volatile SingularAttribute<Cliente, String> nombre;
    public static volatile SingularAttribute<Cliente, Estructurales> estructurales;
    public static volatile ListAttribute<Cliente, Pago> pagos;
    public static volatile SingularAttribute<Cliente, String> dni;

}