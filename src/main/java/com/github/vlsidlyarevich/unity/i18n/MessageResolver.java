package com.github.vlsidlyarevich.unity.i18n;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MessageResolver {

    private final MessageSource messageSource;

    public String getMessage(final String msg, final Object... args) {
        return messageSource.getMessage(escapeMsg(msg), args, msg, LocaleContextHolder.getLocale());
    }

    private String escapeMsg(final String msg) {
        if (StringUtils.isBlank(msg)) {
            return "";
        }
        return msg.replace(" ", "_");
    }
}
