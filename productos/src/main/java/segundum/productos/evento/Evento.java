package segundum.productos.evento;

import java.time.LocalDateTime;

public abstract class Evento {

	private String timestamp;
	private String id;
	private String tipo;

	public Evento(String id, String tipo) {
		this.timestamp = LocalDateTime.now().toString();
		this.id = id;
		this.tipo = tipo;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public String getId() {
		return id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTimestamp(String t) {
		this.timestamp = t;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}