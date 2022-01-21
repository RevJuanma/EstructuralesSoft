package modelo;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Estructurales;
import modelo.MarcaMaterial;
import modelo.Stock;
import modelo.UnidadDeMedida;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-11-26T17:15:41", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Material.class)
public class Material_ { 

    public static volatile SingularAttribute<Material, String> descripcion;
    public static volatile SingularAttribute<Material, MarcaMaterial> marcaMaterial;
    public static volatile SingularAttribute<Material, String> tipo;
    public static volatile SingularAttribute<Material, String> contenido;
    public static volatile SingularAttribute<Material, String> color;
    public static volatile SingularAttribute<Material, UnidadDeMedida> unidadDeMedida;
    public static volatile SingularAttribute<Material, Integer> id;
    public static volatile SingularAttribute<Material, Stock> stock;
    public static volatile SingularAttribute<Material, String> nombre;
    public static volatile SingularAttribute<Material, Estructurales> estructurales;

}