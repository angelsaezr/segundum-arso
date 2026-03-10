package segundum.productos.rest.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ModificadoProductoDto {
	@NotNull
	private String descripcion;
	@NotNull
	@Min(0)
	private double precio;

	public ModificadoProductoDto() {

	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

}
