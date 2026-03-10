package segundum.usuarios.rest.dto;

import java.util.Date;

import segundum.usuarios.modelo.Usuario;

/**
 * DTO con la información completa de un usuario.
 */
public class UsuarioDTO {

	private String id;
	private String email;
	private String nombre;
	private String apellidos;
	private Date fechaNacimiento;
	private String telefono;
	private boolean administrador;

	public UsuarioDTO() {
	}

	public UsuarioDTO(String id, String email, String nombre, String apellidos, Date fechaNacimiento, String telefono,
			boolean administrador) {
		this.id = id;
		this.email = email;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
		this.telefono = telefono;
		this.administrador = administrador;

	}

	// Getters y Setters

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public boolean isAdministrador() {
		return administrador;
	}

	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}

	public static UsuarioDTO fromEntity(Usuario usuario) {
		return new UsuarioDTO(usuario.getId(), usuario.getEmail(), usuario.getNombre(), usuario.getApellidos(),
				usuario.getFechaNacimiento(), usuario.getTelefono(), usuario.isAdministrador());
	}
}
