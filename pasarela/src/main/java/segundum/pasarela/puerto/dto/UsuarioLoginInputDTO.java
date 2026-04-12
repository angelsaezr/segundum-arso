package segundum.pasarela.puerto.dto;

public class UsuarioLoginInputDTO {

	private String email;
	private String password;

	public UsuarioLoginInputDTO() {
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}