package modelo;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.CajaChica;
import modelo.Cliente;
import modelo.Compra;
import modelo.Maquinas;
import modelo.Material;
import modelo.PagoDeServicio;
import modelo.Pedidos;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-11-26T17:15:41", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Estructurales.class)
public class Estructurales_ { 

    public static volatile SingularAttribute<Estructurales, String> direccion;
    public static volatile SingularAttribute<Estructurales, CajaChica> cajaChica;
    public static volatile SingularAttribute<Estructurales, String> cel;
    public static volatile ListAttribute<Estructurales, Pedidos> pedidos;
    public static volatile SingularAttribute<Estructurales, String> provincia;
    public static volatile SingularAttribute<Estructurales, String> nombre;
    public static volatile SingularAttribute<Estructurales, String> claveSeguridad;
    public static volatile ListAttribute<Estructurales, Cliente> cliente;
    public static volatile ListAttribute<Estructurales, Compra> compras;
    public static volatile ListAttribute<Estructurales, PagoDeServicio> pagoDeServicio;
    public static volatile SingularAttribute<Estructurales, String> cuit;
    public static volatile SingularAttribute<Estructurales, String> ciudad;
    public static volatile ListAttribute<Estructurales, Maquinas> maquina;
    public static volatile SingularAttribute<Estructurales, String> tel;
    public static volatile SingularAttribute<Estructurales, Integer> id;
    public static volatile ListAttribute<Estructurales, Material> materiales;
    public static volatile SingularAttribute<Estructurales, String> codPostal;

}