package org.loop.troop.book.web;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class MessageResolver {

    private static MessageSource messageSource;

    @Autowired
    public MessageResolver(MessageSource messageSource) {
        MessageResolver.messageSource = messageSource;
    }

    public static String getMessage(String key, Object[] args, Locale locale) {
        try {
            return messageSource.getMessage(key, args, locale);
        } catch (NoSuchMessageException ex) {
            return key;
        }
    }

    public static String getMessage(String key,Object [] args) {
        return getMessage(key, args, LocaleContextHolder.getLocale());
    }
}