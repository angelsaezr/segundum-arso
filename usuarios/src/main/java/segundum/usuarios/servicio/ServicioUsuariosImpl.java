package segundum.usuarios.servicio;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

import segundum.usuarios.adaptador.PublicadorEventosRabbitMQ;
import segundum.usuarios.evento.EventoUsuarioCreado;
import segundum.usuarios.evento.EventoUsuarioModificado;
import segundum.usuarios.modelo.Usuario;
import segundum.usuarios.puerto.PublicadorEventos;
import segundum.usuarios.repositorio.EntidadNoEncontrada;
import segundum.usuarios.repositorio.FactoriaRepositorios;
import segundum.usuarios.repositorio.Repositorio;
import segundum.usuarios.repositorio.RepositorioException;
import segundum.usuarios.rest.dto.UsuarioInputDTO;

public class ServicioUsuariosImpl implements ServicioUsuarios {

	private final Repositorio<Usuario, String> repositorio = FactoriaRepositorios.getRepositorio(Usuario.class);

	private final PublicadorEventos publicadorEventos = new PublicadorEventosRabbitMQ();

	@Override
	public Usuario login(String email, String clave) throws RepositorioException {
		validarNoVacio(email, "email");
		validarNoVacio(clave, "clave");

		return repositorio.getAll().stream()
				.filter(u -> u.getEmail().equalsIgnoreCase(email) && u.getClave().equals(clave)).findFirst()
				.orElse(null);
	}

	@Override
	public String altaUsuario(UsuarioInputDTO dto) throws RepositorioException, IOException {
		if (dto == null)
			throw new IllegalArgumentException("usuario: no puede ser nulo");
		validarNoVacio(dto.getNombre(), "nombre");
		validarNoVacio(dto.getApellidos(), "apellidos");
		validarNoVacio(dto.getEmail(), "email");
		validarNoVacio(dto.getClave(), "clave");
		if (dto.getFechaNacimiento() == null)
			throw new IllegalArgumentException("fecha nacimiento: no debe ser nula");

		if (repositorio.getAll().stream().anyMatch(u -> u.getEmail().equalsIgnoreCase(dto.getEmail()))) {
			throw new IllegalArgumentException("email: ya existe un usuario registrado con ese email");
		}

		Usuario usuario = new Usuario(dto);
		String id = repositorio.add(usuario);

		publicadorEventos.publicarEvento(
				new EventoUsuarioCreado(id, usuario.getEmail(), usuario.getNombre(), usuario.getApellidos()));

		return id;
	}

	@Override
	public void modificarUsuario(String id, String email, String nombre, String apellidos, String clave,
			Date fechaNacimiento, String telefono, boolean administrador)
			throws RepositorioException, EntidadNoEncontrada, IOException {
		validarNoVacio(id, "id");

		Usuario usuario = repositorio.getById(id);

		actualizarSiNoVacio(email, usuario::setEmail);
		actualizarSiNoVacio(nombre, usuario::setNombre);
		actualizarSiNoVacio(apellidos, usuario::setApellidos);
		actualizarSiNoVacio(clave, usuario::setClave);
		actualizarSiNoVacio(telefono, usuario::setTelefono);
		if (fechaNacimiento != null)
			usuario.setFechaNacimiento(fechaNacimiento);
		usuario.setAdministrador(administrador);

		repositorio.update(usuario);

		publicadorEventos.publicarEvento(
				new EventoUsuarioModificado(id, usuario.getEmail(), usuario.getNombre(), usuario.getApellidos()));
	}

	@Override
	public Usuario recuperar(String id) throws RepositorioException, EntidadNoEncontrada {
		validarNoVacio(id, "id");
		return repositorio.getById(id);
	}

	@Override
	public List<Usuario> recuperarTodos() throws RepositorioException {
		return repositorio.getAll();
	}

	private void validarNoVacio(String valor, String campo) {
		if (valor == null || valor.isEmpty())
			throw new IllegalArgumentException(campo + ": no debe ser nulo ni vacio");
	}

	private void actualizarSiNoVacio(String valor, Consumer<String> setter) {
		if (valor != null && !valor.isEmpty()) {
			setter.accept(valor);
		}
	}
}