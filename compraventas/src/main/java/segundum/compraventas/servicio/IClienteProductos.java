package segundum.compraventas.servicio;

import segundum.compraventas.rest.dto.ProductoDTO;

public interface IClienteProductos {
	ProductoDTO getProducto(String idProducto) throws Exception;
}
