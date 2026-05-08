package segundum.productos.config;

import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class RabbitMQConfig {

	public static final String QUEUE_NAME = "productos";
	public static final String EXCHANGE_NAME = "bus";
	public static final String BINDING_KEY_COMPRAVENTAS = "bus.compraventas.#";
	public static final String BINDING_KEY_USUARIOS = "bus.usuarios.#";
	public static final String ROUTING_KEY = "bus.productos.";

	// Spring Boot lee spring.rabbitmq.host/port/username/password de
	// application.properties, que a su vez los lee de las variables de entorno.
	@Value("${spring.rabbitmq.host:localhost}")
	private String host;

	@Value("${spring.rabbitmq.port:5672}")
	private int port;

	@Value("${spring.rabbitmq.username:admin}")
	private String username;

	@Value("${spring.rabbitmq.password:practicas}")
	private String password;

	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory factory = new CachingConnectionFactory();
		factory.setHost(host);
		factory.setPort(port);
		factory.setUsername(username);
		factory.setPassword(password);
		return factory;
	}

	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(EXCHANGE_NAME);
	}

	@Bean
	public Queue queue() {
		return new Queue(QUEUE_NAME, true, false, false);
	}

	@Bean
	public Binding bindingCompraventas(Queue queue, Exchange exchange) {
		Map<String, Object> props = null;
		return BindingBuilder.bind(queue).to(exchange).with(BINDING_KEY_COMPRAVENTAS).and(props);
	}

	@Bean
	public Binding bindingUsuarios(Queue queue, Exchange exchange) {
		Map<String, Object> props = null;
		return BindingBuilder.bind(queue).to(exchange).with(BINDING_KEY_USUARIOS).and(props);
	}

	@Bean
	public MessageConverter jsonMessageConverter() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		return new Jackson2JsonMessageConverter(objectMapper);
	}

	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter converter) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(converter);
		return rabbitTemplate;
	}
}
