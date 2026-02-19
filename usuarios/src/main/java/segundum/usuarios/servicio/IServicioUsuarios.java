package segundum.usuarios.servicio;

import java.time.LocalDate;

import segundum.usuarios.modelo.Usuario;
import segundum.usuarios.repositorio.EntidadNoEncontrada;
import segundum.usuarios.repositorio.RepositorioException;

public interface IServicioUsuarios {
	String altaUsuario(String email, String nombre, String apellidos, String clave, LocalDate fechaNacimiento,
			String telefono) throws RepositorioException;

	void modificarUsuario(String id, String email, String nombre, String apellidos, String clave,
			LocalDate fechaNacimiento, String telefono, boolean administrador)
			throws RepositorioException, EntidadNoEncontrada;
	
	Usuario recuperar(String id) throws RepositorioException, EntidadNoEncontrada;
}
