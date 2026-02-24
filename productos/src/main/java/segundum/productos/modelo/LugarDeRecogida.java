package segundum.productos.modelo;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;

@Embeddable
public class LugarDeRecogida {

	@Lob
	@Column(name = "descripcion_recogida")
	private String descripcion;

	private double longitud;

	private double latitud;

	public LugarDeRecogida() {

	}

	public LugarDeRecogida(String descripcion, double longitud, double latitud) {
		this.descripcion = descripcion;
		this.longitud = longitud;
		this.latitud = latitud;
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

	@Override
	public String toString() {
		return "LugarDeRecogida{" + "descripcion='" + descripcion + '\'' + ", longitud=" + longitud + ", latitud="
				+ latitud + '}';
	}
}
