package segundum.compraventas.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UsuariosAPI {

	@GET("usuarios/{id}/nombre")
	Call<String> getNombre(@Path("id") String id);
}