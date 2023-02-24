package co.book.reactivespringboot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import co.book.reactivespringboot.entity.Item;

@SpringBootApplication
// @EnableAutoConfiguration(exclude = { MongoAutoConfiguration.class })
// @EnableMongoRepositories(basePackages =
// "co.book.reactivespringboot.repository")
public class ReactiveSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveSpringBootApplication.class, args);
	}

	// @Bean
	// CommandLineRunner initializeTEST(ReactiveMongoOperations mongo) {
	// return args -> {
	// System.out.println("커맨드라인 실행됨");

	// mongo.save(new Item("clock", 19.00));
	// mongo.save(new Item("tray", 0.55));

	// };
	// }

}
