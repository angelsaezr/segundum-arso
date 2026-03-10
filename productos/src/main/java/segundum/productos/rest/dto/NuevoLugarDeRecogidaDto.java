package segundum.productos.rest.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class NuevoLugarDeRecogidaDto {

	@NotNull
	private String descripcion;
	// Ambas deben estar entre -90 y 90 para latitud, y entre -180 y 180 para
	// longitud. Ponerlo con anotaciones de validación si es posible, o al menos
	// documentarlo claramente.
	@Min(-180)
	@Max(180)
	private double longitud;
	@Min(-90)
	@Max(90)
	private double latitud;

	public NuevoLugarDeRecogidaDto() {

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
