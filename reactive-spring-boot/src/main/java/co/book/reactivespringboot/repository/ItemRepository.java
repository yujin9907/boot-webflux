package co.book.reactivespringboot.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FluentMongoOperations;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import co.book.reactivespringboot.entity.Item;
import reactor.core.publisher.Flux;

public interface ItemRepository extends ReactiveCrudRepository<Item, String>, ReactiveQueryByExampleExecutor<Item> {

    // 네이밍 규칙 1
    Flux<Item> findByNameContaining(String name);

    // 네이밍 규칙 2 커스텀 : 이름과 나이에 따른 조회 (가정)
    @Query("{'name':?0, 'age':?1}")
    Flux<Item> FindItemsForCustmoer(String name, int age);

    // 네이밍 규칙 3 한계 : name 과 description 검색
    // Flux<Item> findByNameContainingOrDescriptionContainingAllIgrnoreCase(String
    // name, String description);
    // 심지어 오타인듯 오류남

    // example 쿼리 : itemRepository 상속, InventoryService 구현

}
