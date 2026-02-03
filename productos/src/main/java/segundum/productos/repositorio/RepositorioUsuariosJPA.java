package segundum.productos.repositorio;

import segundum.productos.modelo.Usuario;

public class RepositorioUsuariosJPA extends RepositorioJPA<Usuario> {

	@Override
	public Class<Usuario> getClase() {
		return Usuario.class;
	}

}
