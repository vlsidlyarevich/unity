package com.github.vlsidlyarevich.unity.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.mongeez.Mongeez;
import org.mongeez.MongeezRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoConverter;

import java.io.IOException;

/**
 * Created by vladislav on 10/10/16.
 */
@Configuration
public class InitMongo {

    @Value("${spring.data.mongodb.database}")
    private String dbName;

    @Value("${spring.data.mongodb.host}")
    private String dbHost;

    @Value("${spring.data.mongodb.port}")
    private String dbPort;

    @Bean
    public MongeezRunner mongeezRunner() {
        MongeezRunner mongeez = new MongeezRunner();
        mongeez.setDbName(dbName);
        mongeez.setMongo(new MongoClient(dbHost, Integer.parseInt(dbPort)));
        mongeez.setFile(new ClassPathResource("db/config.xml"));
        return mongeez;
    }

//    @Bean
//    public MongoTemplate mongoTemplate(Mongo mongo, MongoDbFactory mongoDbFactory,
//                                       MongoConverter converter) throws IOException {
//        // make sure that Mongeez runs before Spring Data is initialized
//        runMongeez(mongo);
//
//        return new MongoTemplate(mongoDbFactory, converter);
//    }
//
//    private void runMongeez(Mongo mongo) throws IOException {
//        Mongeez mongeez = new Mongeez();
//        mongeez.setMongo(mongo);
//        mongeez.setDbName(dbName);
//        mongeez.setFile(new ClassPathResource("db/config.xml"));
//        mongeez.process();
//    }
}
