package segundum.compraventas.puerto;

import segundum.compraventas.rest.dto.ProductoDTO;

public interface ClienteProductos {
	ProductoDTO getProducto(String idProducto) throws Exception;
}
