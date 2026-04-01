package segundum.productos.puerto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import segundum.productos.repositorio.RepositorioProductos;

@Component
public class ManejadorEventos {

	@Autowired
	private RepositorioProductos repositorioProductos;

	public void compraventaCreada(String idProducto) {

		this.repositorioProductos.findById(idProducto).ifPresent(p -> {
			p.setVendido(true);
			this.repositorioProductos.save(p);
		});

		System.out.println("productos: compraventa creada");
	}

}
