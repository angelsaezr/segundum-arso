package segundum.compraventas.rest;

import javax.validation.Valid;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
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
import segundum.compraventas.rest.dto.CompraventaDTO;
import segundum.compraventas.rest.dto.NuevaCompraventaDTO;

public interface CompraventasApi {

	@Operation(summary = "Crear compraventa", description = "Registra la compra de un producto por un usuario")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Compraventa creada exitosamente", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
			@ApiResponse(responseCode = "401", description = "No autenticado"),
			@ApiResponse(responseCode = "409", description = "El producto ya ha sido vendido") })
	@PostMapping
	@PreAuthorize("hasAuthority('USER')")
	ResponseEntity<Void> createCompraventa(@Valid @RequestBody NuevaCompraventaDTO nuevaCompraventaDTO)
			throws Exception;

	@Operation(summary = "Obtener compraventa", description = "Obtiene una compraventa por su id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Compraventa encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CompraventaDTO.class))),
			@ApiResponse(responseCode = "404", description = "Compraventa no encontrada"),
			@ApiResponse(responseCode = "401", description = "No autenticado") })
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('USER')")
	EntityModel<CompraventaDTO> getCompraventaById(
			@Parameter(description = "Identificador de la compraventa", required = true) @PathVariable String id) throws Exception;

	@Operation(summary = "Listar compraventas paginadas", description = "Obtiene un listado paginado de todas las compraventas")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Compraventas obtenidas exitosamente", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "400", description = "Parámetros de paginación inválidos"),
			@ApiResponse(responseCode = "401", description = "No autenticado") })
	@GetMapping
	@PreAuthorize("hasAuthority('USER')")
	PagedModel<EntityModel<CompraventaDTO>> getCompraventasPaginado(@ParameterObject Pageable pageable)
			throws Exception;

	@Operation(summary = "Compras de un usuario", description = "Obtiene las compras realizadas por un usuario")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Compras obtenidas exitosamente", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "400", description = "Identificador de usuario inválido"),
			@ApiResponse(responseCode = "401", description = "No autenticado") })
	@GetMapping("/compras")
	@PreAuthorize("hasAuthority('USER')")
	ResponseEntity<?> getComprasUsuario(
			@Parameter(description = "Identificador del usuario comprador", required = true) @RequestParam String idComprador) throws Exception;

	@Operation(summary = "Ventas de un usuario", description = "Obtiene las ventas realizadas por un usuario")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Ventas obtenidas exitosamente", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "400", description = "Identificador de usuario inválido"),
			@ApiResponse(responseCode = "401", description = "No autenticado") })
	@GetMapping("/ventas")
	@PreAuthorize("hasAuthority('USER')")
	ResponseEntity<?> getVentasUsuario(
			@Parameter(description = "Identificador del usuario vendedor", required = true) @RequestParam String idVendedor) throws Exception;

	@Operation(summary = "Compraventas entre dos usuarios", description = "Obtiene las compraventas entre un comprador y un vendedor")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Compraventas obtenidas exitosamente", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "400", description = "Identificadores de usuario inválidos"),
			@ApiResponse(responseCode = "401", description = "No autenticado"),
			@ApiResponse(responseCode = "403", description = "Solo administradores pueden acceder a esta operación") })
	@GetMapping("/entre")
	@PreAuthorize("hasAuthority('ADMIN')")
	ResponseEntity<?> getCompraventasEntreUsuarios(
			@Parameter(description = "Identificador del usuario comprador", required = true) @RequestParam String idComprador,
			@Parameter(description = "Identificador del usuario vendedor", required = true) @RequestParam String idVendedor)
			throws Exception;
}