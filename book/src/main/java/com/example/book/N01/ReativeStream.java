package com.example.book.N01;

import reactor.core.publisher.Flux;

class KitchenService {
    Flux<Dish> getDish() {
        return Flux.just(
                new Dish("cake"),
                new Dish("Lo mein noodles, plain"));
    }
}

class CookServer {
    private final KitchenService k;

    CookServer(KitchenService k) {
        this.k = k;
    }

    Flux<Dish> doingJob() {
        return this.k.getDish()
                .doOnNext(dish -> System.out.println("thank u"))
                .doOnNext(dish -> System.out.println("if you w..."));
    }

    Flux<Dish> doingJob2() { // 하나의 콜백을 사용하는 게 성능면에서 유리함
        return this.k.getDish()
                .doOnNext(Dish -> {
                    System.out.println("thank u");
                    System.out.println("if you w...");
                });
    }

    // 가독성 < 작업, 기능을 명확하게 분리
}

public class ReativeStream {
    public static void main(String[] args) {
        CookServer server = new CookServer(new KitchenService()); // 객체 생성

        server.doingJob2().subscribe(
                dish -> System.out.println("job"));
    }
}