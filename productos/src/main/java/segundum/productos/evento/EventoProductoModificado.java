package segundum.productos.evento;

public class EventoProductoModificado extends Evento {

	public static final String TIPO = "producto-modificado";

	private final double precio;
	private final String descripcion;

	public EventoProductoModificado(String idProducto, double precio, String descripcion) {
		super(idProducto, TIPO);
		this.precio = precio;
		this.descripcion = descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public String getDescripcion() {
		return descripcion;
	}
}