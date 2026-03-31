package segundum.compraventas.evento;

public class EventoCompraventaCreada extends Evento {

	private String nombre;

	public EventoCompraventaCreada(String email, String nombre) {
		super(email, "compraventa-creada");
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
