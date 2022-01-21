package modelo;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Estructurales;
import modelo.Medidas;
import modelo.Pedidos;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-11-30T13:55:56", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Maquinas.class)
public class Maquinas_ { 

    public static volatile SingularAttribute<Maquinas, Boolean> baja;
    public static volatile SingularAttribute<Maquinas, String> cod;
    public static volatile ListAttribute<Maquinas, Medidas> medidas;
    public static volatile SingularAttribute<Maquinas, Integer> id;
    public static volatile ListAttribute<Maquinas, Pedidos> pedidos;
    public static volatile SingularAttribute<Maquinas, String> nombre;
    public static volatile SingularAttribute<Maquinas, Estructurales> estructurales;

}