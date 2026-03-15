package segundum.compraventas.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import segundum.compraventas.modelo.Compraventa;
import segundum.compraventas.repositorio.RepositorioCompraventas;

@Service
public class ServicioCompraventas implements IServicioCompraventas {

	@Autowired
	private RepositorioCompraventas repositorioCompraventas;

	@Autowired
	public ServicioCompraventas(RepositorioCompraventas repositorioCompraventas) {
		this.repositorioCompraventas = repositorioCompraventas;
	}

	@Override
	public Compraventa crearCompraventa(String titulo, double precio) {
		Compraventa nueva = new Compraventa();
		nueva.setTitulo(titulo);
		nueva.setPrecio(precio);
		return repositorioCompraventas.save(nueva);
	}
}