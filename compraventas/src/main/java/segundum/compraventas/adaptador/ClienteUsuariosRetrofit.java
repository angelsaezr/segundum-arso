package segundum.compraventas.adaptador;

import org.springframework.stereotype.Component;

import okhttp3.ResponseBody;
import segundum.compraventas.puerto.ClienteUsuarios;
import segundum.compraventas.puerto.UsuariosRetrofit;

@Component
public class ClienteUsuariosRetrofit implements ClienteUsuarios {

	private final UsuariosRetrofit api;

	public ClienteUsuariosRetrofit(UsuariosRetrofit api) {
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