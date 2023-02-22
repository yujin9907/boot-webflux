package co.book.reactivespringboot.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import co.book.reactivespringboot.entity.Item;

public interface ItemRepository extends ReactiveCrudRepository<Item, String> {

}
