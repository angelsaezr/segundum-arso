package segundum.usuarios.adaptador;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import segundum.usuarios.puerto.ManejadorEventos;
import segundum.usuarios.repositorio.EntidadNoEncontrada;
import segundum.usuarios.repositorio.RepositorioException;
import segundum.usuarios.servicio.FactoriaServicios;

@WebListener
public class ConsumidorRabbitMQ implements ServletContextListener {

	// El puerto de entrada se obtiene a través de la factoría
	private final ManejadorEventos manejadorEventos = FactoriaServicios.getServicio(ManejadorEventos.class);

	private Connection connection;
	private Channel channel;

	@Override
	public void contextInitialized(ServletContextEvent sce) {

		String uri = getRabbitMQUri();

		try {
			ConnectionFactory factory = new ConnectionFactory();
			factory.setUri(uri);

			connection = factory.newConnection();
			channel = connection.createChannel();

			String exchangeName = "bus";
			boolean durable = true;
			channel.exchangeDeclare(exchangeName, "topic", durable);

			final String queueName = "usuarios";
			final String bindingKey = "bus.compraventas.#";
			durable = true;
			boolean exclusive = false;
			boolean autodelete = false;
			Map<String, Object> properties = null;
			channel.queueDeclare(queueName, durable, exclusive, autodelete, properties);
			channel.queueBind(queueName, exchangeName, bindingKey);

			boolean autoAck = false;
			channel.basicConsume(queueName, autoAck, "usuarios-consumer", new DefaultConsumer(channel) {
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
						byte[] body) throws IOException {

					long deliveryTag = envelope.getDeliveryTag();
					String contenido = new String(body);
					System.out.println(contenido);

					JsonObject objeto = JsonParser.parseString(contenido).getAsJsonObject();

					if (objeto.get("tipo").getAsString().equals("compraventa-creada")) {
						String idComprador = objeto.get("idComprador").getAsString();
						String idVendedor = objeto.get("idVendedor").getAsString();
						try {
							manejadorEventos.compraventaCreada(idComprador, idVendedor);
						} catch (EntidadNoEncontrada e) {
							e.printStackTrace();
						} catch (RepositorioException e) {
							e.printStackTrace();
						}
					}

					channel.basicAck(deliveryTag, false);
				}
			});

			System.out.println("consumidor RabbitMQ esperando en: " + uri);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		try {
			if (this.channel != null)
				this.channel.close();
			if (this.connection != null)
				this.connection.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private String getRabbitMQUri() {
		// Propiedad del sistema (-DRABBITMQ_URI=...)
		String uri = System.getProperty("RABBITMQ_URI");
		if (uri != null && !uri.isEmpty())
			return uri;

		// Variable de entorno del sistema operativo
		uri = System.getenv("RABBITMQ_URI");
		if (uri != null && !uri.isEmpty())
			return uri;

		// Valor por defecto para desarrollo local
		return "amqp://admin:practicas@localhost:5672";
	}
}
