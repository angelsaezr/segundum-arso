package segundum.usuarios.servicio;

import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

import segundum.usuarios.dto.UsuarioInputDTO;
import segundum.usuarios.modelo.Usuario;
import segundum.usuarios.repositorio.EntidadNoEncontrada;
import segundum.usuarios.repositorio.FactoriaRepositorios;
import segundum.usuarios.repositorio.Repositorio;
import segundum.usuarios.repositorio.RepositorioException;

public class ServicioUsuarios implements IServicioUsuarios {

	private final Repositorio<Usuario, String> repositorio = FactoriaRepositorios.getRepositorio(Usuario.class);

	@Override
	public String altaUsuario(UsuarioInputDTO dto) throws RepositorioException {
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
		return repositorio.add(usuario);
	}

	@Override
	public void modificarUsuario(String id, String email, String nombre, String apellidos, String clave,
			Date fechaNacimiento, String telefono, boolean administrador)
			throws RepositorioException, EntidadNoEncontrada {
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
