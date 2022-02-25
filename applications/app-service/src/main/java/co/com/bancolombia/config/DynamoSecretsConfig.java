package co.com.bancolombia.config;

import co.com.bancolombia.model.secrets.DynamoSecretModel;
import co.com.bancolombia.secretsmanager.config.AWSSecretsManagerConfig;
import co.com.bancolombia.secretsmanager.connector.AWSSecretManagerConnectorAsync;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;

@Configuration
public class DynamoSecretsConfig {
    public static final String AMBIENTE_LOCAL = "local";

    @Value("${app.perfil}")
    private String perfil;

    @Value("${aws.dynamodb.endpoint}")
    private String endpoint;

    @Value("${aws.dynamodb.accessKeyId}")
    private String accessKeyID;

    @Value("${aws.dynamodb.secretAccessKey}")
    private String secretAccessKey;

    @Bean
    public DynamoSecretModel getDBSecretManager(@Value("${aws.dynamodb.secret.region}") String region, @Value("${aws.dynamodb.secret.name}") String secretName ) {
        if (perfil.equals(AMBIENTE_LOCAL))
            return dynamoSecretModelLocal();
        else
            return new AWSSecretManagerConnectorAsync(getConfig(region)).getSecret(secretName, DynamoSecretModel.class).block();
    }

    private AWSSecretsManagerConfig getConfig(String region) {
        return AWSSecretsManagerConfig.builder()
                .region(Region.of(region))
                .cacheSize(5)
                .cacheSeconds(3600)
                .build();
    }

    private DynamoSecretModel dynamoSecretModelLocal(){
        return new DynamoSecretModel(this.accessKeyID,this.secretAccessKey);
    }
}

