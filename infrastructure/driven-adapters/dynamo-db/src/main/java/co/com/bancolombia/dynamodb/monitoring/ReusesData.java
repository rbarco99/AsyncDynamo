package co.com.bancolombia.dynamodb.monitoring;

import lombok.*;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@DynamoDbBean
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReusesData {
    private String monitoringReusesId;
    private String monitoringId;
    private String operationReuse;
    private String requestReuse;
    private String responseReuse;
    private String dateInitOperation;
    private String dateEndOperation;
    private String stateOperation;
}
