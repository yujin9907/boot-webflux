package co.book.reactivespringboot;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Answers.RETURNS_SELF;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.ExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class LoadingWebTest {

    @Autowired
    WebTestClient client;

    @Test
    public void test() {
        client.get().uri("/").exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.TEXT_HTML)
                .expectBody(String.class)
                .consumeWith(ExchangeResult -> {
                    assertThat(ExchangeResult.getResponseBody()).contains("<a href=\"/add");
                });
    }

}
