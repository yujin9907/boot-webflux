package co.book.reactivespringboot.repository;

import java.util.concurrent.Flow.Subscriber;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.stereotype.Component;

import co.book.reactivespringboot.entity.Item;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class TemplateDatabaseLoader {

    @Bean
    CommandLineRunner initialize(ReactiveMongoOperations mongo) {
        return args -> {
            mongo.save(new Item("clock", 19.00));
            mongo.save(new Item("tray", 0.55));

        };
    }
}
