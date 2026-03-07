package segundum.usuarios.servicio;

import java.util.Date;
import java.util.List;

import segundum.usuarios.modelo.Usuario;
import segundum.usuarios.repositorio.EntidadNoEncontrada;
import segundum.usuarios.repositorio.FactoriaRepositorios;
import segundum.usuarios.repositorio.Repositorio;
import segundum.usuarios.repositorio.RepositorioException;

public class ServicioUsuarios implements IServicioUsuarios {

	private Repositorio<Usuario, String> repositorio = FactoriaRepositorios.getRepositorio(Usuario.class);

	@Override
	public String altaUsuario(String email, String nombre, String apellidos, String clave, Date fechaNacimiento,
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

		boolean emailYaExiste = repositorio.getAll().stream().anyMatch(u -> u.getEmail().equalsIgnoreCase(email));
		if (emailYaExiste)
			throw new IllegalArgumentException("email: ya existe un usuario registrado con ese email");

		Usuario usuario = new Usuario(email, nombre, apellidos, clave, fechaNacimiento, telefono, false);

		String id = repositorio.add(usuario);

		return id;
	}

	@Override
	public void modificarUsuario(String id, String email, String nombre, String apellidos, String clave,
			Date fechaNacimiento, String telefono, boolean administrador)
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

		if (clave != null && !clave.isEmpty())
			usuario.setClave(clave);

		if (fechaNacimiento != null)
			usuario.setFechaNacimiento(fechaNacimiento);

		if (telefono != null && !telefono.isEmpty())
			usuario.setTelefono(telefono);

		repositorio.update(usuario);
	}

	@Override
	public Usuario recuperar(String id) throws RepositorioException, EntidadNoEncontrada {
		if (id == null || id.isEmpty())
			throw new IllegalArgumentException("id: no debe ser nulo ni vacio");

		return repositorio.getById(id);
	}

	@Override
	public List<Usuario> recuperarTodos() throws RepositorioException {
		return repositorio.getAll();
	}
}
