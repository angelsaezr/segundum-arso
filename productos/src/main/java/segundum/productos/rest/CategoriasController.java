package segundum.productos.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import segundum.productos.rest.dto.CategoriaDTO;
import segundum.productos.servicio.ServicioCategorias;

@RestController
@RequestMapping("/categorias")
public class CategoriasController implements CategoriasApi {

	private ServicioCategorias servicioCategorias;

	@Autowired
	private PagedResourcesAssembler<CategoriaDTO> pagedResourcesAssemblerCategorias;

	@Autowired
	private CategoriaAssembler categoriaAssembler;

	@Autowired
	public CategoriasController(ServicioCategorias servicioCategorias) {
		this.servicioCategorias = servicioCategorias;
	}

	@Override
	public PagedModel<EntityModel<CategoriaDTO>> getCategoriasRaiz(Pageable paginacion) throws Exception {
		Page<CategoriaDTO> resultado = this.servicioCategorias.getCategoriasRaizPaginado(paginacion);
		return this.pagedResourcesAssemblerCategorias.toModel(resultado, categoriaAssembler);
	}

	@Override
	public PagedModel<EntityModel<CategoriaDTO>> getDescendientes(@PathVariable String id, Pageable paginacion)
			throws Exception {
		Page<CategoriaDTO> resultado = this.servicioCategorias.getDescendientesPaginado(id, paginacion);
		return this.pagedResourcesAssemblerCategorias.toModel(resultado, categoriaAssembler);
	}
}