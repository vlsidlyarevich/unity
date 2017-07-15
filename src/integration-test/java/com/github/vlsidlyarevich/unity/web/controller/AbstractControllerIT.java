package com.github.vlsidlyarevich.unity.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.vlsidlyarevich.unity.db.domain.User;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

import static com.github.vlsidlyarevich.unity.TestUtils.createUser;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

abstract class AbstractControllerIT {

    protected final MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    protected User user;

    protected String token;

    protected MockMvc mvc;

    protected ObjectMapper objectMapper;

    protected void init(final WebApplicationContext context) {
        user = createUser();
        objectMapper = new ObjectMapper();
        this.mvc = webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }
}
