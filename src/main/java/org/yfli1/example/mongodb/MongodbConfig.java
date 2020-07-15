package org.yfli1.example.mongodb;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import java.util.concurrent.TimeUnit;

import static java.util.Collections.singletonList;


@Configuration
public class MongodbConfig {
    @Autowired
    private MongodbProperties mongodbProperties;

    @Bean
    public MongoClient mongoClient() {

        MongoClientSettings settings = MongoClientSettings.builder()
                .credential(MongoCredential.createCredential(mongodbProperties.getUsername(), mongodbProperties.getDatabase(), mongodbProperties.getPassword().toCharArray()))
                .applyToClusterSettings(clusterSettings -> {
                    clusterSettings.hosts(singletonList(new ServerAddress(mongodbProperties.getAddress(), mongodbProperties.getPort())));
                    clusterSettings.serverSelectionTimeout(mongodbProperties.getServerSelectionTimeout(), TimeUnit.MILLISECONDS);
                })
                .applyToConnectionPoolSettings(poolSettings -> {
                    poolSettings.minSize(mongodbProperties.getMinSize());
                    poolSettings.maxSize(mongodbProperties.getMaxSize());
                    poolSettings.maxWaitTime(mongodbProperties.getMaxWaitTime(), TimeUnit.MICROSECONDS);
                    poolSettings.maxConnectionIdleTime(mongodbProperties.getMaxConnectionIdleTime(), TimeUnit.MICROSECONDS);
                    poolSettings.maxConnectionLifeTime(mongodbProperties.getMaxConnectionLifeTime(), TimeUnit.MICROSECONDS);
                })
                .applyToSocketSettings(socketSetting -> {
                    socketSetting.connectTimeout(mongodbProperties.getConnectTimeout(), TimeUnit.MILLISECONDS);
                    socketSetting.readTimeout(mongodbProperties.getReadTimeout(), TimeUnit.MICROSECONDS);
                })
                .applyToServerSettings(serverSetting -> {
                })
                .applyToSslSettings(sslSetting -> {
                    sslSetting.enabled(mongodbProperties.isSslEnable());
                })
                .build();
        MongoClient mongoClient = MongoClients.create(settings);
        return mongoClient;
    }

    @Bean
    public MongoDatabaseFactory mongoDatabaseFactory() {
        return new SimpleMongoClientDatabaseFactory(mongoClient(), mongodbProperties.getDatabase());
    }

    //    @Autowired
//    private ApplicationContext applicationContext //SERVLET 3
    @Bean(name = "myMongoTemplate")
    public MongoTemplate mongoTemplate() {
        MongoMappingContext mongoMappingContext = new MongoMappingContext();
//        mongoMappingContext.setApplicationContext(applicationContext);

        MappingMongoConverter converter = new MappingMongoConverter(new DefaultDbRefResolver(mongoDatabaseFactory()), mongoMappingContext);
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        return new MongoTemplate(mongoDatabaseFactory(), converter);
    }
}


