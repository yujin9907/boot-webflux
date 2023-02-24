package co.book.reactivespringboot.repository;

import java.util.concurrent.Flow.Subscriber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Component;

import co.book.reactivespringboot.entity.Item;
import lombok.RequiredArgsConstructor;

// @RequiredArgsConstructor
@Component
public class TemplateDatabaseLoader {

    @Autowired
    private ItemRepository itemRepository;

    @Bean
    CommandLineRunner initialize(ReactiveMongoOperations mongo) {
        return args -> {
            System.out.println("실행됨");
            mongo.insert(Item.builder().name("쿠버네티스 / 도커").price(34000).build());
            mongo.save(new Item("tray", 0.55));
            itemRepository.save(Item.builder().name("쿠버네티스 / 도커").price(34000).build());

        };
    }
}
