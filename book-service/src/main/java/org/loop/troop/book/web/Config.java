package org.loop.troop.book.web;

import org.loop.troop.book.domain.service.Vendor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

}
