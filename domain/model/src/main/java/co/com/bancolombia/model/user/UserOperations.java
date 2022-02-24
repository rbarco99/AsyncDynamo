package co.com.bancolombia.model.user;

import co.com.bancolombia.model.user.common.UniqueIDGenerator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.Date;

import static reactor.core.publisher.Mono.just;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserOperations {

    public static Mono<User> createUser(Date now, User user) {
        return UniqueIDGenerator.random()
                .flatMap(id -> just(user.toBuilder()
                        .id(id)
                        .createdAt(String.valueOf(now))
                        .updatedAt(String.valueOf(now))
                        .build()));
    }
}
