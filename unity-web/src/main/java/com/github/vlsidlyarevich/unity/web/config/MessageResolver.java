package com.github.vlsidlyarevich.unity.web.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageResolver {

    private final MessageSource messageSource;

    @Autowired
    public MessageResolver(final MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(final String msg) {
        return getMessage(msg, new Object[]{});
    }

    public String getMessage(final String msg, final Object... args) {
        return messageSource.getMessage(escapeMsg(msg), args, msg, LocaleContextHolder.getLocale());
    }

    public String getMessage(final String msg, final Object[] args,
                             final String defaultMsg) {
        return messageSource.getMessage(escapeMsg(msg), args, defaultMsg,
                LocaleContextHolder.getLocale());
    }

    private String escapeMsg(final String msg) {
        if (StringUtils.isBlank(msg)) {
            return "";
        }
        return msg.replace(" ", "_");
    }
}
