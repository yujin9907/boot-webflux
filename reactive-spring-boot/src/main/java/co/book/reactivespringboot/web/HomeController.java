package co.book.reactivespringboot.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.result.view.Rendering;

import co.book.reactivespringboot.entity.Cart;
import co.book.reactivespringboot.entity.CartItem;
import co.book.reactivespringboot.entity.Item;
import co.book.reactivespringboot.repository.CartRepository;
import co.book.reactivespringboot.repository.ItemRepository;
import co.book.reactivespringboot.service.CartService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final CartService cartService;
    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;

    @GetMapping
    public Mono<Rendering> home() {

        itemRepository.save(new Item("pen", 0.75));

        return Mono.just(Rendering.view("home.html")
                .modelAttribute("items", this.itemRepository.findAll())
                .modelAttribute("cart", this.cartRepository.findById("My Cart")
                        .defaultIfEmpty(new Cart("My Cart")))
                .build());
    }

    @PostMapping("/add/{id}")
    public Mono<String> addToCart(@PathVariable(name = "id") String id) {
        return this.cartService.addToCart("My cart", id)
                .thenReturn("redirect:/");
    }

    @ResponseBody
    @PostMapping("/test")
    public Mono<Item> save(@RequestBody Item dto) { // {"title":"제목3","content":"내용3"}
        Mono<Item> boardEntity = itemRepository.save(dto);

        return boardEntity;
    }
}
