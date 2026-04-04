package segundum.usuarios.puerto;

import java.io.IOException;

import segundum.usuarios.evento.Evento;

public interface PublicadorEventos {
	void publicarEvento(Evento evento) throws IOException;
}