package co.book.reactivespringboot.service;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.book.reactivespringboot.entity.Cart;
import co.book.reactivespringboot.entity.CartItem;
import co.book.reactivespringboot.entity.Item;
import co.book.reactivespringboot.repository.CartRepository;
import co.book.reactivespringboot.repository.ItemRepository;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class CartServiceTest {

        CartService cartService;
        @MockBean
        private ItemRepository itemRepository;
        @MockBean
        private CartRepository cartRepository;

        @BeforeEach
        void setUp() {
                // 테스트 데이터 정의
                Item sampleItem = Item.builder()
                                .id("iteml")
                                .name("test1")
                                .price(19.99)
                                .build();
                CartItem samplCartItem = CartItem.builder()
                                .item(sampleItem)
                                .quantity(1)
                                .build();
                Cart sapmleCart = Cart.builder()
                                .id("My cart")
                                .cartItems(Collections.singletonList(samplCartItem))
                                .build();
                // 상호작용 정의
                when(cartRepository.findById(anyString())).thenReturn(Mono.empty());
                when(itemRepository.findById(anyString())).thenReturn(Mono.just(sampleItem));
                when(cartRepository.save(any(Cart.class))).thenReturn(Mono.just(sapmleCart));

                cartService = new CartService(itemRepository, cartRepository);
        }

        @Test
        public void AddItemToEmptyCart() {
                cartService.addToCart("My cart", "iteml")
                                .as(StepVerifier::create)
                                .expectNextMatches(cart -> {
                                        assertThat(cart.getCartItems()).extracting(CartItem::getQuantity)
                                                        .containsExactlyInAnyOrder(1); // 1이어야 맞는데 좆됐네
                                        assertThat(cart.getCartItems()).extracting(CartItem::getItem)
                                                        .containsExactly(new Item("iteml", "test1", 19.99));

                                        return true;

                                }).verifyComplete();
        }

}
