package modelo;

import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Pago;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-11-26T17:15:41", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Cuota.class)
public class Cuota_ { 

    public static volatile SingularAttribute<Cuota, Date> fecha;
    public static volatile SingularAttribute<Cuota, String> estado;
    public static volatile SingularAttribute<Cuota, Integer> id;
    public static volatile SingularAttribute<Cuota, Double> importe;
    public static volatile SingularAttribute<Cuota, Pago> pago;

}