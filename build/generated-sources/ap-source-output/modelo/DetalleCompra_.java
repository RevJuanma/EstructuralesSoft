package modelo;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Compra;
import modelo.Material;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-11-26T17:15:41", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(DetalleCompra.class)
public class DetalleCompra_ { 

    public static volatile SingularAttribute<DetalleCompra, Compra> compra;
    public static volatile SingularAttribute<DetalleCompra, Double> costo;
    public static volatile SingularAttribute<DetalleCompra, Integer> id;
    public static volatile SingularAttribute<DetalleCompra, Integer> cantidad;
    public static volatile SingularAttribute<DetalleCompra, Material> materiales;

}