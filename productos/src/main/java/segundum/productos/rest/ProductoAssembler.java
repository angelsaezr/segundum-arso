package segundum.productos.rest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import segundum.productos.rest.dto.ProductoDTO;

@Component
public class ProductoAssembler implements RepresentationModelAssembler<ProductoDTO, EntityModel<ProductoDTO>> {

	@Override
	public EntityModel<ProductoDTO> toModel(ProductoDTO producto) {
		try {

			EntityModel<ProductoDTO> resultado = EntityModel.of(producto,
					linkTo(methodOn(ProductosController.class).getProductoById(producto.getId())).withSelfRel());
			return resultado;
		} catch (Exception e) {
			return EntityModel.of(producto);
		}
	}
}