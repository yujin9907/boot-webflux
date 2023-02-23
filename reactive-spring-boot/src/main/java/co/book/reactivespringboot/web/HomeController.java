package co.book.reactivespringboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.result.view.Rendering;

import co.book.reactivespringboot.entity.Cart;
import co.book.reactivespringboot.repository.CartRepository;
import co.book.reactivespringboot.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;

    @GetMapping
    Mono<Rendering> home() {
        return Mono.just(Rendering.view("home.html")
                .modelAttribute("item", this.itemRepository.findAll())
                .modelAttribute("cart", this.cartRepository.findById("My Cart")
                        .defaultIfEmpty(new Cart("My Cart")))
                .build());
    }
}
