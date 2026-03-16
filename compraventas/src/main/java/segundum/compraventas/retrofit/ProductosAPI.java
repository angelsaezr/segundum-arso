package segundum.compraventas.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import segundum.compraventas.rest.dto.ProductoRespuestaDTO;

public interface ProductosAPI {

	@GET("productos/{id}")
	Call<ProductoRespuestaDTO> getProducto(@Path("id") String id);
}
