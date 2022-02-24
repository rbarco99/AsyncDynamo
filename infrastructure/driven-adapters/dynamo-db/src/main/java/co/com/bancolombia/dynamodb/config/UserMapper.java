package co.com.bancolombia.dynamodb.config;


import co.com.bancolombia.dynamodb.DynamoDBRepositoryAdapter;
import co.com.bancolombia.model.user.User;

public class UserMapper {

    public DynamoDBRepositoryAdapter.UserData toUserData(User user) {
        return DynamoDBRepositoryAdapter.UserData.builder()
                .id(user.getId())
                .name(user.getName())
                .lastName(user.getLastName())
                .address(user.getAddress())
                .age(user.getAge())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    public User toUser(DynamoDBRepositoryAdapter.UserData userData) {
        return User.builder()
                .id(userData.getId())
                .name(userData.getName())
                .lastName(userData.getLastName())
                .address(userData.getAddress())
                .age(userData.getAge())
                .createdAt(userData.getCreatedAt())
                .updatedAt(userData.getUpdatedAt())
                .build();
    }
}
