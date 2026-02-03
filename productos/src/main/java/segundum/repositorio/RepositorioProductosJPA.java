package segundum.repositorio;

import segundum.modelo.Producto;

public class RepositorioProductosJPA extends RepositorioJPA<Producto> {

	@Override
	public Class<Producto> getClase() {
		return Producto.class;
	}

}
