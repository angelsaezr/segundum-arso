package segundum.compraventas.servicio;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import segundum.compraventas.modelo.Compraventa;
import segundum.compraventas.repositorio.RepositorioCompraventas;
import segundum.compraventas.rest.dto.CompraventaDTO;
import segundum.compraventas.rest.dto.ProductoDTO;

@Service
public class ServicioCompraventas implements IServicioCompraventas {

	private final RepositorioCompraventas repositorioCompraventas;
	private final IClienteProductos clienteProductos;
	private final IClienteUsuarios clienteUsuarios;

	@Autowired
	public ServicioCompraventas(RepositorioCompraventas repositorioCompraventas, IClienteProductos clienteProductos,
			IClienteUsuarios clienteUsuarios) {
		this.repositorioCompraventas = repositorioCompraventas;
		this.clienteProductos = clienteProductos;
		this.clienteUsuarios = clienteUsuarios;
	}

	@Override
	public Compraventa compraventa(String idProducto, String idComprador) throws Exception {
		// Obtener datos del producto desde el microservicio Productos
		ProductoDTO producto = clienteProductos.getProducto(idProducto);

		// Obtener nombres desde el microservicio Usuarios
		String nombreVendedor = clienteUsuarios.getNombreUsuario(producto.getIdVendedor());
		String nombreComprador = clienteUsuarios.getNombreUsuario(idComprador);

		Compraventa c = new Compraventa();
		c.setIdProducto(idProducto);
		c.setIdComprador(idComprador);
		c.setTitulo(producto.getTitulo());
		c.setPrecio(producto.getPrecio());
		c.setRecogida(producto.getRecogida());
		c.setIdVendedor(producto.getIdVendedor());
		c.setNombreVendedor(nombreVendedor);
		c.setNombreComprador(nombreComprador);
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

	@Override
	public Page<CompraventaDTO> getListadoPaginado(Pageable pageable) {
		return this.repositorioCompraventas.findAll(pageable).map(compraventa -> {
			return CompraventaDTO.fromEntity(compraventa);
		});
	}
}