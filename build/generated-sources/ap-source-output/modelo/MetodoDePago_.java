package modelo;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Pago;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-11-26T17:15:41", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(MetodoDePago.class)
public class MetodoDePago_ { 

    public static volatile SingularAttribute<MetodoDePago, Integer> id;
    public static volatile SingularAttribute<MetodoDePago, String> nombre;
    public static volatile SingularAttribute<MetodoDePago, Boolean> cuotas;
    public static volatile ListAttribute<MetodoDePago, Pago> pagos;

}