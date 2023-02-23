package co.book.reactivespringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
// @EnableAutoConfiguration(exclude = { MongoAutoConfiguration.class })
// @EnableMongoRepositories(basePackages =
// "co.book.reactivespringboot.repository")
public class ReactiveSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveSpringBootApplication.class, args);
	}

}
