package segundum.productos.puerto;

import segundum.productos.evento.Evento;

public interface PublicadorEventos {
	void publicarEvento(Evento evento);
}