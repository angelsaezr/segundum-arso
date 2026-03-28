package segundum.compraventas.rest;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import segundum.compraventas.modelo.Compraventa;
import segundum.compraventas.rest.dto.CompraventaDTO;
import segundum.compraventas.rest.dto.NuevaCompraventaDTO;
import segundum.compraventas.servicio.ServicioCompraventas;

@RestController
@RequestMapping("/compraventas")
public class CompraventasController implements CompraventasApi {

	private final ServicioCompraventas servicioCompraventas;

	@Autowired
	private PagedResourcesAssembler<CompraventaDTO> pagedResourcesAssembler;

	@Autowired
	private CompraventaAssembler compraventaAssembler;

	@Autowired
	public CompraventasController(ServicioCompraventas servicioCompraventas) {
		this.servicioCompraventas = servicioCompraventas;
	}

	@Override
	public ResponseEntity<Void> createCompraventa(@Valid NuevaCompraventaDTO nuevaCompraventaDTO) throws Exception {
		Compraventa compraventa = servicioCompraventas.compraventa(nuevaCompraventaDTO.getIdProducto(),
				nuevaCompraventaDTO.getIdComprador());

		URI nuevaURL = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(compraventa.getId()).toUri();

		return ResponseEntity.created(nuevaURL).build();
	}

	@Override
	public EntityModel<CompraventaDTO> getCompraventaById(String id) throws Exception {
		CompraventaDTO dto = servicioCompraventas.getCompraventaById(id);

		EntityModel<CompraventaDTO> model = EntityModel.of(dto);
		model.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(CompraventasController.class).getCompraventaById(id)).withSelfRel());
		return model;
	}

	@Override
	public PagedModel<EntityModel<CompraventaDTO>> getCompraventasPaginado(Pageable pageable) throws Exception {
		Page<CompraventaDTO> resultado = servicioCompraventas.getListadoPaginado(pageable);
		return pagedResourcesAssembler.toModel(resultado, compraventaAssembler);
	}

	@Override
	public ResponseEntity<?> getComprasUsuario(String idComprador) throws Exception {
		List<CompraventaDTO> compras = servicioCompraventas.recuperarComprasUsuario(idComprador).stream()
				.map(CompraventaDTO::fromEntity).collect(Collectors.toList());
		return ResponseEntity.ok(compras);
	}

	@Override
	public ResponseEntity<?> getVentasUsuario(String idVendedor) throws Exception {
		List<CompraventaDTO> ventas = servicioCompraventas.recuperarVentasUsuario(idVendedor).stream()
				.map(CompraventaDTO::fromEntity).collect(Collectors.toList());
		return ResponseEntity.ok(ventas);
	}

	@Override
	public ResponseEntity<?> getCompraventasEntreUsuarios(String idComprador, String idVendedor) throws Exception {
		List<CompraventaDTO> resultado = servicioCompraventas
				.recuperarCompraventasEntreUsuarios(idComprador, idVendedor).stream().map(CompraventaDTO::fromEntity)
				.collect(Collectors.toList());
		return ResponseEntity.ok(resultado);
	}
}