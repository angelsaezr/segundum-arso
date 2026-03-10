package segundum.productos.rest;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import segundum.productos.modelo.EstadoProducto;
import segundum.productos.modelo.Producto;
import segundum.productos.rest.dto.NuevoLugarDeRecogidaDto;
import segundum.productos.rest.dto.NuevoProductoDto;
import segundum.productos.rest.dto.ProductoDto;
import segundum.productos.servicio.IServicioProductos;
import segundum.productos.servicio.ProductoResumenMensual;

@RestController
@RequestMapping("/productos")
public class ProductosController {

	private IServicioProductos serviciosProductos;

	@Autowired
	public ProductosController(IServicioProductos serviciosProductos) {
		this.serviciosProductos = serviciosProductos;
	}

	@PostMapping
	public ResponseEntity<Void> createProducto(@RequestBody NuevoProductoDto nuevoProducto) throws Exception {
		String id = this.serviciosProductos.crear(nuevoProducto.getTitulo(), nuevoProducto.getDescripcion(),
				nuevoProducto.getPrecio(), nuevoProducto.getEstado(), nuevoProducto.isEnvioDisponible(),
				nuevoProducto.getIdCategoria(), nuevoProducto.getIdVendedor());

		URI nuevaURL = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();

		return ResponseEntity.created(nuevaURL).build();
	}

	@GetMapping("/{id}")
	public ProductoDto getProducto(@PathVariable String id) throws Exception {
		Producto producto = this.serviciosProductos.getProducto(id);
		return ProductoDto.fromEntity(producto);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Void> modificarDatosProducto(@PathVariable String id,
			@RequestBody NuevoProductoDto productoDto) throws Exception {
		this.serviciosProductos.modificarDatosProducto(id, productoDto.getPrecio(), productoDto.getDescripcion());
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/{id}/recogida")
	public ResponseEntity<Void> asignarLugarRecogida(@PathVariable String id,
			@RequestBody NuevoLugarDeRecogidaDto lugarRecogida) throws Exception {
		this.serviciosProductos.asignarLugarRecogida(id, lugarRecogida.getLongitud(), lugarRecogida.getLatitud(),
				lugarRecogida.getDescripcion());
		return ResponseEntity.noContent().build();
	}

	@PatchMapping("/{id}/visualizaciones")
	public ResponseEntity<Void> incrementarVisualizaciones(@PathVariable String id) throws Exception {
		this.serviciosProductos.incrementarVisualizaciones(id);
		return ResponseEntity.noContent().build();
	}

	// TODO: Pageable
	@GetMapping("/historial?idVendedor={idVendedor}&mes={mes}&año={año}")
	public List<ProductoResumenMensual> getResumenMensual(@PathVariable String idVendedor, @PathVariable int mes,
			@PathVariable int año) throws Exception {
		return this.serviciosProductos.getResumenMensual(idVendedor, mes, año);
	}

	// TODO: Borrar esto, es solo para pruebas
	// http://localhost:8080/productos?page=0&size=10&sort=id,titulo
	@GetMapping
	public Page<ProductoDto> getProductosPaginado(Pageable pageable) throws Exception {
		return this.serviciosProductos.getListadoPaginado(pageable);
	}

	@GetMapping("/buscar?descripcion={descripcion}&idCategoria={idCategoria}&estado={estado}&precioMax={precioMax}")
	public List<ProductoDto> buscarProductos(@PathVariable String descripcion, @PathVariable String idCategoria,
			@PathVariable String estado, @PathVariable Double precioMax) throws Exception {
		return this.serviciosProductos
				.buscarProductos(descripcion, idCategoria, EstadoProducto.valueOf(estado), precioMax).stream()
				.map(ProductoDto::fromEntity).collect(Collectors.toList());
	}
}
