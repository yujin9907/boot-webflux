package co.book.reactivespringboot.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

import com.mongodb.reactivestreams.client.MongoClient;

// @Configuration
public class ConfigMongo2 {

    // @Value("${mongodb.test.connectionString}")
    // private String connectionString;

    // @Bean
    // public MongoDatabaseFactory mongoDatabaseFactory() {
    // return new SimpleMongoClientDatabaseFactory(connectionString);
    // }

    // @Bean
    // public MongoTemplate mongoTemplate() {
    // return new MongoTemplate(mongoDatabaseFactory());
    // }
}
