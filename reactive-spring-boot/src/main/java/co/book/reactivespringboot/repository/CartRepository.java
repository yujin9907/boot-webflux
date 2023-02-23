package co.book.reactivespringboot.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import co.book.reactivespringboot.entity.Cart;

public interface CartRepository extends ReactiveCrudRepository<Cart, String> {

}
