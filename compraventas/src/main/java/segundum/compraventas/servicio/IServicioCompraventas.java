package segundum.compraventas.servicio;

import java.util.List;

import segundum.compraventas.modelo.Compraventa;

public interface IServicioCompraventas {
	Compraventa compraventa(String idProducto, String idComprador);

	List<Compraventa> recuperarComprasUsuario(String idComprador);

	List<Compraventa> recuperarVentasUsuario(String idVendedor);

	List<Compraventa> recuperarCompraventasEntreUsuarios(String idComprador, String idVendedor);
}