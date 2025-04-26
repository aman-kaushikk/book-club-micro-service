package org.loop.troop.book.web;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Rabbit config.
 */
@Configuration
@RequiredArgsConstructor
public class RabbitConfig {

	private final AppConfig appConfig;

	private AppConfig.RabbitMQ rabbitMQ;

	private static final String DEAD_LETTER_EXCHANGE = "book-service.dlx.exchange";

	private static final String DEAD_LETTER_ROUTING_KEY = "book-service.dlx.routingKey";

	/**
	 * Post construct.
	 */
	@PostConstruct
	public void postConstruct() {
		this.rabbitMQ = appConfig.getRabbitmq();
	}

	/**
	 * Book exchange topic exchange. Define Topic Exchange for book events
	 * @return the topic exchange
	 */
	@Bean
	public TopicExchange bookExchange() {
		return new TopicExchange(rabbitMQ.getBookExchange());
	}

	/**
	 * Book queue queue. Main Queue with Dead Letter Queue (DLQ) configuration
	 * @return the queue
	 */
	@Bean
	public Queue bookQueue() {
		Map<String, Object> args = new HashMap<>();
		// Set the DLX (Dead Letter Exchange) to move failed messages to the dead letter
		// queue
		args.put("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE);
		args.put("x-dead-letter-routing-key", DEAD_LETTER_ROUTING_KEY);

		return QueueBuilder.durable(rabbitMQ.getBookQueue()).withArguments(args).build();
	}

	/**
	 * Dead letter queue queue. Dead Letter Queue (to handle messages after all retry
	 * attempts fail)
	 * @return the queue
	 */
	@Bean
	public Queue deadLetterQueue() {
		return new Queue(rabbitMQ.getBookDeadLetterQueue());
	}

	/**
	 * Dlx exchange topic exchange. Dead Letter Exchange (for messages that cannot be
	 * processed after retries)
	 * @return the topic exchange
	 */
	@Bean
	public TopicExchange dlxExchange() {
		return new TopicExchange(DEAD_LETTER_EXCHANGE);
	}

	/**
	 * Dlx binding binding. Bind DLX Queue to Dead Letter Exchange with Routing Key
	 * @return the binding
	 */
	@Bean
	public Binding dlxBinding() {
		return BindingBuilder.bind(deadLetterQueue()).to(dlxExchange()).with(DEAD_LETTER_ROUTING_KEY);
	}

	/**
	 * //@Bean public Binding bookCreatedBinding() { return
	 * BindingBuilder.bind(bookQueue()).to(bookExchange()).with(rabbitMQ.getCreateRoutingKey());
	 * }
	 *
	 *
	 * //@Bean public Binding bookUpdatedBinding() { return
	 * BindingBuilder.bind(bookQueue()).to(bookExchange()).with(rabbitMQ.getUpdateRoutingKey());
	 * }
	 **/

	@Bean
	public Binding bookBinding() {
		return BindingBuilder.bind(bookQueue()).to(bookExchange()).with(rabbitMQ.getRoutingKey());
	}

}
