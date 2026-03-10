package segundum.usuarios.rest.dto;

import segundum.usuarios.modelo.Usuario;

/**
 * DTO resumen de un usuario para el listado. Incluye solo datos básicos y un
 * enlace al recurso completo.
 */
public class UsuarioResumenDTO {

	private String id;
	private String nombre;
	private String apellidos;
	private String email;
	private String url;

	public UsuarioResumenDTO() {
	}

	public UsuarioResumenDTO(String id, String nombre, String apellidos, String email, String url) {
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.url = url;
	}

	// Getters y Setters

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public static UsuarioResumenDTO fromEntity(Usuario usuario, String baseUrl) {
		return new UsuarioResumenDTO(usuario.getId(), usuario.getNombre(), usuario.getApellidos(), usuario.getEmail(),
				baseUrl + "/" + usuario.getId());
	}
}
