package segundum.usuarios.modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import segundum.usuarios.repositorio.Identificable;
import segundum.usuarios.rest.dto.UsuarioInputDTO;

@XmlRootElement(name = "usuario")
@Entity
@Table(name = "usuarios")
public class Usuario implements Identificable {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private String id;

	private String email;
	private String nombre;
	private String apellidos;
	private String clave;
	private Date fechaNacimiento;
	private String telefono;
	private boolean administrador;
	private int contadorCompras;
	private int contadorVentas;
	private String githubId;

	public Usuario() {
	}

	public Usuario(String email, String nombre, String apellidos, String clave, Date fechaNacimiento, String telefono,
			boolean administrador) {
		this.email = email;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.clave = clave;
		this.fechaNacimiento = fechaNacimiento;
		this.telefono = telefono;
		this.administrador = administrador;
		this.contadorCompras = 0;
		this.contadorVentas = 0;
	}

	public Usuario(UsuarioInputDTO usuarioInputDTO) {
		this.email = usuarioInputDTO.getEmail();
		this.nombre = usuarioInputDTO.getNombre();
		this.apellidos = usuarioInputDTO.getApellidos();
		this.clave = usuarioInputDTO.getClave();
		this.fechaNacimiento = usuarioInputDTO.getFechaNacimiento();
		this.telefono = usuarioInputDTO.getTelefono();
		this.administrador = usuarioInputDTO.isAdministrador();
		this.contadorCompras = 0;
		this.contadorVentas = 0;
	}

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

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
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

	public int getContadorCompras() {
		return contadorCompras;
	}

	public void setContadorCompras(int contadorCompras) {
		this.contadorCompras = contadorCompras;
	}

	public int getContadorVentas() {
		return contadorVentas;
	}

	public void setContadorVentas(int contadorVentas) {
		this.contadorVentas = contadorVentas;
	}

	public void incrementarCompras() {
		this.contadorCompras++;
	}

	public void incrementarVentas() {
		this.contadorVentas++;
	}

	public String getGithubId() {
		return githubId;
	}

	public void setGithubId(String githubId) {
		this.githubId = githubId;
	}

	@Override
	public String toString() {
		return "Usuario{id='" + id + "', email='" + email + "', nombre='" + nombre + "', apellidos='" + apellidos
				+ "', githubId='" + githubId + "', contadorCompras=" + contadorCompras + ", contadorVentas="
				+ contadorVentas + '}';
	}
}