package segundum.compraventas.adaptador;

import java.io.IOException;

import com.google.gson.Gson;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import segundum.compraventas.evento.Evento;
import segundum.compraventas.puerto.PublicadorEventos;

public class PublicadorEventosRabbitMQ implements PublicadorEventos {

	public PublicadorEventosRabbitMQ() {

		try {
			String uri = "amqps://mjxnthmp:pq0QmuFwei2ZNuHoSh2F8dtuQZxZl6co@rat.rmq2.cloudamqp.com/mjxnthmp";

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
			String uri = "amqps://mjxnthmp:pq0QmuFwei2ZNuHoSh2F8dtuQZxZl6co@rat.rmq2.cloudamqp.com/mjxnthmp";

			ConnectionFactory factory = new ConnectionFactory();
			factory.setUri(uri);

			Connection connection = factory.newConnection();
			Channel channel = connection.createChannel();

			Gson gson = new Gson();

			String mensaje = gson.toJson(evento);

			channel.basicPublish("bus", "bus.compraventas." + evento.getTipo(),
					new AMQP.BasicProperties.Builder().contentType("application/json").build(), mensaje.getBytes());

			channel.close();
			connection.close();
		} catch (Exception e) {
			throw new IOException(e);
		}

	}

}
