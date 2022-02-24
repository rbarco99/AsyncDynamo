package co.com.bancolombia.api;

import co.com.bancolombia.model.monitoring.Reuses;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ReqTest {

        private String Id;
        private String customerAcquisitionId;
        private Object request;
        private List<Reuses> reuses;
        private LocalDateTime dateInitOperation;


}
