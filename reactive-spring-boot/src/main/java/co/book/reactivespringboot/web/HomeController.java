package co.book.reactivespringboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.result.view.Rendering;

import co.book.reactivespringboot.entity.Cart;
import co.book.reactivespringboot.entity.Item;
import co.book.reactivespringboot.repository.CartRepository;
import co.book.reactivespringboot.repository.ItemRepository;
import co.book.reactivespringboot.service.CartService;
import co.book.reactivespringboot.service.InventoryService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final CartService cartService;
    private final InventoryService inventoryService;
    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;

    @ResponseBody
    @PostMapping("/test")
    public Mono<Item> saveDummy(@RequestBody Item dto) { // {"title":"제목3","content":"내용3"}
        Mono<Item> boardEntity = itemRepository.save(dto);

        return boardEntity;
    }

    @GetMapping
    public Mono<Rendering> home() {

        itemRepository.save(new Item("pen", 0.75));

        return Mono.just(Rendering.view("home.html")
                .modelAttribute("items", this.itemRepository.findAll())
                .modelAttribute("cart", this.cartRepository.findById("My cart")
                        .defaultIfEmpty(new Cart("My Cart")))
                .build());
    }

    @PostMapping("/add/{id}")
    public Mono<String> addToCart(@PathVariable(name = "id") String id) {
        return this.cartService.addToCart("My cart", id)
                .thenReturn("redirect:/");
    }

    @GetMapping("/search")
    public Mono<Rendering> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam boolean isUsed) {
        return Mono.just(Rendering.view("home.html")
                .modelAttribute("items", inventoryService.searchByExample(name, description, isUsed))
                .modelAttribute("cart", cartRepository.findById("My cart"))
                .build());

    }

}
