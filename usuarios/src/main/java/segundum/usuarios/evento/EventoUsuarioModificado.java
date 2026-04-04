package segundum.usuarios.evento;

public class EventoUsuarioModificado extends Evento {

	public static final String TIPO = "usuario-modificado";

	private final String email;
	private final String nombre;
	private final String apellidos;

	public EventoUsuarioModificado(String idUsuario, String email, String nombre, String apellidos) {
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