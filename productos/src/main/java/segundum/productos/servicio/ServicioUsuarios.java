package segundum.productos.servicio;

import java.time.LocalDate;

import segundum.productos.modelo.Usuario;
import segundum.productos.repositorio.EntidadNoEncontrada;
import segundum.productos.repositorio.FactoriaRepositorios;
import segundum.productos.repositorio.Repositorio;
import segundum.productos.repositorio.RepositorioException;

public class ServicioUsuarios implements IServicioUsuarios {

	private Repositorio<Usuario, String> repositorio = FactoriaRepositorios.getRepositorio(Usuario.class);

	@Override
	public String altaUsuario(String email, String nombre, String apellidos, String clave, LocalDate fechaNacimiento,
			String telefono) throws RepositorioException {
		// Control de integridad de los datos

		if (nombre == null || nombre.isEmpty())
			throw new IllegalArgumentException("nombre: no debe ser nulo ni vacio");

		if (apellidos == null || apellidos.isEmpty())
			throw new IllegalArgumentException("apellidos: no deben ser nulos ni vacios");

		if (email == null || email.isEmpty())
			throw new IllegalArgumentException("email: no debe ser nulo ni vacio");

		if (clave == null || clave.isEmpty())
			throw new IllegalArgumentException("clave: no debe ser nulo ni vacio");

		if (fechaNacimiento == null)
			throw new IllegalArgumentException("fecha nacimiento: no debe ser nula");

		Usuario usuario = new Usuario(email, nombre, apellidos);

		String id = repositorio.add(usuario);

		return id;
	}

	@Override
	public void modificarUsuario(String id, String email, String nombre, String apellidos, String clave,
			LocalDate fechaNacimiento, String telefono, boolean administrador)
			throws RepositorioException, EntidadNoEncontrada {

		if (id == null || id.isEmpty())
			throw new IllegalArgumentException("id: no debe ser nulo ni vacio");

		Usuario usuario = repositorio.getById(id);

		if (email != null && !email.isEmpty())
			usuario.setEmail(email);

		if (nombre != null && !nombre.isEmpty())
			usuario.setNombre(nombre);

		if (apellidos != null && !apellidos.isEmpty())
			usuario.setApellidos(apellidos);

		repositorio.update(usuario);
	}

}
