package modelo;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.PagoDeServicio;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-11-26T17:15:41", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Servicio.class)
public class Servicio_ { 

    public static volatile ListAttribute<Servicio, PagoDeServicio> pagoDeServicio;
    public static volatile SingularAttribute<Servicio, Integer> id;
    public static volatile SingularAttribute<Servicio, String> nombre;

}