package co.book.reactivespringboot.repository;

import org.springframework.data.repository.CrudRepository;

// 사용 x :  시작시 로딩 문제를 해결하기 위한 방안 (1)
public interface BlockingItemRepository extends CrudRepository {

}
