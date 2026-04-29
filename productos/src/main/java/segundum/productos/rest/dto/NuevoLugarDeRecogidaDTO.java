package segundum.productos.rest.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO de entrada para asignar un lugar de recogida a un producto")
public class NuevoLugarDeRecogidaDTO {

	@NotNull
	@Schema(description = "Descripción del lugar de recogida")
	private String descripcion;
	@Min(-180)
	@Max(180)
	@Schema(description = "Longitud del lugar de recogida (entre -180 y 180)")
	private double longitud;
	@Min(-90)
	@Max(90)
	@Schema(description = "Latitud del lugar de recogida (entre -90 y 90)")
	private double latitud;

	public NuevoLugarDeRecogidaDTO() {

	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

}
