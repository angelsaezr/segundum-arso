package segundum.compraventas.servicio;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import segundum.compraventas.evento.EventoCompraventaCreada;
import segundum.compraventas.modelo.Compraventa;
import segundum.compraventas.puerto.ClienteProductos;
import segundum.compraventas.puerto.ClienteUsuarios;
import segundum.compraventas.puerto.PublicadorEventos;
import segundum.compraventas.repositorio.RepositorioCompraventas;
import segundum.compraventas.rest.dto.CompraventaDTO;
import segundum.compraventas.rest.dto.ProductoDTO;

@Service
public class ServicioCompraventasImpl implements ServicioCompraventas {

	private final RepositorioCompraventas repositorioCompraventas;
	private final ClienteProductos clienteProductos;
	private final ClienteUsuarios clienteUsuarios;
	private final PublicadorEventos publicadorEventos;

	@Autowired
	public ServicioCompraventasImpl(RepositorioCompraventas repositorioCompraventas, ClienteProductos clienteProductos,
			ClienteUsuarios clienteUsuarios, PublicadorEventos publicadorEventos) {
		this.repositorioCompraventas = repositorioCompraventas;
		this.clienteProductos = clienteProductos;
		this.clienteUsuarios = clienteUsuarios;
		this.publicadorEventos = publicadorEventos;
	}

	@Override
	public Compraventa compraventa(String idProducto, String idComprador) throws Exception {
		// Obtener datos del producto desde el microservicio Productos
		ProductoDTO producto = clienteProductos.getProducto(idProducto);

		if (producto.isVendido()) {
			throw new IllegalArgumentException("El producto ya ha sido vendido.");
		}

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

		Compraventa compraventa = repositorioCompraventas.save(c);

		publicadorEventos.publicarEvento(
				new EventoCompraventaCreada(compraventa.getId(), idProducto, idComprador, producto.getIdVendedor()));

		return compraventa;

	}

	@Override
	public CompraventaDTO getCompraventaById(String id) {
		Compraventa compraventa = repositorioCompraventas.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("No existe compraventa con id: " + id));
		return CompraventaDTO.fromEntity(compraventa);
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
		return repositorioCompraventas.findAll(pageable).map(CompraventaDTO::fromEntity);
	}
}