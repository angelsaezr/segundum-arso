package segundum.compraventas.rest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import segundum.compraventas.rest.dto.CompraventaDTO;

@Component
public class CompraventaAssembler implements RepresentationModelAssembler<CompraventaDTO, EntityModel<CompraventaDTO>> {

	@Override
	public EntityModel<CompraventaDTO> toModel(CompraventaDTO dto) {
		try {
			return EntityModel.of(dto,
					linkTo(methodOn(CompraventasController.class).getCompraventaById(dto.getId())).withSelfRel());
		} catch (Exception e) {
			return EntityModel.of(dto);
		}
	}
}