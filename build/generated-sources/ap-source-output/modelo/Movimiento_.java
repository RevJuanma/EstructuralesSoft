package modelo;

import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.CajaChica;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-11-26T17:15:41", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Movimiento.class)
public class Movimiento_ { 

    public static volatile SingularAttribute<Movimiento, Date> fecha;
    public static volatile SingularAttribute<Movimiento, String> tipo;
    public static volatile SingularAttribute<Movimiento, CajaChica> cajaChica;
    public static volatile SingularAttribute<Movimiento, Integer> id;
    public static volatile SingularAttribute<Movimiento, Double> importe;

}