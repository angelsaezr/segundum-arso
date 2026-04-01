package segundum.compraventas.adaptador;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import segundum.compraventas.config.RabbitMQConfig;
import segundum.compraventas.evento.Evento;
import segundum.compraventas.puerto.PublicadorEventos;

@Component
public class PublicadorEventosRabbitMQ implements PublicadorEventos {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Override
	public void publicarEvento(Evento evento) {
		rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY + evento.getTipo(),
				evento);

	}

}
