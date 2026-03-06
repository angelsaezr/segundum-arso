package segundum.usuarios.rest;

import segundum.usuarios.modelo.Usuario;

/**
 * DTO resumen de un usuario para el listado.
 * Incluye solo datos básicos y un enlace al recurso completo.
 */
public class UsuarioResumenDTO {

	private String id;
	private String nombre;
	private String apellidos;
	private String email;
	private String url;

	public UsuarioResumenDTO() {
	}

	public UsuarioResumenDTO(Usuario usuario, String baseUri) {
		this.id = usuario.getId();
		this.nombre = usuario.getNombre();
		this.apellidos = usuario.getApellidos();
		this.email = usuario.getEmail();
		this.url = baseUri + "/" + usuario.getId();
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
}
