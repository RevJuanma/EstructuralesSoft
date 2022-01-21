package modelo;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Material;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-11-26T17:15:41", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(MarcaMaterial.class)
public class MarcaMaterial_ { 

    public static volatile SingularAttribute<MarcaMaterial, Integer> id;
    public static volatile ListAttribute<MarcaMaterial, Material> materiales;
    public static volatile SingularAttribute<MarcaMaterial, String> nombre;

}