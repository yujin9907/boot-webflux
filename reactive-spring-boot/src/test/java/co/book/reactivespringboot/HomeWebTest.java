package co.book.reactivespringboot;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import co.book.reactivespringboot.entity.Cart;
import co.book.reactivespringboot.entity.Item;
import co.book.reactivespringboot.service.InventoryService;
import co.book.reactivespringboot.web.HomeController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@WebFluxTest(HomeController.class) // homecontroller에 국한된 스프링 웹플럭스 슬라이스 테스트
public class HomeWebTest {

    @Autowired
    private WebTestClient webTestClient; // 이새끼 필요해서 생성자 주입(슬라이스 테스트의 일부)
    @MockBean
    InventoryService inventoryService;

    @Test
    public void homePage() {
        when(inventoryService.getInventory()).thenReturn(Flux.just(
            new Item("id1", "name1", "de1", 1.55),
            new Item("id2", "name2", "de3", 1.99)
        ));
        when(inventoryService.getCart("My cart"))
            .thenReturn(Mono.just(new Cart("My cart")));
        
        webTestClient.get().uri("/").exchange()
        .expectStatus().isOk()
        .expectBody(String.class)
        .consumeWith(result -> {
            assertThat(result.getResponseBody()).contains("action=\"/add/id1\"");
            assertThat(result.getResponseBody()).contains("action=\"/add/id2\"");
        });
    }
}
// <<= 왜 지랄? 줄마다 실행버튼이 뜨는 기기괴괴?
