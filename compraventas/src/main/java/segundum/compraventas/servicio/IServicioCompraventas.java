package segundum.compraventas.servicio;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import segundum.compraventas.modelo.Compraventa;
import segundum.compraventas.rest.dto.CompraventaDTO;

public interface IServicioCompraventas {
	Compraventa compraventa(String idProducto, String idComprador) throws Exception;

	List<Compraventa> recuperarComprasUsuario(String idComprador);

	List<Compraventa> recuperarVentasUsuario(String idVendedor);

	List<Compraventa> recuperarCompraventasEntreUsuarios(String idComprador, String idVendedor);

	Page<CompraventaDTO> getListadoPaginado(Pageable pageable);
}