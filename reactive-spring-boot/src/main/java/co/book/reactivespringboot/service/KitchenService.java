package co.book.reactivespringboot.service;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import co.book.reactivespringboot.entity.Dish;
import reactor.core.publisher.Flux;

@Service
public class KitchenService {

    Flux<Dish> getPublicPlz() {
        // 이 새끼 접근제어자 작성 안 하면 자동 퍼블릭 아니었냐
        // 디폴트(같은 패키지 내-service에서만 접근 가능) 였음
        return null;
    }

    public Flux<Dish> getDishes() {
        return Flux.<Dish>generate(sink -> sink.next(randomDish()))
                .delayElements(Duration.ofMillis(250));
    }

    private Dish randomDish() {
        return menu.get(picker.nextInt(menu.size()));
    }

    private Random picker = new Random();

    private List<Dish> menu = Arrays.asList(
            new Dish("fish"),
            new Dish("Lo mein noodles, plain"));

}
