package co.book.reactivespringboot.service;

import org.springframework.stereotype.Service;

import co.book.reactivespringboot.entity.Cart;
import co.book.reactivespringboot.entity.CartItem;
import co.book.reactivespringboot.entity.Item;
import co.book.reactivespringboot.repository.CartRepository;
import co.book.reactivespringboot.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class CartService {

    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;

    public Mono<Cart> addToCart(String cartId, String id) {
        return this.cartRepository.findById(cartId)
                .defaultIfEmpty(new Cart(cartId))
                .flatMap(cart -> cart.getCartItems().stream()
                        .filter(cartItem -> cartItem.getItem()
                                .getId().equals(id))
                        .findAny()
                        .map(cartItem -> {
                            cartItem.increment();
                            return Mono.just(cart);
                        })
                        .orElseGet(() -> {
                            return this.itemRepository.findById(id)
                                    .map(item -> new CartItem(item))
                                    .map(cartItem -> {
                                        cart.getCartItems().add(cartItem);
                                        return cart;
                                    });
                        }))
                .flatMap(cart -> this.cartRepository.save(cart));
    }

}
