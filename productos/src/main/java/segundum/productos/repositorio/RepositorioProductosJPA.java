package segundum.productos.repositorio;

import segundum.productos.modelo.Producto;

public class RepositorioProductosJPA extends RepositorioJPA<Producto> {

	@Override
	public Class<Producto> getClase() {
		return Producto.class;
	}

}
