package co.com.bancolombia.api;
import co.com.bancolombia.model.catalogs.gateways.GeneralCatalogsRepository;
import co.com.bancolombia.model.catalogs.generalcatalogs.Catalog;
import co.com.bancolombia.model.catalogs.generalcatalogs.CatalogQuery;
import co.com.bancolombia.model.catalogs.generalcatalogs.CatalogResponse;
import co.com.bancolombia.model.monitoring.Monitoring;
import co.com.bancolombia.model.monitoring.gateways.MonitoringRepository;
import co.com.bancolombia.model.user.User;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ApiRest {
//    private final MyUseCase useCase;
    //private final UserRepository userRepository;
    private final GeneralCatalogsRepository generalCatalogsRepository;

    @GetMapping(path = "/generalCatalogs")
    public Mono<CatalogResponse> getGeneralCatalog() {
        CatalogQuery catQuery = CatalogQuery.builder().build();
        return generalCatalogsRepository.getGeneralCatalogByParent(catQuery);
    }
    @PostMapping(path = "/saveCatalogs")
    public Mono<Void> saveMonitoringRest(@RequestBody CatalogResponse req) {
        return generalCatalogsRepository.saveCatalog(req);
    }
    @GetMapping(path = "/test")
    public Mono<Void> test() {
        CatalogQuery catQuery = CatalogQuery.builder().build();
        return generalCatalogsRepository.communicationTest();
    }
}
