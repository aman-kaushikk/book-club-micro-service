package org.loop.troop.book.web;

import org.loop.troop.book.domain.enums.Vendor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

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

}
