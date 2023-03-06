package co.book.reactivespringboot.entity;

import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
public class ItemTest {
    @Test
    public void itemEntityTest() {
        Item sample = Item.builder()
                .id("testId")
                .name("test")
                .price(14.55)
                .build();

        // assert j 활용
        // assert 가 안 됨

    }
}
