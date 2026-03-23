package segundum.compraventas.rest;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.Operation;
import segundum.compraventas.rest.dto.NuevaCompraventaDTO;

public interface CompraventasApi {

	@Operation(summary = "Crear compraventa", description = "Crea una nueva compraventa con los datos proporcionados")
	@PostMapping
	ResponseEntity<Void> createCompraventa(@Valid @RequestBody NuevaCompraventaDTO nuevaCompraventaDTO)
			throws Exception;

}