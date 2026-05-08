package segundum.usuarios.adaptador;

import java.io.IOException;

import com.google.gson.Gson;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import segundum.usuarios.evento.Evento;
import segundum.usuarios.puerto.PublicadorEventos;

public class PublicadorEventosRabbitMQ implements PublicadorEventos {

	public PublicadorEventosRabbitMQ() {
		// Verificamos la conexión al arrancar para detectar problemas pronto
		try {
			String uri = getRabbitMQUri();
			ConnectionFactory factory = new ConnectionFactory();
			factory.setUri(uri);

			Connection connection = factory.newConnection();
			Channel channel = connection.createChannel();

			String exchangeName = "bus";
			boolean durable = true;
			channel.exchangeDeclare(exchangeName, "topic", durable);

			channel.close();
			connection.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void publicarEvento(Evento evento) throws IOException {
		try {
			String uri = getRabbitMQUri();
			ConnectionFactory factory = new ConnectionFactory();
			factory.setUri(uri);

			Connection connection = factory.newConnection();
			Channel channel = connection.createChannel();

			Gson gson = new Gson();
			String mensaje = gson.toJson(evento);

			channel.basicPublish("bus", "bus.usuarios." + evento.getTipo(),
					new AMQP.BasicProperties.Builder().contentType("application/json").build(), mensaje.getBytes());

			channel.close();
			connection.close();
		} catch (Exception e) {
			throw new IOException(e);
		}
	}

	private String getRabbitMQUri() {
		String uri = System.getProperty("RABBITMQ_URI");
		if (uri != null && !uri.isEmpty())
			return uri;
		uri = System.getenv("RABBITMQ_URI");
		if (uri != null && !uri.isEmpty())
			return uri;
		return "amqp://admin:practicas@localhost:5672";
	}
}
