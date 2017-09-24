package com.github.vlsidlyarevich.unity.twitter.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.vlsidlyarevich.unity.twitter.config.FileConfig;
import com.github.vlsidlyarevich.unity.twitter.config.TwitterProperties;
import com.github.vlsidlyarevich.unity.twitter.model.TwitterPopularProfile;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class JsonTwitterPopularProfileService implements TwitterPopularProfileService {

    private static final String POPULAR_PROFILES = "popularProfiles";

    private final TwitterProperties properties;
    private ObjectMapper objectMapper;

    @PostConstruct
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Override
    public List<TwitterPopularProfile> getPopularProfileList() throws IOException {
        final FileConfig config = properties.getFiles().get(POPULAR_PROFILES);

        return readFile(config.getName(), config.getPath());
    }

    private List<TwitterPopularProfile> readFile(final String fileName, final String filePath) throws IOException {
        final byte[] jsonContent = Files.readAllBytes(Paths.get(filePath, fileName));

        return Arrays.asList(objectMapper.readValue(jsonContent, TwitterPopularProfile[].class));
    }
}
