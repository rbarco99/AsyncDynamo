package co.com.bancolombia.dynamodb.monitoring;

import co.com.bancolombia.dynamodb.helper.TemplateAdapterOperations;
import co.com.bancolombia.model.monitoring.Monitoring;
import co.com.bancolombia.model.monitoring.gateways.MonitoringRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.Expression;
import software.amazon.awssdk.enhanced.dynamodb.model.ScanEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MonitoringRepositoryAdapter extends TemplateAdapterOperations<Monitoring,String,MonitoringData>
        implements MonitoringRepository {

    protected MonitoringRepositoryAdapter(DynamoDbAsyncTable<MonitoringData> dynamoDbAsyncTable, ObjectMapper mapper) {
        super(dynamoDbAsyncTable, mapper, d -> mapper.map(d, Monitoring.class));
    }

    @Override
    public Mono<Monitoring> insertMonitoring(Monitoring monitoring) {
        MonitoringData entity = this.toEntity(monitoring);
        this.save(entity);

        return Mono.just(monitoring);
    }

    @Override
    public Mono<Monitoring> getMonitoringByID(String id) {
        return this.getById(id);
    }
    public Flux<Monitoring> findAll() {
        Flux<MonitoringData> result = Flux.from(dynamoDbAsyncTable.scan().items());
        return result.map(this::toModel);
    }
    public Flux<Monitoring>filterUntilDate(String date) {
        Map<String, AttributeValue> expressionValues = new HashMap<>();
        expressionValues.put(":value", AttributeValue
                .builder()
                        .s(date)
                .build());
        Expression expression = Expression.builder()
                .expression("dateInitOperation < :value")
                .expressionValues(expressionValues)
                .build();

        ScanEnhancedRequest scanReq = ScanEnhancedRequest.builder().filterExpression(expression).build();
        Flux<MonitoringData> result = Flux.from(dynamoDbAsyncTable.scan(scanReq).items());
        return result.map(this::toModel);
    }
}
