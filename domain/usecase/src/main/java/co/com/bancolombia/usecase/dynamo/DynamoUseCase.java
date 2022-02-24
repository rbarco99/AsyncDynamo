package co.com.bancolombia.usecase.dynamo;

import co.com.bancolombia.model.monitoring.Monitoring;
import co.com.bancolombia.model.monitoring.gateways.MonitoringRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class DynamoUseCase {
    private final MonitoringRepository monitoringRepository;

    public Mono<Monitoring> saveMonitoring(Monitoring monitoring){
        return monitoringRepository.insertMonitoring(monitoring);
    }
}
