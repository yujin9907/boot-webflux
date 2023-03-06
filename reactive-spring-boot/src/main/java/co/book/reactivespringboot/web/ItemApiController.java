package co.book.reactivespringboot.web;

import java.net.URI;

import javax.swing.text.AbstractDocument.Content;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.book.reactivespringboot.entity.Item;
import co.book.reactivespringboot.repository.ItemRepository;
import co.book.reactivespringboot.service.InventoryService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequestMapping("/api/*")
@RequiredArgsConstructor
@RestController
public class ItemApiController {

    private final InventoryService inventoryService;
    private final ItemRepository itemRepository;

    @GetMapping(value = "/items")
    public Flux<Item> findAll() {
        return inventoryService.getInventory();
    }

    @GetMapping(value = "/item/{id}")
    public Mono<Item> findByID(@PathVariable String id) {
        return inventoryService.getIdInventory(id);
    }

    @PostMapping(value = "/item")
    public Mono<ResponseEntity<?>> addNewItem(@RequestBody Mono<Item> item) {
        return item.flatMap(i -> inventoryService.saveItem(i)) // flatmap : mono를 mono로 반환
                .map(savedItem -> ResponseEntity
                        .created(URI.create("/item/" + savedItem.getId()))
                        .body(savedItem));
    }

    @PutMapping("/items/{id}")
    public Mono<ResponseEntity<?>> updateItem(@RequestBody Mono<Item> item, @PathVariable(name = "id") String id) {
        return item.map(content -> new Item(id, content.getName(), content.getDescription(), content.getPrice()))
                .flatMap(itemRepository::save)
                .map(ResponseEntity::ok); // http 200 ok 반환
    }
}
