package segundum.productos.rest;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import segundum.productos.modelo.EstadoProducto;
import segundum.productos.modelo.Producto;
import segundum.productos.rest.dto.ModificadoProductoDTO;
import segundum.productos.rest.dto.NuevoLugarDeRecogidaDTO;
import segundum.productos.rest.dto.NuevoProductoDTO;
import segundum.productos.rest.dto.ProductoDTO;
import segundum.productos.rest.dto.ProductoResumenDTO;
import segundum.productos.servicio.ProductoResumenMensual;
import segundum.productos.servicio.ServicioProductos;

@RestController
@RequestMapping("/productos")
public class ProductosController implements ProductosApi {

	private ServicioProductos serviciosProductos;

	@Autowired
	private PagedResourcesAssembler<ProductoResumenDTO> pagedResourcesAssembler;

	@Autowired
	private PagedResourcesAssembler<ProductoResumenMensual> pagedResourcesAssemblerMensual;

	@Autowired
	private ProductoResumenAssembler productoResumenAssembler;

	@Autowired
	private ProductoResumenMensualAssembler productoResumenAssemblerMensual;

	@Autowired
	public ProductosController(ServicioProductos serviciosProductos) {
		this.serviciosProductos = serviciosProductos;
	}

	@Override
	public ResponseEntity<Void> createProducto(NuevoProductoDTO nuevoProductoDto) throws Exception {
		// El usuario autenticado debe ser el vendedor del producto
		String idUsuarioAutenticado = getIdUsuarioAutenticado();
		if (!idUsuarioAutenticado.equals(nuevoProductoDto.getIdVendedor())) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN,
					"Solo puedes dar de alta productos como vendedor propio");
		}

		String id = this.serviciosProductos.crear(nuevoProductoDto.getTitulo(), nuevoProductoDto.getDescripcion(),
				nuevoProductoDto.getPrecio(), nuevoProductoDto.getEstado(), nuevoProductoDto.isEnvioDisponible(),
				nuevoProductoDto.getIdCategoria(), nuevoProductoDto.getIdVendedor());

		URI nuevaURL = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
		return ResponseEntity.created(nuevaURL).build();
	}

	@Override
	public EntityModel<ProductoDTO> getProductoById(String id) throws Exception {
		Producto producto = this.serviciosProductos.getProducto(id);
		ProductoDTO productoDto = ProductoDTO.fromEntity(producto);

		EntityModel<ProductoDTO> model = EntityModel.of(productoDto);
		model.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProductosController.class).getProductoById(id))
				.withSelfRel());
		return model;
	}

	@Override
	public ResponseEntity<Void> modificarDatosProducto(String id, ModificadoProductoDTO modificadoProductoDto)
			throws Exception {
		// Verificar que el usuario autenticado es el propietario
		verificarPropietario(id);

		this.serviciosProductos.modificarDatosProducto(id, modificadoProductoDto.getPrecio(),
				modificadoProductoDto.getDescripcion());
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Void> asignarLugarRecogida(String id, NuevoLugarDeRecogidaDTO lugarRecogida)
			throws Exception {
		// Verificar que el usuario autenticado es el propietario
		verificarPropietario(id);

		this.serviciosProductos.asignarLugarRecogida(id, lugarRecogida.getLongitud(), lugarRecogida.getLatitud(),
				lugarRecogida.getDescripcion());
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Void> incrementarVisualizaciones(String id) throws Exception {
		this.serviciosProductos.incrementarVisualizaciones(id);
		return ResponseEntity.noContent().build();
	}

	@Override
	public PagedModel<EntityModel<ProductoResumenMensual>> getResumenMensual(String idVendedor, int mes, int anyo,
			Pageable paginacion) throws Exception {
		Page<ProductoResumenMensual> resultado = this.serviciosProductos.getResumenMensual(idVendedor, mes, anyo,
				paginacion);
		return this.pagedResourcesAssemblerMensual.toModel(resultado, productoResumenAssemblerMensual);
	}

	@Override
	public PagedModel<EntityModel<ProductoResumenDTO>> getProductosPaginado(Pageable paginacion) throws Exception {
		Page<ProductoResumenDTO> resultado = this.serviciosProductos.getListadoPaginado(paginacion);
		return this.pagedResourcesAssembler.toModel(resultado, productoResumenAssembler);
	}

	@Override
	public List<ProductoDTO> buscarProductos(String descripcion, String idCategoria, EstadoProducto estado,
			Double precioMax) throws Exception {
		return this.serviciosProductos.buscarProductos(descripcion, idCategoria, estado, precioMax).stream()
				.map(ProductoDTO::fromEntity).collect(Collectors.toList());
	}

	// Métodos auxiliares

	// Devuelve el id del usuario autenticado (extraído del JWT por el filtro)
	private String getIdUsuarioAutenticado() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null || auth.getPrincipal() == null) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "No autenticado");
		}
		return auth.getName(); // getName() devuelve el "sub" del JWT
	}

	// Verifica que el usuario autenticado sea el propietario (vendedor) del
	// producto
	private void verificarPropietario(String idProducto) throws Exception {
		Producto producto = this.serviciosProductos.getProducto(idProducto);
		String idUsuarioAutenticado = getIdUsuarioAutenticado();
		if (!idUsuarioAutenticado.equals(producto.getVendedor().getId())) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN,
					"Solo el propietario puede realizar esta operación");
		}
	}
}