package org.loop.troop.event.web;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * The type Message resolver.
 */
@Component
@RequiredArgsConstructor
public class MessageResolver {

	private static MessageSource messageSource;

	/**
	 * Instantiates a new Message resolver.
	 * @param messageSource the message source
	 */
	@Autowired
	public MessageResolver(MessageSource messageSource) {
		MessageResolver.messageSource = messageSource;
	}

	/**
	 * Gets message.
	 * @param key the key
	 * @param args the args
	 * @param locale the locale
	 * @return the message
	 */
	public static String getMessage(String key, Object[] args, Locale locale) {
		try {
			return messageSource.getMessage(key, args, locale);
		}
		catch (NoSuchMessageException ex) {
			return key;
		}
	}

	/**
	 * Gets message.
	 * @param key the key
	 * @param args the args
	 * @return the message
	 */
	public static String getMessage(String key, Object[] args) {
		return getMessage(key, args, LocaleContextHolder.getLocale());
	}

}