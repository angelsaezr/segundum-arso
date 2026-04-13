package segundum.pasarela.puerto;

import segundum.pasarela.puerto.dto.UsuarioLoginInputDTO;
import segundum.pasarela.puerto.dto.UsuarioLoginResponseDTO;

public interface ClienteUsuarios {
	UsuarioLoginResponseDTO login(UsuarioLoginInputDTO dto) throws Exception;

	UsuarioLoginResponseDTO buscarPorEmail(String email) throws Exception;
}
