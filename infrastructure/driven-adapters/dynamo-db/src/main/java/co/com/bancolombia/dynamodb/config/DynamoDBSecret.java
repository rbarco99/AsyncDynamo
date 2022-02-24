package co.com.bancolombia.dynamodb.config;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DynamoDBSecret {
    private final String uri;
    private final String databaseName;
}