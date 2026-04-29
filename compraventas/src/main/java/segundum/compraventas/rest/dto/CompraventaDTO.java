package segundum.compraventas.rest.dto;

import java.time.LocalDateTime;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.v3.oas.annotations.media.Schema;
import segundum.compraventas.modelo.Compraventa;

@Schema(description = "DTO de respuesta con los datos completos de una compraventa")
public class CompraventaDTO extends RepresentationModel<CompraventaDTO> {

	@Schema(description = "Identificador único de la compraventa")
	private String id;

	@Schema(description = "Identificador del producto vendido")
	private String idProducto;

	@Schema(description = "Título del producto")
	private String titulo;

	@Schema(description = "Precio de la compraventa en euros")
	private double precio;

	@Schema(description = "Método o lugar de recogida del producto")
	private String recogida;

	@Schema(description = "Identificador del vendedor")
	private String idVendedor;

	@Schema(description = "Nombre del vendedor")
	private String nombreVendedor;

	@Schema(description = "Identificador del comprador")
	private String idComprador;

	@Schema(description = "Nombre del comprador")
	private String nombreComprador;

	@Schema(description = "Fecha en la que se realizó la compraventa")
	private LocalDateTime fecha;

	public CompraventaDTO() {

	}

	public CompraventaDTO(String id, String idProducto, String titulo, double precio, String recogida,
			String idVendedor, String nombreVendedor, String idComprador, String nombreComprador, LocalDateTime fecha) {
		this.id = id;
		this.idProducto = idProducto;
		this.titulo = titulo;
		this.precio = precio;
		this.recogida = recogida;
		this.idVendedor = idVendedor;
		this.nombreVendedor = nombreVendedor;
		this.idComprador = idComprador;
		this.nombreComprador = nombreComprador;
		this.fecha = fecha;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(String idProducto) {
		this.idProducto = idProducto;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getRecogida() {
		return recogida;
	}

	public void setRecogida(String recogida) {
		this.recogida = recogida;
	}

	public String getIdVendedor() {
		return idVendedor;
	}

	public void setIdVendedor(String idVendedor) {
		this.idVendedor = idVendedor;
	}

	public String getNombreVendedor() {
		return nombreVendedor;
	}

	public void setNombreVendedor(String nombreVendedor) {
		this.nombreVendedor = nombreVendedor;
	}

	public String getIdComprador() {
		return idComprador;
	}

	public void setIdComprador(String idComprador) {
		this.idComprador = idComprador;
	}

	public String getNombreComprador() {
		return nombreComprador;
	}

	public void setNombreComprador(String nombreComprador) {
		this.nombreComprador = nombreComprador;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public static CompraventaDTO fromEntity(Compraventa compraventa) {
		return new CompraventaDTO(compraventa.getId(), compraventa.getIdProducto(), compraventa.getTitulo(),
				compraventa.getPrecio(), compraventa.getRecogida(), compraventa.getIdVendedor(),
				compraventa.getNombreVendedor(), compraventa.getIdComprador(), compraventa.getNombreComprador(),
				compraventa.getFecha());
	}
}
