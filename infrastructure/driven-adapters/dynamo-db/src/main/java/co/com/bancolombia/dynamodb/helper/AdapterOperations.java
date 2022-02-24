package co.com.bancolombia.dynamodb.helper;

import org.reactivecommons.utils.ObjectMapper;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.PagePublisher;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class  AdapterOperations <E, K, V>{
    private final Class<V> dataClass;
    private final Function<V, E> toEntityFn;
    protected ObjectMapper mapper;
    protected DynamoDbAsyncTable<V> customerTable;

    protected AdapterOperations(DynamoDbEnhancedAsyncClient dynamoDbEnhancedAsyncClient, ObjectMapper mapper, Function<V, E> toEntityFn, String tableName) {
        this.toEntityFn = toEntityFn;
        this.mapper = mapper;
        ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.dataClass = (Class<V>) genericSuperclass.getActualTypeArguments()[2];
        this.customerTable  = dynamoDbEnhancedAsyncClient.table(tableName, TableSchema.fromBean(dataClass));
    }
    public Mono<Void> save(E model) {
        return Mono.fromFuture(customerTable.putItem( toEntity(model)));
    }

    public Mono<Void> saveEntity(V entity){return Mono.fromFuture(customerTable.putItem(entity));}

    public Mono<E> getById(K id) {
        return Mono.fromFuture(customerTable.getItem(Key.builder().partitionValue(AttributeValue.builder().s((String) id).build()).build())).map(this::toModel);
    }

    public Mono<E> delete(E model) {
        return Mono.fromFuture(customerTable.deleteItem(toEntity(model))).map(this::toModel);
    }

    public Mono<List<E>> query(QueryEnhancedRequest queryExpression) {
        PagePublisher<V> pagePublisher = customerTable.query(queryExpression);
        return listOfModel(pagePublisher);
    }
    private Mono<List<E>> listOfModel(PagePublisher<V> pagePublisher) {
        return Mono.from(pagePublisher).map(page -> page.items().stream().map(this::toModel).collect(Collectors.toList()));
    }

    protected V toEntity(E model) {
        return mapper.map(model, dataClass);
    }

    protected E toModel(V data) {
        return data != null ? toEntityFn.apply(data) : null;
    }
}
