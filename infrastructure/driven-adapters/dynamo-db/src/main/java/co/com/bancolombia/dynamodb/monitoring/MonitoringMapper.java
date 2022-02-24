package co.com.bancolombia.dynamodb.monitoring;

import co.com.bancolombia.model.monitoring.Monitoring;

import java.time.LocalDateTime;

public class MonitoringMapper {
    public Monitoring toMonitoring(MonitoringData md){
        return Monitoring.builder()
                .id(md.getId())
                .customerAcquisitionId(md.getCustomerAcquisitionId())
                .request(md.getRequest())
                .dateInitOperation(LocalDateTime.parse(md.getDateInitOperation()))
                .operation(md.getOperation())


                .build();
    }
}
