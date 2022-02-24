package co.com.bancolombia.dynamodb.monitoring;

import lombok.*;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@DynamoDbBean
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MonitoringData {
        @Getter(AccessLevel.NONE)
        private String id;
        private String customerAcquisitionId;
        private String operation;
        private String request;
        private List<ReusesData> reuses;
        private String dateInitOperation;

        @DynamoDbPartitionKey
        public String getId() {
            return this.id;
        }

        //@DynamoDbConvertedBy(DateTimeTranslator.class)

}
