package co.book.reactivespringboot.repository.test;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import co.book.reactivespringboot.entity.Item;

public class BlockingDatabaseLoder {
    CommandLineRunner saveSample(BlockingItemRepository repository) {
        return args -> {
            repository.save(new Item("alarm clock", 19.00));
            repository.save(new Item("tray", 0.50));
        };
    }
}
