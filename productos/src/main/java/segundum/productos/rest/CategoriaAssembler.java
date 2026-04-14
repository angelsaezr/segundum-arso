package segundum.productos.rest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import segundum.productos.rest.dto.CategoriaDTO;

@Component
public class CategoriaAssembler implements RepresentationModelAssembler<CategoriaDTO, EntityModel<CategoriaDTO>> {

	@Override
	public EntityModel<CategoriaDTO> toModel(CategoriaDTO categoria) {
		try {

			EntityModel<CategoriaDTO> resultado = EntityModel.of(categoria,
					linkTo(methodOn(CategoriasController.class).getDescendientes(categoria.getId(), null))
							.withRel("descendientes"));

			// Agregar links a cada subcategoría de forma individual
			if (categoria.getSubcategorias() != null) {
				for (EntityModel<CategoriaDTO> subcategoriaModel : categoria.getSubcategorias()) {
					subcategoriaModel.add(linkTo(methodOn(CategoriasController.class)
							.getDescendientes(subcategoriaModel.getContent().getId(), null)).withRel("descendientes"));
				}
			}
			return resultado;
		} catch (Exception e) {
			return EntityModel.of(categoria);
		}
	}
}
