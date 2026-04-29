package segundum.productos.rest.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO de entrada para modificar un producto existente")
public class ModificadoProductoDTO {
	@NotNull
	@Schema(description = "Nueva descripción del producto")
	private String descripcion;
	@NotNull
	@Min(0)
	@Schema(description = "Nuevo precio del producto en euros")
	private double precio;

	public ModificadoProductoDTO() {

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
