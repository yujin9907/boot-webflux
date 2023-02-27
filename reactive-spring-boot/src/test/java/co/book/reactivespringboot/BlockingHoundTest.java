package co.book.reactivespringboot;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class BlockingHoundTest {
    // 블록킹... 내가 니를 못 빻는 게 천추의 한이다

    @Test
    public void 스래드슬맆을이용해서블락킹호출테스트() { // threadSleepForBlockingCall
        Mono.delay(Duration.ofSeconds(1))
                .flatMap(tick -> {
                    try {
                        Thread.sleep(10);
                        return Mono.just("호출");
                    } catch (InterruptedException e) {
                        return Mono.error(e);
                    }
                })
                .as(StepVerifier::create)
                .verifyComplete();
    }

    @Test
    public void 스래드슬맆을이용해서블락킹호출테스트통과() { // threadSleepForBlockingCall
        Mono.delay(Duration.ofSeconds(1))
                .flatMap(tick -> {
                    try {
                        Thread.sleep(10);
                        return Mono.just("호출");
                    } catch (InterruptedException e) {
                        return Mono.error(e);
                    }
                })
                .as(StepVerifier::create)
                .verifyErrorMatches(throwable -> { // 검증의 에러를 넣음(통과 하는 코드)
                    assertThat(throwable.getMessage()).contains("Bolcking cll! java.lang.Threaqd.sleep");
                    return true;
                });
    }
}
