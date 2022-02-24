package co.com.bancolombia.dynamodb.monitoring;

import org.springframework.format.datetime.joda.LocalDateTimeParser;
import software.amazon.awssdk.enhanced.dynamodb.AttributeConverter;
import software.amazon.awssdk.enhanced.dynamodb.AttributeValueType;
import software.amazon.awssdk.enhanced.dynamodb.EnhancedType;
import software.amazon.awssdk.enhanced.dynamodb.internal.converter.string.LocalDateTimeStringConverter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbConvertedBy;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.lang.annotation.Annotation;
import java.time.LocalDateTime;

public class DateTimeTranslator<T> implements AttributeConverter<T> {

    @Override
    public AttributeValue transformFrom(Object input) {
        return AttributeValue.builder().s(input.toString()).build();
    }

    @Override
    public T transformTo(AttributeValue input) {
        return null;
    }

    @Override
    public EnhancedType type() {
        return EnhancedType.of(LocalDateTime.class);
    }

    @Override
    public AttributeValueType attributeValueType() {
        return AttributeValueType.S;
    }
}
