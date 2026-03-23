package segundum.compraventas.rest;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import segundum.compraventas.rest.dto.NuevaCompraventaDTO;

@RestController
@RequestMapping("/compraventas")
public class CompraventasController implements CompraventasApi {

	public CompraventasController() {
	}

	@Override
	public ResponseEntity<Void> createCompraventa(@Valid NuevaCompraventaDTO nuevaCompraventaDTO) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Class<?> getCompraventaById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}