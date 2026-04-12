package segundum.pasarela.puerto.dto;

/**
 * DTO devuelto a la pasarela tras autenticar. Contiene los datos necesarios
 * para que la pasarela genere el JWT.
 */
public class UsuarioLoginResponseDTO {

	private String id;
	private String email;
	private String nombre;
	private String apellidos;
	private String roles;

	public UsuarioLoginResponseDTO() {
	}

	public UsuarioLoginResponseDTO(String id, String email, String nombre, String apellidos, String roles) {
		this.id = id;
		this.email = email;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.roles = roles;
	}

	public String getId() {
		return id;
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

	public String getRoles() {
		return roles;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}
}