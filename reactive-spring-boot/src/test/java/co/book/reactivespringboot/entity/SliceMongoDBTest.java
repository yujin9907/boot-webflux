package co.book.reactivespringboot.entity;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;

import co.book.reactivespringboot.entity.Item;
import co.book.reactivespringboot.repository.ItemRepository;
import reactor.test.StepVerifier;

@ActiveProfiles("test")
@DataMongoTest
public class SliceMongoDBTest {

    @Autowired
    ItemRepository itemRepository;

    @Test
    public void 아이템저장테스트() {
        Item item = Item.builder().name("name").description("description").price(19.00).build();

        itemRepository.save(item)
                .as(StepVerifier::create)
                .expectNextMatches(i -> {
                    assertThat(i.getId()).isNotNull();
                    assertThat(i.getName()).isEqualTo("name");
                    assertThat(i.getDescription()).isEqualTo("description");
                    assertThat(i.getPrice()).isEqualTo(19.00);

                    return true;
                }).verifyComplete();
    }
}
