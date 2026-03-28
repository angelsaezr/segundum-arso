package segundum.compraventas.puerto;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UsuariosRetrofit {

	@GET("usuarios/{id}/nombre")
	Call<ResponseBody> getNombre(@Path("id") String id);
}