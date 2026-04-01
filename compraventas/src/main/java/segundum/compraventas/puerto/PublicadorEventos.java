package segundum.compraventas.puerto;

import java.io.IOException;

import segundum.compraventas.evento.Evento;

public interface PublicadorEventos {

	void publicarEvento(Evento evento) throws IOException;
}
