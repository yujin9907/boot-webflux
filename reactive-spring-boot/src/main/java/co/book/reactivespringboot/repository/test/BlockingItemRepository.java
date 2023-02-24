package co.book.reactivespringboot.repository.test;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.book.reactivespringboot.entity.Item;

public interface BlockingItemRepository extends CrudRepository<Item, String> {

}
