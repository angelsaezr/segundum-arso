package segundum.pasarela.puerto;

import segundum.pasarela.puerto.dto.UsuarioGithubInputDTO;
import segundum.pasarela.puerto.dto.UsuarioLoginInputDTO;
import segundum.pasarela.puerto.dto.UsuarioLoginResponseDTO;

public interface ClienteUsuarios {

	UsuarioLoginResponseDTO login(UsuarioLoginInputDTO dto) throws Exception;

	UsuarioLoginResponseDTO buscarPorGithubId(String githubId) throws Exception;

	UsuarioLoginResponseDTO crearUsuarioGithub(UsuarioGithubInputDTO dto) throws Exception;
}