package segundum.productos.rest;

import java.util.List;

import javax.validation.Valid;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.Operation;
import segundum.productos.modelo.EstadoProducto;
import segundum.productos.rest.dto.ModificadoProductoDTO;
import segundum.productos.rest.dto.NuevoLugarDeRecogidaDTO;
import segundum.productos.rest.dto.NuevoProductoDTO;
import segundum.productos.rest.dto.ProductoDTO;
import segundum.productos.rest.dto.ProductoResumenDTO;
import segundum.productos.servicio.ProductoResumenMensual;

public interface ProductosApi {

	@Operation(summary = "Crear producto", description = "Crea un nuevo producto con los datos proporcionados")
	@PostMapping
	@PreAuthorize("hasAuthority('USER')")
	ResponseEntity<Void> createProducto(@Valid @RequestBody NuevoProductoDTO nuevoProductoDto) throws Exception;

	@Operation(summary = "Obtener producto", description = "Obtiene un producto por su id")
	@GetMapping("/{id}")
	EntityModel<ProductoDTO> getProductoById(@PathVariable String id) throws Exception;

	@Operation(summary = "Modificar producto", description = "Modifica los datos de un producto existente")
	@PatchMapping("/{id}")
	@PreAuthorize("hasAuthority('USER')")
	ResponseEntity<Void> modificarDatosProducto(@PathVariable String id,
			@Valid @RequestBody ModificadoProductoDTO modificadoProductoDto) throws Exception;

	@Operation(summary = "Asignar lugar de recogida", description = "Asigna un lugar de recogida a un producto")
	@PostMapping("/{id}/recogida")
	@PreAuthorize("hasAuthority('USER')")
	ResponseEntity<Void> asignarLugarRecogida(@PathVariable String id,
			@Valid @RequestBody NuevoLugarDeRecogidaDTO lugarRecogida) throws Exception;

	@Operation(summary = "Incrementar visualizaciones", description = "Incrementa el contador de visualizaciones de un producto")
	@PatchMapping("/{id}/visualizaciones")
	ResponseEntity<Void> incrementarVisualizaciones(@PathVariable String id) throws Exception;

	@Operation(summary = "Obtener resumen mensual", description = "Obtiene el resumen mensual de productos de un vendedor")
	@GetMapping("/historial")
	PagedModel<EntityModel<ProductoResumenMensual>> getResumenMensual(@RequestParam String idVendedor,
			@RequestParam int mes, @RequestParam int anyo, @ParameterObject Pageable paginacion) throws Exception;

	@Operation(summary = "Obtener productos paginados", description = "Obtiene una lista paginada de productos con su resumen")
	@GetMapping
	PagedModel<EntityModel<ProductoResumenDTO>> getProductosPaginado(@ParameterObject Pageable paginacion)
			throws Exception;

	@Operation(summary = "Buscar productos", description = "Busca productos por descripción, categoría, estado o precio máximo")
	@GetMapping("/buscar")
	List<ProductoDTO> buscarProductos(@RequestParam(required = false) String descripcion,
			@RequestParam(required = false) String idCategoria, @RequestParam(required = false) EstadoProducto estado,
			@RequestParam(required = false) Double precioMax) throws Exception;
}
