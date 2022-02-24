package co.com.bancolombia.model.monitoring;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Monitoring {
    private String id;
    private String customerAcquisitionId;
    private Object request;
    private Object response;
    private String operation;
    private LocalDateTime dateInitOperation;
    private LocalDateTime dateEndOperation;
    private Number stateOperation;
    private List<Reuses> reuses;
}
