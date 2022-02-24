package co.com.bancolombia.model.user.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UniqueIDGenerator {

    private static final int UPPER_BOUND = 1000;

    public static Mono<String> uuid() {
        return Mono.fromSupplier(() -> UUID.randomUUID().toString());
    }

    public static Mono<String> random() {
        return Mono.fromSupplier(() -> String.valueOf(new Random().nextInt(UPPER_BOUND)));
    }

    public static Flux<String> uuids() {
        return Flux.generate(sink -> sink.next(UUID.randomUUID().toString()));
    }

    public static Mono<Date> now() {
        return Mono.fromSupplier(Date::new);
    }

}
