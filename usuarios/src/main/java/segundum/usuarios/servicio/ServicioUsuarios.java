package segundum.usuarios.servicio;

import java.util.Date;
import java.util.List;

import segundum.usuarios.modelo.Usuario;
import segundum.usuarios.repositorio.EntidadNoEncontrada;
import segundum.usuarios.repositorio.RepositorioException;
import segundum.usuarios.rest.dto.UsuarioInputDTO;

public interface ServicioUsuarios {
	Usuario login(String email, String clave) throws RepositorioException, EntidadNoEncontrada;

	String altaUsuario(UsuarioInputDTO usuarioInputDTO) throws RepositorioException;

	void modificarUsuario(String id, String email, String nombre, String apellidos, String clave, Date fechaNacimiento,
			String telefono, boolean administrador) throws RepositorioException, EntidadNoEncontrada;

	Usuario recuperar(String id) throws RepositorioException, EntidadNoEncontrada;

	List<Usuario> recuperarTodos() throws RepositorioException;
}
