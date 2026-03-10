package segundum.productos.rest.dto;

import javax.validation.constraints.NotNull;

public class NuevoLugarDeRecogidaDto {

	@NotNull
	private String descripcion;
	private double longitud;
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
