package co.book.reactivespringboot.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.mongodb.core.FluentMongoOperations;
import org.springframework.data.mongodb.core.ReactiveFluentMongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import co.book.reactivespringboot.entity.Item;
import co.book.reactivespringboot.repository.CartRepository;
import co.book.reactivespringboot.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@Service
public class InventoryService {

    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;
    // private final ReactiveFluentMongoOperations fluentMongoOperations;

    // example 쿼리 : name과 discription 검색
    public Flux<Item> searchByExample(String name, String description, boolean isUsed) {
        Item item = new Item(name, description, 0.0);

        ExampleMatcher matcher = (isUsed
                ? ExampleMatcher.matchingAll()
                : ExampleMatcher.matchingAny())
                .withStringMatcher(StringMatcher.CONTAINING)
                .withIgnoreCase()
                .withIgnorePaths("price");
        Example<Item> pro = Example.of(item, matcher);

        return itemRepository.findAll(pro);
    }

    // 평문형 연산 쿼리:
    // name 과
    // description 검색

    // public Flux<Item> searchByFluen(String name, String description) {
    // return fluentMongoOperations.query(Item.class)
    // .matching(query(where("test").is(name).and("test2").is(description)))
    // .all();
    // }
}
