package com.github.vlsidlyarevich.unity.db;

import com.github.vlsidlyarevich.unity.common.UnityCommon;
import com.github.vlsidlyarevich.unity.db.config.StorageProperties;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

@Import({
        UnityCommon.class
})
@EnableConfigurationProperties(StorageProperties.class)
@SpringBootApplication
public class UnityDatabaseIT {

}
