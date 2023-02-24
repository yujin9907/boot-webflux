package co.book.reactivespringboot.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import co.book.reactivespringboot.entity.Cart;
import co.book.reactivespringboot.entity.Item;
import reactor.core.publisher.Flux;

public interface CartRepository extends ReactiveCrudRepository<Cart, String>, ReactiveQueryByExampleExecutor<Item> {

}
