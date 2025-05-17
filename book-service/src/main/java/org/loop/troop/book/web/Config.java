package org.loop.troop.book.web;

import org.loop.troop.book.domain.BookServiceEventClient;
import org.loop.troop.book.domain.CustomHttpClientException;
import org.loop.troop.book.domain.enums.Vendor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import org.springframework.http.HttpStatusCode;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executor;

/**
 * The type Config.
 */
@Configuration
public class Config {

	/**
	 * Amazon vendor vendor.
	 * @return the vendor
	 */
	@Bean
	@Qualifier("amazonVendor")
	Vendor amazonVendor() {
		return Vendor.AMAZON;
	}

	/**
	 * Task executor executor.
	 * @return the executor
	 */
	@Bean
	public Executor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(3);
		executor.setMaxPoolSize(10);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("async-");
		executor.initialize();
		return executor;
	}

	@Bean
	public BookServiceEventClient createBookServiceClient(RestClient.Builder builder) {
		RestClient restClient = builder.baseUrl("http://localhost:8080")
			.defaultStatusHandler(HttpStatusCode::isError, (request, response) -> {
				try (InputStream bodyStream = response.getBody()) {
					String body = new String(bodyStream.readAllBytes(), StandardCharsets.UTF_8);
					throw new CustomHttpClientException(body, response.getStatusCode());
				}
				catch (IOException e) {
					throw new CustomHttpClientException("Failed to read error response", response.getStatusCode());
				}
			})
			.build();
		HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient))
			.build();
		return factory.createClient(BookServiceEventClient.class);
	}

}
