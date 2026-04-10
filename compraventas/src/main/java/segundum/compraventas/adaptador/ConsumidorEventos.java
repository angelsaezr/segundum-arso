package segundum.compraventas.adaptador;

import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import segundum.compraventas.config.RabbitMQConfig;
import segundum.compraventas.puerto.ManejadorEventos;

@Component
public class ConsumidorEventos {

	@Autowired
	private ManejadorEventos manejadorEventos;

	@RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
	public void handleEvent(Map<String, String> mensaje) {

		System.out.println("Mensaje recibido: " + mensaje);

		switch (mensaje.get("tipo")) {
		case "usuario-modificado":
			this.manejadorEventos.usuarioModificado(mensaje.get("id"), mensaje.get("nombre"));
			break;
		default:
			System.out.println("Tipo de evento desconocido: " + mensaje.get("tipo"));
		}

	}
}
