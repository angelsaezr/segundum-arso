package segundum.compraventas.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO con los datos del producto en una compraventa")
public class ProductoDTO {
	@Schema(description = "Identificador del vendedor del producto")
	private String idVendedor;
	@Schema(description = "Título del producto")
	private String titulo;
	@Schema(description = "Precio del producto en euros")
	private double precio;
	@Schema(description = "Lugar de recogida del producto")
	private String recogida;
	@Schema(description = "Indica si el producto ya ha sido vendido")
	private boolean vendido;

	public ProductoDTO() {
	}

	public ProductoDTO(String idVendedor, String titulo, double precio, String recogida, boolean vendido) {
		this.idVendedor = idVendedor;
		this.titulo = titulo;
		this.precio = precio;
		this.recogida = recogida;
		this.vendido = vendido;
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

	public boolean isVendido() {
		return vendido;
	}

	public void setVendido(boolean vendido) {
		this.vendido = vendido;
	}
}
