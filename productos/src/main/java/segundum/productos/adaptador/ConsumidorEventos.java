package segundum.productos.adaptador;

import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import segundum.productos.config.RabbitMQConfig;
import segundum.productos.puerto.ManejadorEventos;

@Component
public class ConsumidorEventos {

	@Autowired
	private ManejadorEventos manejadorEventos;

	@RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
	public void handleEvent(Map<String, String> mensaje) {

		System.out.println("Mensaje recibido: " + mensaje);

		switch (mensaje.get("tipo")) {
		case "compraventa-creada":
			this.manejadorEventos.compraventaCreada(mensaje.get("idProducto"));
			break;
		case "usuario-creado":
			this.manejadorEventos.usuarioCreado(mensaje.get("id"), mensaje.get("email"), mensaje.get("nombre"),
					mensaje.get("apellidos"));
			break;
		default:
			System.out.println("Tipo de evento desconocido: " + mensaje.get("tipo"));
		}

	}
}
