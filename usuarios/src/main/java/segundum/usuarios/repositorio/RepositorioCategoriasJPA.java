package segundum.usuarios.repositorio;

import segundum.usuarios.modelo.Categoria;

public class RepositorioCategoriasJPA extends RepositorioJPA<Categoria> {

	@Override
	public Class<Categoria> getClase() {
		return Categoria.class;
	}

}
