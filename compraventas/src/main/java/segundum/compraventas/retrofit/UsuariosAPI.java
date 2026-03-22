package segundum.compraventas.retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UsuariosAPI {

	@GET("usuarios/{id}/nombre")
	Call<ResponseBody> getNombre(@Path("id") String id);
}