package segundum.usuarios.puerto;

import segundum.usuarios.modelo.Usuario;
import segundum.usuarios.repositorio.EntidadNoEncontrada;
import segundum.usuarios.repositorio.RepositorioException;
import segundum.usuarios.repositorio.RepositorioUsuariosJPA;

public class ManejadorEventos {

	private RepositorioUsuariosJPA repositorioUsuarios;

	public void compraventaCreada(String idComprador, String idVendedor)
			throws EntidadNoEncontrada, RepositorioException {

		Usuario comprador = this.repositorioUsuarios.getById(idComprador);
		comprador.incrementarCompras();
		this.repositorioUsuarios.update(comprador);

		Usuario vendedor = this.repositorioUsuarios.getById(idVendedor);
		vendedor.incrementarVentas();
		this.repositorioUsuarios.update(vendedor);

		System.out.println("usuarios: compraventa creada");
	}

}
