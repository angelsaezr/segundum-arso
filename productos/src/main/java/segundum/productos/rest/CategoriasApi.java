package segundum.productos.rest;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;
import segundum.productos.rest.dto.CategoriaDTO;

public interface CategoriasApi {

	@Operation(summary = "Obtener categorías raíz", description = "Obtiene una lista paginada de categorías raíz")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Categorías obtenidas exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoriaDTO.class))),
			@ApiResponse(responseCode = "400", description = "Parámetros de paginación inválidos") })
	@GetMapping
	PagedModel<EntityModel<CategoriaDTO>> getCategoriasRaiz(@ParameterObject Pageable paginacion) throws Exception;

	@Operation(summary = "Obtener descendientes de una categoría", description = "Obtiene una lista paginada de descendientes de una categoría dada su id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Descendientes obtenidos exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoriaDTO.class))),
			@ApiResponse(responseCode = "404", description = "Categoría no encontrada"),
			@ApiResponse(responseCode = "400", description = "Parámetros inválidos") })
	@GetMapping("/{id}/descendientes")
	PagedModel<EntityModel<CategoriaDTO>> getDescendientes(
			@Parameter(description = "Identificador de la categoría", required = true) @PathVariable String id,
			@ParameterObject Pageable paginacion) throws Exception;
}
