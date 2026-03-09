package segundum.usuarios.servicio;

import java.util.Date;
import java.util.List;

import segundum.usuarios.dto.UsuarioInputDTO;
import segundum.usuarios.modelo.Usuario;
import segundum.usuarios.repositorio.EntidadNoEncontrada;
import segundum.usuarios.repositorio.RepositorioException;

public interface IServicioUsuarios {
	String altaUsuario(UsuarioInputDTO usuarioInputDTO) throws RepositorioException;

	void modificarUsuario(String id, String email, String nombre, String apellidos, String clave, Date fechaNacimiento,
			String telefono, boolean administrador) throws RepositorioException, EntidadNoEncontrada;

	Usuario recuperar(String id) throws RepositorioException, EntidadNoEncontrada;

	List<Usuario> recuperarTodos() throws RepositorioException;
}
