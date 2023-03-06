package co.book.reactivespringboot.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when; // 
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*; // preprocessResponse
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.*; // document 
// import static javax.swing.text.Document.*;

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
import reactor.core.publisher.Mono;

@WebFluxTest(controllers = ItemApiController.class) // 필요한 내용만 자동설정하도록(itemapicontroller 만 집중적으로 테스트한다)
@AutoConfigureRestDocs // 스프링 레스트 독 사용에 피룡한 내용을 자동으로 설정함
public class ItemApiControllerTest {

    @Autowired
    private WebTestClient webTestClient; // 웹플럭스 컨트룰러 호출
    @MockBean
    InventoryService inventoryService;
    @MockBean
    ItemRepository itemRepository;

    // ./mvnw clean prepare-package 실행 => 테스트 실행 + api 문서 생성

    @Test
    public void apiFindItem() {
        when(itemRepository.findAll()).thenReturn( // mockito가 제공하는 when일 때 return 을 반환
            Flux.just(new Item("item-test", "TEST", 19.99))
        );

        webTestClient.get().uri("/api/items") // 협력 개체는 mockbean과 mockito에 의해 미리 정해진 값을 반환함
            .exchange()
            .expectStatus().isOk() // 실행 ok 인지 검증
            .expectBody() // 응답 본문을 테스트 할 수 있으나 여기선 안 함
            .consumeWith(document("findAll", preprocessResponse(prettyPrint()))); // "findall" 디렉토리 안에 .adoc 파일 생성, pre~ 요청 결과로 반환되는 json 문자열 보기 편하도록 출력
    }

    @Test
    public void apiSaveItem (){
        when(inventoryService.saveItem(any())).thenReturn(
            Mono.just(Item.builder().name("test-doc").price(14.55).build())
        );

        webTestClient.post().uri("/api/item")
            .bodyValue(Item.builder().name("test-doc").price(14.55).build()) // 전달할 body
            .exchange()
            .expectStatus().isCreated() // 201
            .expectBody()
            .consumeWith(document("post-new-item", preprocessResponse(prettyPrint())));
    }

}
