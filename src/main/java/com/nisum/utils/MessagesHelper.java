package com.nisum.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * Utility class for retrieving localized messages from resource bundles.
 * This class provides a method to fetch messages by their key, allowing
 * for internationalization and easier management of application messages.
 *
 * @author avasquez
 */
@Component
public class MessagesHelper {

    @Autowired
    private MessageSource messageSource;

    /**
     * Retrieves a message corresponding to the given key.
     *
     * @param key the key for the desired message
     * @return the localized message as a String
     */
    public String getMessage(String key) {
        return messageSource.getMessage(key, null, Locale.getDefault());
    }
}
