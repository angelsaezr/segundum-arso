package segundum.usuarios.repositorio;

import segundum.usuarios.modelo.Producto;

public class RepositorioProductosJPA extends RepositorioJPA<Producto> {

	@Override
	public Class<Producto> getClase() {
		return Producto.class;
	}

}
