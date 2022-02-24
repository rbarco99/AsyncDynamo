package co.com.bancolombia.dynamodb.helper;


import org.reactivecommons.utils.ObjectMapper;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;

import java.lang.reflect.ParameterizedType;
import java.util.function.Function;

import static reactor.core.publisher.Mono.just;

public abstract class TemplateAdapterOperations<E, K, V> {
    private final Class<V> dataClass;
    private final Function<V, E> toEntityFn;
    protected ObjectMapper mapper;
    protected DynamoDbAsyncTable<V> dynamoDbAsyncTable;

    protected TemplateAdapterOperations( DynamoDbAsyncTable<V> dynamoDbAsyncTable, ObjectMapper mapper, Function<V, E> toEntityFn) {
        this.toEntityFn = toEntityFn;
        this.mapper = mapper;
        ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.dataClass = (Class<V>) genericSuperclass.getActualTypeArguments()[2];
        /* Crea tabla si no existe */
        this.dynamoDbAsyncTable = dynamoDbAsyncTable;

    }

    public Mono<V> save(V entity) {
        dynamoDbAsyncTable.putItem(entity);
        return just(entity);

    }

    public Mono<E> getById(K id) {
        return Mono.fromCompletionStage(dynamoDbAsyncTable.getItem(Key.builder().partitionValue((String) id).build()))
                .map(this::toModel);
    }

    protected V toEntity(E model) {
        return mapper.map(model, dataClass);
    }

    protected E toModel(V data) {
        return data != null ? toEntityFn.apply(data) : null;
    }
}

