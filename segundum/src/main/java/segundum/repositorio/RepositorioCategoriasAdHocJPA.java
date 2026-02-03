package segundum.repositorio;

import segundum.modelo.Categoria;

public class RepositorioCategoriasAdHocJPA extends RepositorioCategoriasJPA implements RepositorioCategoriasAdHoc {

	@Override
	public Class<Categoria> getClase() {
		return Categoria.class;
	}

}
