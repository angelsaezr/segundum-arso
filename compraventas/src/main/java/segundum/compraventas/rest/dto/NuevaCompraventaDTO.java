package segundum.compraventas.rest.dto;

import javax.validation.constraints.NotNull;

public class NuevaCompraventaDTO {

	@NotNull
	private String idProducto;

	@NotNull
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