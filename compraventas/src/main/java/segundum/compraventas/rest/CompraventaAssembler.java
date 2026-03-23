package segundum.compraventas.rest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import segundum.compraventas.modelo.Compraventa;

@Component
public class CompraventaAssembler implements RepresentationModelAssembler<Compraventa, EntityModel<Compraventa>> {

	@Override
	public EntityModel<Compraventa> toModel(Compraventa compraventa) {
		try {

			EntityModel<Compraventa> resultado = EntityModel.of(compraventa,
					linkTo(methodOn(CompraventasController.class).getCompraventaById(compraventa.getId()))
							.withSelfRel());
			return resultado;
		} catch (Exception e) {
			return EntityModel.of(compraventa);
		}
	}

}
