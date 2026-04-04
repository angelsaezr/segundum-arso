package segundum.usuarios.evento;

public class EventoUsuarioCreado extends Evento {

	public static final String TIPO = "usuario-creado";

	private final String email;
	private final String nombre;
	private final String apellidos;

	public EventoUsuarioCreado(String idUsuario, String email, String nombre, String apellidos) {
		super(idUsuario, TIPO);
		this.email = email;
		this.nombre = nombre;
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellidos() {
		return apellidos;
	}
}