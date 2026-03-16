package segundum.compraventas.rest.dto;

public class ProductoDTO {
	private String idVendedor;
	private String titulo;
	private double precio;
	private String recogida;

	public ProductoDTO() {
	}

	public ProductoDTO(String idVendedor, String titulo, double precio, String recogida) {
		this.idVendedor = idVendedor;
		this.titulo = titulo;
		this.precio = precio;
		this.recogida = recogida;
	}

	public String getIdVendedor() {
		return idVendedor;
	}

	public String getTitulo() {
		return titulo;
	}

	public double getPrecio() {
		return precio;
	}

	public String getRecogida() {
		return recogida;
	}

	public void setIdVendedor(String idVendedor) {
		this.idVendedor = idVendedor;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public void setRecogida(String recogida) {
		this.recogida = recogida;
	}
}
