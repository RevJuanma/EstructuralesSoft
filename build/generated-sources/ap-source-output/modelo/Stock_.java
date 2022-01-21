package modelo;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-11-26T17:15:41", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Stock.class)
public class Stock_ { 

    public static volatile SingularAttribute<Stock, String> ubicacion;
    public static volatile SingularAttribute<Stock, Integer> id;
    public static volatile SingularAttribute<Stock, Integer> cantidad;
    public static volatile SingularAttribute<Stock, Integer> cantidadMin;

}