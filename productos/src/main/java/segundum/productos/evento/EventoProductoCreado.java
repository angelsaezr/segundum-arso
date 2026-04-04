package segundum.productos.evento;

public class EventoProductoCreado extends Evento {

	public static final String TIPO = "producto-creado";

	private final String titulo;
	private final double precio;
	private final String idVendedor;
	private final String idCategoria;

	public EventoProductoCreado(String idProducto, String titulo, double precio, String idVendedor,
			String idCategoria) {
		super(idProducto, TIPO);
		this.titulo = titulo;
		this.precio = precio;
		this.idVendedor = idVendedor;
		this.idCategoria = idCategoria;
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

	public String getIdCategoria() {
		return idCategoria;
	}
}