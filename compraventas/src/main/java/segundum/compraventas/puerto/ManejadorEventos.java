package segundum.compraventas.puerto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import segundum.compraventas.repositorio.RepositorioCompraventas;

@Component
public class ManejadorEventos {

	@Autowired
	private RepositorioCompraventas repositorioCompraventas;

	public void usuarioModificado(String idUsuario, String nombre) {
		repositorioCompraventas.findByIdVendedor(idUsuario).forEach(compraventa -> {
			compraventa.setNombreVendedor(nombre);
			repositorioCompraventas.save(compraventa);
		});
		repositorioCompraventas.findByIdComprador(idUsuario).forEach(compraventa -> {
			compraventa.setNombreComprador(nombre);
			repositorioCompraventas.save(compraventa);
		});
	}

}
