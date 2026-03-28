package segundum.compraventas.adaptador;

import org.springframework.stereotype.Component;

import okhttp3.ResponseBody;
import segundum.compraventas.puerto.ClienteUsuariosRetrofit;
import segundum.compraventas.puerto.UsuariosRetrofit;

@Component
public class ClienteUsuariosRetrofitImpl implements ClienteUsuariosRetrofit {

	private final UsuariosRetrofit api;

	public ClienteUsuariosRetrofitImpl(UsuariosRetrofit api) {
		this.api = api;
	}

	@Override
	public String getNombreUsuario(String idUsuario) throws Exception {
		retrofit2.Response<ResponseBody> response = api.getNombre(idUsuario).execute();

		if (!response.isSuccessful() || response.body() == null) {
			throw new Exception("Usuario no encontrado: " + idUsuario);
		}

		return response.body().string();
	}
}