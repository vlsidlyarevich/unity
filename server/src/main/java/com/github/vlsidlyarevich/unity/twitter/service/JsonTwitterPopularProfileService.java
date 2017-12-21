package com.github.vlsidlyarevich.unity.twitter.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.vlsidlyarevich.unity.twitter.config.FileConfig;
import com.github.vlsidlyarevich.unity.twitter.config.TwitterProperties;
import com.github.vlsidlyarevich.unity.twitter.model.TwitterPopularProfile;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
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
        final ClassPathResource jsonFileClasspathResource
                = new ClassPathResource(Paths.get(filePath, fileName).toString());

        final byte[] jsonContent = getJsonContentOfResource(jsonFileClasspathResource);

        return Arrays.asList(objectMapper.readValue(jsonContent, TwitterPopularProfile[].class));
    }

    private byte[] getJsonContentOfResource(final ClassPathResource resource) {
        try (InputStream inputStream = resource.getInputStream()) {
            return StreamUtils.copyToByteArray(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
