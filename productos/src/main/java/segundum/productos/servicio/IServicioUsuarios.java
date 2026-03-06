package segundum.productos.servicio;

import java.time.LocalDate;

import segundum.productos.modelo.Usuario;
import segundum.productos.repositorio.EntidadNoEncontrada;

public interface IServicioUsuarios {
	String altaUsuario(String email, String nombre, String apellidos);

	Usuario getUsuario(String id) throws EntidadNoEncontrada;

	void modificarUsuario(String id, String email, String nombre, String apellidos, String clave,
			LocalDate fechaNacimiento, String telefono, boolean administrador) throws EntidadNoEncontrada;
}
