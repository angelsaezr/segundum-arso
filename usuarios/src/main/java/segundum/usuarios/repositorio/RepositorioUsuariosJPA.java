package segundum.usuarios.repositorio;

import segundum.usuarios.modelo.Usuario;

public class RepositorioUsuariosJPA extends RepositorioJPA<Usuario> {

	@Override
	public Class<Usuario> getClase() {
		return Usuario.class;
	}

}
