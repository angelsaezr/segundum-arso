package segundum.productos.servicio;

import java.time.LocalDate;

import segundum.productos.repositorio.EntidadNoEncontrada;
import segundum.productos.repositorio.RepositorioException;

public interface IServicioUsuarios {
	String altaUsuario(String email, String nombre, String apellidos, String clave, LocalDate fechaNacimiento,
			String telefono) throws RepositorioException;

	void modificarUsuario(String id, String email, String nombre, String apellidos, String clave,
			LocalDate fechaNacimiento, String telefono, boolean administrador)
			throws RepositorioException, EntidadNoEncontrada;
}
