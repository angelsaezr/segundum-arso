package segundum.productos.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import segundum.productos.repositorio.Identificable;

@Entity
@Table(name = "usuarios")
public class Usuario implements Identificable {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private String id;

	private String email;

	private String nombre;

	private String apellidos;

	public Usuario() {

	}

	public Usuario(String email, String nombre, String apellidos) {
		this.email = email;
		this.nombre = nombre;
		this.apellidos = apellidos;
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

	@Override
	public String toString() {
		return "Usuario{" + "id='" + id + '\'' + ", email='" + email + '\'' + ", nombre='" + nombre + '\''
				+ ", apellidos='" + apellidos + '}';
	}
}
