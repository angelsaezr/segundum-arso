package segundum.productos.rest;

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
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import segundum.productos.modelo.EstadoProducto;
import segundum.productos.rest.dto.ModificadoProductoDTO;
import segundum.productos.rest.dto.NuevoLugarDeRecogidaDTO;
import segundum.productos.rest.dto.NuevoProductoDTO;
import segundum.productos.rest.dto.ProductoDTO;
import segundum.productos.rest.dto.ProductoResumenDTO;
import segundum.productos.servicio.ProductoResumenMensual;

public interface ProductosApi {

	@Operation(summary = "Crear producto", description = "Crea un nuevo producto con los datos proporcionados")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Producto creado exitosamente", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
			@ApiResponse(responseCode = "403", description = "Solo puedes dar de alta productos como vendedor propio"),
			@ApiResponse(responseCode = "401", description = "No autenticado") })
	@PostMapping
	@PreAuthorize("hasAuthority('USUARIO')")
	ResponseEntity<Void> createProducto(@Valid @RequestBody NuevoProductoDTO nuevoProductoDto) throws Exception;

	@Operation(summary = "Obtener producto", description = "Obtiene un producto por su id con toda su información")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Producto encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductoDTO.class))),
			@ApiResponse(responseCode = "404", description = "Producto no encontrado"),
			@ApiResponse(responseCode = "401", description = "No autenticado") })
	@GetMapping("/{id}")
	EntityModel<ProductoDTO> getProductoById(
			@Parameter(description = "Identificador del producto", required = true) @PathVariable String id)
			throws Exception;

	@Operation(summary = "Modificar producto", description = "Modifica los datos de un producto existente")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Producto modificado exitosamente"),
			@ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
			@ApiResponse(responseCode = "403", description = "Solo el propietario puede realizar esta operación"),
			@ApiResponse(responseCode = "404", description = "Producto no encontrado"),
			@ApiResponse(responseCode = "401", description = "No autenticado") })
	@PatchMapping("/{id}")
	@PreAuthorize("hasAuthority('USUARIO')")
	ResponseEntity<Void> modificarDatosProducto(
			@Parameter(description = "Identificador del producto", required = true) @PathVariable String id,
			@Valid @RequestBody ModificadoProductoDTO modificadoProductoDto) throws Exception;

	@Operation(summary = "Asignar lugar de recogida", description = "Asigna un lugar de recogida a un producto")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Lugar de recogida asignado exitosamente"),
			@ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
			@ApiResponse(responseCode = "403", description = "Solo el propietario puede realizar esta operación"),
			@ApiResponse(responseCode = "404", description = "Producto no encontrado"),
			@ApiResponse(responseCode = "401", description = "No autenticado") })
	@PostMapping("/{id}/recogida")
	@PreAuthorize("hasAuthority('USUARIO')")
	ResponseEntity<Void> asignarLugarRecogida(
			@Parameter(description = "Identificador del producto", required = true) @PathVariable String id,
			@Valid @RequestBody NuevoLugarDeRecogidaDTO lugarRecogida) throws Exception;

	@Operation(summary = "Incrementar visualizaciones", description = "Incrementa el contador de visualizaciones de un producto")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Visualizaciones incrementadas exitosamente"),
			@ApiResponse(responseCode = "404", description = "Producto no encontrado") })
	@PatchMapping("/{id}/visualizaciones")
	ResponseEntity<Void> incrementarVisualizaciones(
			@Parameter(description = "Identificador del producto", required = true) @PathVariable String id)
			throws Exception;

	@Operation(summary = "Obtener resumen mensual", description = "Obtiene el resumen mensual de productos de un vendedor")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Resumen obtenido exitosamente", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "400", description = "Parámetros inválidos"),
			@ApiResponse(responseCode = "401", description = "No autenticado") })
	@GetMapping("/historial")
	PagedModel<EntityModel<ProductoResumenMensual>> getResumenMensual(
			@Parameter(description = "Identificador del vendedor", required = true) @RequestParam String idVendedor,
			@Parameter(description = "Mes (1-12)", required = true) @RequestParam int mes,
			@Parameter(description = "Año", required = true) @RequestParam int anyo,
			@ParameterObject Pageable paginacion) throws Exception;

	@Operation(summary = "Obtener productos paginados", description = "Obtiene una lista paginada de productos con su resumen")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Productos obtenidos exitosamente", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "400", description = "Parámetros de paginación inválidos") })
	@GetMapping
	PagedModel<EntityModel<ProductoResumenDTO>> getProductosPaginado(@ParameterObject Pageable paginacion)
			throws Exception;

	@Operation(summary = "Buscar productos", description = "Busca productos por descripción, categoría, estado o precio máximo")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Búsqueda completada exitosamente", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "400", description = "Parámetros de búsqueda inválidos") })
	@GetMapping("/buscar")
	PagedModel<EntityModel<ProductoDTO>> buscarProductos(
			@Parameter(description = "Descripción del producto a buscar", required = false) @RequestParam(required = false) String descripcion,
			@Parameter(description = "ID de la categoría a filtrar", required = false) @RequestParam(required = false) String idCategoria,
			@Parameter(description = "Estado del producto", required = false) @RequestParam(required = false) EstadoProducto estado,
			@Parameter(description = "Precio máximo del producto en euros", required = false) @RequestParam(required = false) Double precioMax,
			@ParameterObject Pageable paginacion) throws Exception;
}
