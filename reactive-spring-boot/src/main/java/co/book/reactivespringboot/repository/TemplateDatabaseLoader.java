package co.book.reactivespringboot.repository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoActionOperation;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

import co.book.reactivespringboot.entity.Item;

@Component
public class TemplateDatabaseLoader {
    @Bean
    CommandLineRunner initialize(MongoOperations mongo) {
        return args -> {
            mongo.save(new Item("clock", 19.00));
            mongo.save(new Item("tray", 0.55));
        };
    }
}
