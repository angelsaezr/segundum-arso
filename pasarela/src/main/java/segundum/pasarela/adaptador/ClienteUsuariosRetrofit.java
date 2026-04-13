package segundum.pasarela.adaptador;

import org.springframework.stereotype.Component;

import retrofit2.Response;
import segundum.pasarela.puerto.ClienteUsuarios;
import segundum.pasarela.puerto.UsuariosRetrofit;
import segundum.pasarela.puerto.dto.UsuarioLoginInputDTO;
import segundum.pasarela.puerto.dto.UsuarioLoginResponseDTO;

@Component
public class ClienteUsuariosRetrofit implements ClienteUsuarios {

	private final UsuariosRetrofit api;

	public ClienteUsuariosRetrofit(UsuariosRetrofit api) {
		this.api = api;
	}

	@Override
	public UsuarioLoginResponseDTO login(UsuarioLoginInputDTO dto) throws Exception {
		Response<UsuarioLoginResponseDTO> response = api.login(dto).execute();

		if (!response.isSuccessful() || response.body() == null) {
			throw new Exception("Usuario no encontrado: " + dto.getEmail());
		}

		return response.body();
	}

	@Override
	public UsuarioLoginResponseDTO buscarPorEmail(String email) throws Exception {
		if (email == null || email.isEmpty()) {
			throw new Exception("El usuario de GitHub no tiene email público");
		}
		Response<UsuarioLoginResponseDTO> response = api.buscarPorEmail(email).execute();
		if (!response.isSuccessful() || response.body() == null) {
			throw new Exception("No existe usuario con email: " + email);
		}
		return response.body();
	}

}