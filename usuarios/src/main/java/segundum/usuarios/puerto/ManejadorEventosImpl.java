package segundum.usuarios.puerto;

import segundum.usuarios.modelo.Usuario;
import segundum.usuarios.repositorio.EntidadNoEncontrada;
import segundum.usuarios.repositorio.FactoriaRepositorios;
import segundum.usuarios.repositorio.Repositorio;
import segundum.usuarios.repositorio.RepositorioException;

public class ManejadorEventosImpl implements ManejadorEventos {

	private final Repositorio<Usuario, String> repositorioUsuarios = FactoriaRepositorios.getRepositorio(Usuario.class);

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
