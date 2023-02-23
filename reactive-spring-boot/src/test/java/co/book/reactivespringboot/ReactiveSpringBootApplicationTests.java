package co.book.reactivespringboot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = "spring.mongodb.embedded.version=3.5.5")
class ReactiveSpringBootApplicationTests {

	@Test
	void contextLoads() {
	}

}
