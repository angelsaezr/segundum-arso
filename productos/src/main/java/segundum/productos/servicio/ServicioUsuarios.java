package segundum.productos.servicio;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import segundum.productos.modelo.Usuario;
import segundum.productos.repositorio.EntidadNoEncontrada;
import segundum.productos.repositorio.RepositorioUsuarios;

public class ServicioUsuarios implements IServicioUsuarios {

	private RepositorioUsuarios repositorioUsuarios;

	@Autowired
	public ServicioUsuarios(RepositorioUsuarios repositorioUsuarios) {
		this.repositorioUsuarios = repositorioUsuarios;
	}

	@Override
	public String altaUsuario(String email, String nombre, String apellidos) {
		// Control de integridad de los datos

		if (nombre == null || nombre.isEmpty())
			throw new IllegalArgumentException("nombre: no debe ser nulo ni vacio");

		if (apellidos == null || apellidos.isEmpty())
			throw new IllegalArgumentException("apellidos: no deben ser nulos ni vacios");

		if (email == null || email.isEmpty())
			throw new IllegalArgumentException("email: no debe ser nulo ni vacio");

		Usuario usuario = new Usuario(email, nombre, apellidos);

		String id = repositorioUsuarios.save(usuario).getId();

		return id;
	}

	@Override
	public void modificarUsuario(String id, String email, String nombre, String apellidos, String clave,
			LocalDate fechaNacimiento, String telefono, boolean administrador) throws EntidadNoEncontrada {

		if (id == null || id.isEmpty())
			throw new IllegalArgumentException("id: no debe ser nulo ni vacio");

		// Usuario usuario = repositorioUsuarios.getById(id);
		Optional<Usuario> optionalUsuario = repositorioUsuarios.findById(id);
		if (!optionalUsuario.isPresent())
			throw new EntidadNoEncontrada("No se ha encontrado el usuario con id: " + id);
		Usuario usuario = optionalUsuario.get();

		if (email != null && !email.isEmpty())
			usuario.setEmail(email);

		if (nombre != null && !nombre.isEmpty())
			usuario.setNombre(nombre);

		if (apellidos != null && !apellidos.isEmpty())
			usuario.setApellidos(apellidos);

		repositorioUsuarios.save(usuario);
	}

}
