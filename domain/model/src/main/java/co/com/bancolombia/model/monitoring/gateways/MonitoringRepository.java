package co.com.bancolombia.model.monitoring.gateways;

import co.com.bancolombia.model.monitoring.Monitoring;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MonitoringRepository {

    Mono<Monitoring> insertMonitoring(Monitoring monitoring);

    Mono<Monitoring> getMonitoringByID(String monitoringID);

    Flux<Monitoring> findAll();

    Flux<Monitoring> filterUntilDate(String Date);
}
