package modelo;

import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Estructurales;
import modelo.Servicio;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-11-26T17:15:41", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(PagoDeServicio.class)
public class PagoDeServicio_ { 

    public static volatile SingularAttribute<PagoDeServicio, Date> fecha;
    public static volatile SingularAttribute<PagoDeServicio, Servicio> servicio;
    public static volatile SingularAttribute<PagoDeServicio, Integer> id;
    public static volatile SingularAttribute<PagoDeServicio, String> comentario;
    public static volatile SingularAttribute<PagoDeServicio, Double> importe;
    public static volatile SingularAttribute<PagoDeServicio, Estructurales> estructurales;

}