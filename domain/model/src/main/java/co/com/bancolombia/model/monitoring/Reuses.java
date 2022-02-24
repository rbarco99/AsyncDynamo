package co.com.bancolombia.model.monitoring;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Reuses {
    private UUID monitoringReusesId;
    private Long monitoringId;
    private String operationReuse;
    private Object requestReuse;
    private Object responseReuse;
    private LocalDateTime dateInitOperation;
    private LocalDateTime dateEndOperation;
    private Number stateOperation;
}
