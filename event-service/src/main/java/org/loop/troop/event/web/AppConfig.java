package org.loop.troop.event.web;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppConfig {

	private RabbitMQ rabbitmq;

	@Getter
	public static class RabbitMQ {

		@Setter
		private String bookExchange;

		private String bookQueue;

		private String bookDeadLetterQueue;

		private String createRoutingKey;

		private String updateRoutingKey;

		private String routingKey;

		public void setBookServiceGenericRoutingKey(String routingKey) {
			this.routingKey = routingKey;
		}

		public void setBookServiceDeadLetterQueue(String queue) {
			this.bookDeadLetterQueue = queue;
		}

		public void setBookServiceQueue(String queue) {
			this.bookQueue = queue;
		}

		public void setBookServiceCreateRoutingKey(String createKey) {
			this.createRoutingKey = createKey;
		}

		public void setBookServiceUpdateRoutingKey(String updateKey) {
			this.updateRoutingKey = updateKey;
		}

	}

}
