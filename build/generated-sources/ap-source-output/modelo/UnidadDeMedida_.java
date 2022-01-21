package modelo;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Material;
import modelo.Medidas;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-11-30T13:28:54", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(UnidadDeMedida.class)
public class UnidadDeMedida_ { 

    public static volatile ListAttribute<UnidadDeMedida, Medidas> medidas;
    public static volatile SingularAttribute<UnidadDeMedida, Integer> id;
    public static volatile ListAttribute<UnidadDeMedida, Material> materiales;
    public static volatile SingularAttribute<UnidadDeMedida, String> nombre;

}