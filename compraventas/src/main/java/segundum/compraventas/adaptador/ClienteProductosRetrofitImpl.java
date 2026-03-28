package segundum.compraventas.adaptador;

import org.springframework.stereotype.Component;

import segundum.compraventas.puerto.ClienteProductosRetrofit;
import segundum.compraventas.puerto.ProductosRetrofit;
import segundum.compraventas.rest.dto.ProductoDTO;
import segundum.compraventas.rest.dto.ProductoRespuestaDTO;

@Component
public class ClienteProductosRetrofitImpl implements ClienteProductosRetrofit {

	private final ProductosRetrofit api;

	public ClienteProductosRetrofitImpl(ProductosRetrofit api) {
		this.api = api;
	}

	@Override
	public ProductoDTO getProducto(String idProducto) throws Exception {
		retrofit2.Response<ProductoRespuestaDTO> response = api.getProducto(idProducto).execute();

		if (!response.isSuccessful() || response.body() == null) {
			throw new Exception("Producto no encontrado: " + idProducto);
		}

		ProductoRespuestaDTO dto = response.body();

		String lugarRecogida = dto.getRecogida();

		return new ProductoDTO(dto.getIdVendedor(), dto.getTitulo(), dto.getPrecio(), lugarRecogida);
	}
}
