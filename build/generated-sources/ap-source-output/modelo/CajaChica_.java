package modelo;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Movimiento;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-11-26T17:15:41", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(CajaChica.class)
public class CajaChica_ { 

    public static volatile SingularAttribute<CajaChica, Integer> id;
    public static volatile SingularAttribute<CajaChica, Double> saldoActual;
    public static volatile ListAttribute<CajaChica, Movimiento> movimientos;

}