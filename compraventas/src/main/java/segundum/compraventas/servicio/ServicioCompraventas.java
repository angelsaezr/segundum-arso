package segundum.compraventas.servicio;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import segundum.compraventas.modelo.Compraventa;
import segundum.compraventas.repositorio.RepositorioCompraventas;

@Service
public class ServicioCompraventas implements IServicioCompraventas {

	private final RepositorioCompraventas repositorioCompraventas;

	@Autowired
	public ServicioCompraventas(RepositorioCompraventas repositorioCompraventas) {
		this.repositorioCompraventas = repositorioCompraventas;
	}

	@Override
	public Compraventa compraventa(String idProducto, String idComprador) {
		// TODO: llamar al microservicio Productos para obtener titulo, precio, recogida
		// e idVendedor
		// TODO: llamar al microservicio Usuarios para obtener nombreComprador y
		// nombreVendedor
		// Por ahora se usan datos simulados
		Compraventa c = new Compraventa();
		c.setIdProducto(idProducto);
		c.setIdComprador(idComprador);
		c.setTitulo("Producto simulado");
		c.setPrecio(100.0);
		c.setRecogida("Calle Mayor 1, Madrid");
		c.setIdVendedor("vendedor-001");
		c.setNombreVendedor("Juan García");
		c.setNombreComprador("María López");
		c.setFecha(LocalDateTime.now());
		return repositorioCompraventas.save(c);
	}

	@Override
	public List<Compraventa> recuperarComprasUsuario(String idComprador) {
		return repositorioCompraventas.findByIdComprador(idComprador);
	}

	@Override
	public List<Compraventa> recuperarVentasUsuario(String idVendedor) {
		return repositorioCompraventas.findByIdVendedor(idVendedor);
	}

	@Override
	public List<Compraventa> recuperarCompraventasEntreUsuarios(String idComprador, String idVendedor) {
		return repositorioCompraventas.findByIdCompradorAndIdVendedor(idComprador, idVendedor);
	}
}