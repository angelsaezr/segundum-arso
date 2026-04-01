package segundum.compraventas.evento;

public class EventoCompraventaCreada extends Evento {

	public static final String TIPO = "compraventa-creada";

	private final String idProducto;
	private final String idComprador;
	private final String idVendedor;

	public EventoCompraventaCreada(String idCompraventa, String idProducto, String idComprador, String idVendedor) {

		super(idCompraventa, TIPO);
		this.idProducto = idProducto;
		this.idComprador = idComprador;
		this.idVendedor = idVendedor;
	}

	public String getIdProducto() {
		return idProducto;
	}

	public String getIdComprador() {
		return idComprador;
	}

	public String getIdVendedor() {
		return idVendedor;
	}
}