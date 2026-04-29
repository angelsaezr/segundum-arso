package segundum.usuarios.rest.dto;

import java.util.List;

/**
 * Contenedor para el listado de usuarios con enlaces.
 */
public class ListadoUsuarios {

	private List<UsuarioResumenDTO> usuarios;

	public ListadoUsuarios() {
	}

	public ListadoUsuarios(List<UsuarioResumenDTO> usuarios) {
		this.usuarios = usuarios;
	}

	public List<UsuarioResumenDTO> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<UsuarioResumenDTO> usuarios) {
		this.usuarios = usuarios;
	}
}
