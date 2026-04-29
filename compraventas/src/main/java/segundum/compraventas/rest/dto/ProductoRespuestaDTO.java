package segundum.compraventas.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO de respuesta con los datos completos del producto")
public class ProductoRespuestaDTO {
	@Schema(description = "Identificador único del producto")
	private String id;
	@Schema(description = "Título del producto")
	private String titulo;
	@Schema(description = "Precio del producto en euros")
	private double precio;
	@Schema(description = "Identificador del vendedor del producto")
	private String idVendedor;
	@Schema(description = "Lugar de recogida del producto")
	private String recogida;
	@Schema(description = "Indica si el producto ya ha sido vendido")
	private boolean vendido;

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

	public boolean isVendido() {
		return vendido;
	}

	public void setVendido(boolean vendido) {
		this.vendido = vendido;
	}
}