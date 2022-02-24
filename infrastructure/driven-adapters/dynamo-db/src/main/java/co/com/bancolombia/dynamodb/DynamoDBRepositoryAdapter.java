package co.com.bancolombia.dynamodb;

import co.com.bancolombia.model.user.User;
import co.com.bancolombia.model.user.gateways.UserRepository;
import lombok.*;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import static reactor.core.publisher.Mono.fromCompletionStage;
import static reactor.core.publisher.Mono.just;

@Repository
@AllArgsConstructor
public class DynamoDBRepositoryAdapter implements UserRepository {


    @Override
    public Mono<User> save(User user) {
       // dynamoDbTable.putItem(userMapper.toUserData(user));
        return just(user);
    }

    @Override
    public Mono<Void> saveAll(Flux<User> users) {
        return null;
    }

    @Override
    public Mono<User> findById(String id) {
        //Mono<UserData> userData = fromCompletionStage(dynamoDbTable.getItem(Key.builder().partitionValue(id).build()));
        //userData.map(user -> userMapper.toUser(user));
        return null;

    }

    @Override
    public Flux<User> findAll() {
        return null;
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return null;
    }

    @Getter
    @Setter
    @DynamoDbBean
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class UserData {
        @Getter(AccessLevel.NONE)
        private String id;
        private String name;
        private String lastName;
        private int age;
        private String address;
        private String createdAt;
        private String updatedAt;

        @DynamoDbPartitionKey
        public String getId() {
            return this.id;
        }
    }
}
