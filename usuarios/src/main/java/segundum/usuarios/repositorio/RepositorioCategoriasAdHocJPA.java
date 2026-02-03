package segundum.usuarios.repositorio;

import segundum.usuarios.modelo.Categoria;

public class RepositorioCategoriasAdHocJPA extends RepositorioCategoriasJPA implements RepositorioCategoriasAdHoc {

	@Override
	public Class<Categoria> getClase() {
		return Categoria.class;
	}

}
