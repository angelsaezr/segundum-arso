package segundum.pasarela.puerto.dto;

/**
 * DTO enviado desde la pasarela al microservicio Usuarios para registrar un
 * nuevo usuario de GitHub.
 */
public class UsuarioGithubInputDTO {

	private String githubId;
	private String nombre;
	private String apellidos;
	private String email;

	public UsuarioGithubInputDTO() {
	}

	public UsuarioGithubInputDTO(String githubId, String nombre, String apellidos, String email) {
		this.githubId = githubId;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
	}

	public String getGithubId() {
		return githubId;
	}

	public void setGithubId(String githubId) {
		this.githubId = githubId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
