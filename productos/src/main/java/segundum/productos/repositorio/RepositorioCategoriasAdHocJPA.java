package segundum.productos.repositorio;

import segundum.productos.modelo.Categoria;

public class RepositorioCategoriasAdHocJPA extends RepositorioCategoriasJPA implements RepositorioCategoriasAdHoc {

	@Override
	public Class<Categoria> getClase() {
		return Categoria.class;
	}

}
