package modelo;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Maquinas;
import modelo.UnidadDeMedida;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-11-30T21:02:16", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Medidas.class)
public class Medidas_ { 

    public static volatile SingularAttribute<Medidas, UnidadDeMedida> unidadDeMedida;
    public static volatile SingularAttribute<Medidas, Integer> id;
    public static volatile SingularAttribute<Medidas, String> cantidad;
    public static volatile SingularAttribute<Medidas, String> nombre;
    public static volatile SingularAttribute<Medidas, Maquinas> maquinas;

}