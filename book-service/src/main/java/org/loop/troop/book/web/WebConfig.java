package org.loop.troop.book.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.loop.troop.book.domain.modal.PageDto;
import org.loop.troop.book.domain.modal.PageDtoSuppressTypeMixIn;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * The type Web config.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		for (HttpMessageConverter<?> converter : converters) {
			if (converter instanceof MappingJackson2HttpMessageConverter jacksonConverter) {
				ObjectMapper mapper = jacksonConverter.getObjectMapper().copy();
				mapper.addMixIn(PageDto.class, PageDtoSuppressTypeMixIn.class);
				jacksonConverter.setObjectMapper(mapper);
			}
		}
	}

}
