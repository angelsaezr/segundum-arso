package segundum.productos.rest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import segundum.productos.rest.dto.ProductoResumen;

@Component
public class ProductoResumenAssembler
		implements RepresentationModelAssembler<ProductoResumen, EntityModel<ProductoResumen>> {

	@Override
	public EntityModel<ProductoResumen> toModel(ProductoResumen productoResumen) {
		try {

			EntityModel<ProductoResumen> resultado = EntityModel.of(productoResumen,
					linkTo(methodOn(ProductosController.class).getProductoById(productoResumen.getIdProducto()))
							.withSelfRel());
			return resultado;
		} catch (Exception e) {
			return EntityModel.of(productoResumen);
		}
	}
}