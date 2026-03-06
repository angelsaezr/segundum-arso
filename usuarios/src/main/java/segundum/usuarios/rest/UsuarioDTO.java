package segundum.usuarios.rest;

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

	public UsuarioDTO(Usuario usuario) {
		this.id = usuario.getId();
		this.email = usuario.getEmail();
		this.nombre = usuario.getNombre();
		this.apellidos = usuario.getApellidos();
		this.fechaNacimiento = usuario.getFechaNacimiento();
		this.telefono = usuario.getTelefono();
		this.administrador = usuario.isAdministrador();
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
}
