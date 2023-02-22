package co.book.reactivespringboot.repository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import co.book.reactivespringboot.entity.Item;

// 사용 x :  시작시 로딩 문제를 해결하기 위한 방안 (1)

@Component
public class RepositoryDatabaseLoder {

    @Bean
    CommandLineRunner initialize(BlockingItemRepository repository) {
        return args -> {
            repository.save(new Item("alarm clock", 19.00));
            repository.save(new Item("tray", 0.50));
        };
    }
}
