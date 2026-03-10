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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import segundum.productos.modelo.EstadoProducto;
import segundum.productos.modelo.Producto;
import segundum.productos.rest.dto.ModificadoProductoDto;
import segundum.productos.rest.dto.NuevoLugarDeRecogidaDto;
import segundum.productos.rest.dto.NuevoProductoDto;
import segundum.productos.rest.dto.ProductoDto;
import segundum.productos.rest.dto.ProductoResumen;
import segundum.productos.servicio.IServicioProductos;
import segundum.productos.servicio.ProductoResumenMensual;

@RestController
@RequestMapping("/productos")
public class ProductosController implements ProductosApi {

	private IServicioProductos serviciosProductos;

	@Autowired
	private PagedResourcesAssembler<ProductoResumen> pagedResourcesAssembler;

	@Autowired
	private PagedResourcesAssembler<ProductoResumenMensual> pagedResourcesAssemblerMensual;

	@Autowired
	private ProductoResumenAssembler productoResumenAssembler;

	@Autowired
	private ProductoResumenMensualAssembler productoResumenAssemblerMensual;

	@Autowired
	public ProductosController(IServicioProductos serviciosProductos) {
		this.serviciosProductos = serviciosProductos;
	}

	@Override
	public ResponseEntity<Void> createProducto(NuevoProductoDto nuevoProductoDto) throws Exception {
		String id = this.serviciosProductos.crear(nuevoProductoDto.getTitulo(), nuevoProductoDto.getDescripcion(),
				nuevoProductoDto.getPrecio(), nuevoProductoDto.getEstado(), nuevoProductoDto.isEnvioDisponible(),
				nuevoProductoDto.getIdCategoria(), nuevoProductoDto.getIdVendedor());

		URI nuevaURL = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();

		return ResponseEntity.created(nuevaURL).build();
	}

	@Override
	public EntityModel<ProductoDto> getProductoById(String id) throws Exception {
		Producto producto = this.serviciosProductos.getProducto(id);
		ProductoDto productoDto = ProductoDto.fromEntity(producto);

		EntityModel<ProductoDto> model = EntityModel.of(productoDto);
		model.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProductosController.class).getProductoById(id))
				.withSelfRel());
		return model;
	}

	@Override
	public ResponseEntity<Void> modificarDatosProducto(String id, ModificadoProductoDto modificadoProductoDto)
			throws Exception {
		this.serviciosProductos.modificarDatosProducto(id, modificadoProductoDto.getPrecio(),
				modificadoProductoDto.getDescripcion());
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Void> asignarLugarRecogida(String id, NuevoLugarDeRecogidaDto lugarRecogida)
			throws Exception {
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
	public PagedModel<EntityModel<ProductoResumenMensual>> getResumenMensual(String idVendedor, int mes, int año,
			Pageable paginacion) throws Exception {
		Page<ProductoResumenMensual> resultado = this.serviciosProductos.getResumenMensual(idVendedor, mes, año,
				paginacion);

		return this.pagedResourcesAssemblerMensual.toModel(resultado, productoResumenAssemblerMensual);
	}

	@Override
	public PagedModel<EntityModel<ProductoResumen>> getProductosPaginado(Pageable paginacion) throws Exception {
		Page<ProductoResumen> resultado = this.serviciosProductos.getListadoPaginado(paginacion);

		return this.pagedResourcesAssembler.toModel(resultado, productoResumenAssembler);
	}

	@Override
	public List<ProductoDto> buscarProductos(String descripcion, String idCategoria, EstadoProducto estado,
			Double precioMax) throws Exception {
		return this.serviciosProductos.buscarProductos(descripcion, idCategoria, estado, precioMax).stream()
				.map(ProductoDto::fromEntity).collect(Collectors.toList());
	}
}
