package segundum.productos.rest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import segundum.productos.rest.dto.ProductoResumenDTO;

@Component
public class ProductoResumenAssembler
		implements RepresentationModelAssembler<ProductoResumenDTO, EntityModel<ProductoResumenDTO>> {

	@Override
	public EntityModel<ProductoResumenDTO> toModel(ProductoResumenDTO productoResumen) {
		try {

			EntityModel<ProductoResumenDTO> resultado = EntityModel.of(productoResumen,
					linkTo(methodOn(ProductosController.class).getProductoById(productoResumen.getIdProducto()))
							.withSelfRel());
			return resultado;
		} catch (Exception e) {
			return EntityModel.of(productoResumen);
		}
	}
}