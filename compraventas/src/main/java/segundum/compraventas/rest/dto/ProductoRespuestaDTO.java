package segundum.compraventas.rest.dto;

public class ProductoRespuestaDTO {
	private String id;
	private String titulo;
	private double precio;
	private String idVendedor;
	private String recogida;

	public ProductoRespuestaDTO() {
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

	public String getIdVendedor() {
		return idVendedor;
	}

	public String getRecogida() {
		return recogida;
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

	public void setIdVendedor(String idVendedor) {
		this.idVendedor = idVendedor;
	}

	public void setRecogida(String recogida) {
		this.recogida = recogida;
	}
}