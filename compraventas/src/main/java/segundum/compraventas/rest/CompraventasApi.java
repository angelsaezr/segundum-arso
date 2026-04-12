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
import segundum.compraventas.rest.dto.CompraventaDTO;
import segundum.compraventas.rest.dto.NuevaCompraventaDTO;

public interface CompraventasApi {

	@Operation(summary = "Crear compraventa", description = "Registra la compra de un producto por un usuario")
	@PostMapping
	@PreAuthorize("hasAuthority('USER')")
	ResponseEntity<Void> createCompraventa(@Valid @RequestBody NuevaCompraventaDTO nuevaCompraventaDTO)
			throws Exception;

	@Operation(summary = "Obtener compraventa", description = "Obtiene una compraventa por su id")
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('USER')")
	EntityModel<CompraventaDTO> getCompraventaById(@PathVariable String id) throws Exception;

	@Operation(summary = "Listar compraventas paginadas", description = "Obtiene un listado paginado de todas las compraventas")
	@GetMapping
	@PreAuthorize("hasAuthority('USER')")
	PagedModel<EntityModel<CompraventaDTO>> getCompraventasPaginado(@ParameterObject Pageable pageable)
			throws Exception;

	@Operation(summary = "Compras de un usuario", description = "Obtiene las compras realizadas por un usuario")
	@GetMapping("/compras")
	@PreAuthorize("hasAuthority('USER')")
	ResponseEntity<?> getComprasUsuario(@RequestParam String idComprador) throws Exception;

	@Operation(summary = "Ventas de un usuario", description = "Obtiene las ventas realizadas por un usuario")
	@GetMapping("/ventas")
	@PreAuthorize("hasAuthority('USER')")
	ResponseEntity<?> getVentasUsuario(@RequestParam String idVendedor) throws Exception;

	@Operation(summary = "Compraventas entre dos usuarios", description = "Obtiene las compraventas entre un comprador y un vendedor")
	@GetMapping("/entre")
	@PreAuthorize("hasAuthority('ADMIN')")
	ResponseEntity<?> getCompraventasEntreUsuarios(@RequestParam String idComprador, @RequestParam String idVendedor)
			throws Exception;
}