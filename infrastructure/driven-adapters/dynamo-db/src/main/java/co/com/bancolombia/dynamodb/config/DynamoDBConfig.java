package co.com.bancolombia.dynamodb.config;

import co.com.bancolombia.dynamodb.catalogs.CatalogData;
import co.com.bancolombia.model.secrets.DynamoSecretModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.*;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;

import java.net.URI;

@Configuration
public class DynamoDBConfig {

    @Bean
    DynamoDbAsyncClient dbClient(DynamoSecretModel dynamoSecretModel) {
        return DynamoDbAsyncClient.builder()
                .endpointOverride(URI.create("http://dynamodb.us-east-1.amazonaws.com"))
                .region(Region.US_EAST_1)
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(dynamoSecretModel.getAcesKeyID(), dynamoSecretModel.getSecretAcesKey())))
                .build();
    }

    @Bean
    DynamoDbEnhancedAsyncClient dbEnhancedClient(DynamoDbAsyncClient dbClient) {
        return DynamoDbEnhancedAsyncClient.builder().dynamoDbClient(dbClient).build();
    }


}
