package segundum.compraventas.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "compraventas")
public class Compraventa {

	@Id
	private String id;

	// private String idProducto;
	private String titulo;
	private double precio;
	// private String recogida;

	// private String idVendedor;
	// private String nombreVendedor;

	// private String idComprador;
	// private String nombreComprador;

	// private LocalDateTime fecha;

	public Compraventa() {
	}

	public Compraventa(String id, String titulo, double precio) {
		this.id = id;
		this.titulo = titulo;
		this.precio = precio;
	}

	public String getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public double getPrecio() {
		return precio;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
}
