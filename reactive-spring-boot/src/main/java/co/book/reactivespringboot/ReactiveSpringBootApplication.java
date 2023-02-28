package co.book.reactivespringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.thymeleaf.TemplateEngine;

import reactor.blockhound.BlockHound;

@SpringBootApplication
// @EnableAutoConfiguration(exclude = { MongoAutoConfiguration.class })
// @EnableMongoRepositories(basePackages =
// "co.book.reactivespringboot.repository")
public class ReactiveSpringBootApplication {

	public static void main(String[] args) {
		// BlockHound.install(); 블록하운드 기본 설정
		// 블록하운드 탬플릿 허용

		BlockHound.builder()
				.allowBlockingCallsInside(TemplateEngine.class.getCanonicalName(),
						"process")
				.install();

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
