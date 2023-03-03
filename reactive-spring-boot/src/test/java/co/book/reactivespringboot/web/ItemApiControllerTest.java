package co.book.reactivespringboot.web;

import static org.mockito.Mockito.when;

import javax.swing.text.Document;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import co.book.reactivespringboot.entity.Item;
import co.book.reactivespringboot.repository.ItemRepository;
import co.book.reactivespringboot.service.InventoryService;
import reactor.core.publisher.Flux;

@WebFluxTest(controllers = ItemApiController.class) // 필요한 내용만 자동설정하도록(itemapicontroller 만 집중적으로 테스트한다)
@AutoConfigureRestDocs // 스프링 레스트 독 사용에 피룡한 내용을 자동으로 설정함
public class ItemApiControllerTest {

    @Autowired
    private WebTestClient webTestClient; // 웹플럭스 컨트룰러 호출
    @MockBean
    InventoryService inventoryService;
    @MockBean
    ItemRepository itemRepository;

    @Test
    public void 아이템찾기() {
        when(itemRepository.findAll()).thenReturn( // mockito가 제공하는 when일 때 return 을 반환
            Flux.just(new Item("item-test", "TEST", 19.99))
        );

        webTestClient.get().uri("/api/items") // 협력 개체는 mockbean과 mockito에 의해 미리 정해진 값을 반환함
            .exchange()
            .expectStatus().isOk() // 실행 ok 인지 검증
            .expectBody() // 응답 본문을 테스트 할 수 있으나 여기선 안 함
            .consumeWith(document("findAll", preprocessResponse(prettyPrint()))); // 설정에 추가한 디렉토리에 문서생성기능
    }

}
