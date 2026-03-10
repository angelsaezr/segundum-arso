package segundum.productos.rest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import segundum.productos.servicio.ProductoResumenMensual;

@Component
public class ProductoResumenMensualAssembler
		implements RepresentationModelAssembler<ProductoResumenMensual, EntityModel<ProductoResumenMensual>> {

	@Override
	public EntityModel<ProductoResumenMensual> toModel(ProductoResumenMensual productoResumenMensual) {
		try {

			EntityModel<ProductoResumenMensual> resultado = EntityModel.of(productoResumenMensual,
					linkTo(methodOn(ProductosController.class).getProductoById(productoResumenMensual.getIdProducto()))
							.withSelfRel());
			return resultado;
		} catch (Exception e) {
			return EntityModel.of(productoResumenMensual);
		}
	}
}