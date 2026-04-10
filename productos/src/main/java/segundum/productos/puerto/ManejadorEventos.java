package segundum.productos.puerto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import segundum.productos.modelo.Usuario;
import segundum.productos.repositorio.RepositorioProductos;
import segundum.productos.repositorio.RepositorioUsuarios;

@Component
public class ManejadorEventos {

	@Autowired
	private RepositorioProductos repositorioProductos;
	@Autowired
	private RepositorioUsuarios repositorioUsuarios;

	public void compraventaCreada(String idProducto) {
		this.repositorioProductos.findById(idProducto).ifPresent(p -> {
			p.setVendido(true);
			this.repositorioProductos.save(p);
		});

		System.out.println("productos: compraventa creada");
	}

	public void usuarioCreado(String idUsuario, String email, String nombre, String apellidos) {
		Usuario usuario = new Usuario(email, nombre, apellidos);
		usuario.setId(idUsuario);
		this.repositorioUsuarios.save(usuario);
		System.out.println("productos: usuario creado");
	}

}
