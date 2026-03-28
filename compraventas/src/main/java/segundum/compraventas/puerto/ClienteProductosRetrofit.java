package segundum.compraventas.puerto;

import segundum.compraventas.rest.dto.ProductoDTO;

public interface ClienteProductosRetrofit {
	ProductoDTO getProducto(String idProducto) throws Exception;
}
