package co.book.reactivespringboot.web;

import javax.print.attribute.standard.MediaTray;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import co.book.reactivespringboot.entity.Dish;
import co.book.reactivespringboot.service.KitchenService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@RestController
public class ServerController {

    private final KitchenService kitchenService;

    @GetMapping(value = "/index", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<Dish> testIndex() {
        return this.kitchenService.getDishes();
    }

    @GetMapping(value = "/index-2", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<Dish> deliveredDishes() {
        return this.kitchenService.getDishes()
                .map(d -> Dish.deliver(d));
    }

}
