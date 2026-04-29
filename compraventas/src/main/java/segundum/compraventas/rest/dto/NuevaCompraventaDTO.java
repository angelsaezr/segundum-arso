package segundum.compraventas.rest.dto;

import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO de entrada para crear una nueva compraventa")
public class NuevaCompraventaDTO {

	@NotNull
	@Schema(description = "Identificador del producto a comprar")
	private String idProducto;

	@NotNull
	@Schema(description = "Identificador del usuario comprador")
	private String idComprador;

	public NuevaCompraventaDTO() {

	}

	public String getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(String idProducto) {
		this.idProducto = idProducto;
	}

	public String getIdComprador() {
		return idComprador;
	}

	public void setIdComprador(String idComprador) {
		this.idComprador = idComprador;
	}
}