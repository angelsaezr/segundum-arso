package segundum.repositorio;

import segundum.modelo.Categoria;

public class RepositorioCategoriasJPA extends RepositorioJPA<Categoria> {

	@Override
	public Class<Categoria> getClase() {
		return Categoria.class;
	}

}
