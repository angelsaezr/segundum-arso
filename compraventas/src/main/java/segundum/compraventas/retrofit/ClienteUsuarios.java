package segundum.compraventas.retrofit;

import org.springframework.stereotype.Component;

import segundum.compraventas.servicio.IClienteUsuarios;

@Component
public class ClienteUsuarios implements IClienteUsuarios {

	private final UsuariosAPI api;

	public ClienteUsuarios(UsuariosAPI api) {
		this.api = api;
	}

	@Override
	public String getNombreUsuario(String idUsuario) throws Exception {
		retrofit2.Response<String> response = api.getNombre(idUsuario).execute();

		if (!response.isSuccessful() || response.body() == null) {
			throw new Exception("Usuario no encontrado: " + idUsuario);
		}

		return response.body();
	}
}