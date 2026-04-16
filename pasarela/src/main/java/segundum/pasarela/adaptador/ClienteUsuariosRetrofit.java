package segundum.pasarela.adaptador;

import org.springframework.stereotype.Component;

import retrofit2.Response;
import segundum.pasarela.puerto.ClienteUsuarios;
import segundum.pasarela.puerto.UsuariosRetrofit;
import segundum.pasarela.puerto.dto.UsuarioGithubInputDTO;
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
	public UsuarioLoginResponseDTO buscarPorGithubId(String githubId) throws Exception {
		if (githubId == null || githubId.isEmpty()) {
			return null;
		}
		Response<UsuarioLoginResponseDTO> response = api.buscarPorGithubId(githubId).execute();

		if (response.code() == 404) {
			return null;
		}

		if (!response.isSuccessful() || response.body() == null) {
			throw new Exception("Error buscando usuario por githubId: " + githubId);
		}

		return response.body();
	}

	@Override
	public UsuarioLoginResponseDTO crearUsuarioGithub(UsuarioGithubInputDTO dto) throws Exception {
		// Crear el usuario en el microservicio
		Response<Void> createResponse = api.crearUsuarioGithub(dto).execute();
		if (!createResponse.isSuccessful()) {
			throw new Exception("No se pudo crear el usuario de GitHub con id: " + dto.getGithubId());
		}

		// Recuperar los datos del usuario recién creado para generar el JWT
		UsuarioLoginResponseDTO usuario = buscarPorGithubId(dto.getGithubId());
		if (usuario == null) {
			throw new Exception("Usuario creado pero no encontrado al recuperarlo por githubId");
		}
		return usuario;
	}
}