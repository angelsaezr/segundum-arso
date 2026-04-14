package segundum.productos.rest;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import io.swagger.v3.oas.annotations.Operation;
import segundum.productos.rest.dto.CategoriaDTO;

public interface CategoriasApi {

	@Operation(summary = "Obtener categorías raíz", description = "Obtiene una lista paginada de categorías raíz")
	@GetMapping
	PagedModel<EntityModel<CategoriaDTO>> getCategoriasRaiz(@ParameterObject Pageable paginacion) throws Exception;

	@Operation(summary = "Obtener descendientes de una categoría", description = "Obtiene una lista paginada de descendientes de una categoría dada su id")
	@GetMapping("/{id}/descendientes")
	PagedModel<EntityModel<CategoriaDTO>> getDescendientes(@PathVariable String id,
			@ParameterObject Pageable paginacion) throws Exception;
}
