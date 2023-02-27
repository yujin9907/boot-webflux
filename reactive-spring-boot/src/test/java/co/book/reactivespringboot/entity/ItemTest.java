package co.book.reactivespringboot.entity;

import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;

public class ItemTest {
    @Test
    public void 아이템테스트() {
        Item sample = Item.builder()
                .id("testId")
                .name("test")
                .price(14.55)
                .build();

        // assert j 활용
        // assert 가 안 됨

    }
}
