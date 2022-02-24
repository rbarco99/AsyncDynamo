package co.com.bancolombia.api;
import co.com.bancolombia.model.catalogs.gateways.GeneralCatalogsRepository;
import co.com.bancolombia.model.catalogs.generalcatalogs.Catalog;
import co.com.bancolombia.model.catalogs.generalcatalogs.CatalogQuery;
import co.com.bancolombia.model.catalogs.generalcatalogs.CatalogResponse;
import co.com.bancolombia.model.monitoring.Monitoring;
import co.com.bancolombia.model.monitoring.gateways.MonitoringRepository;
import co.com.bancolombia.model.user.User;
import co.com.bancolombia.usecase.dynamo.DynamoUseCase;
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
    private final MonitoringRepository monitoringRepository;
    private final GeneralCatalogsRepository generalCatalogsRepository;

    @GetMapping(path = "/hola")
    public String commandName() {
        User user = User.builder().id("usuer02").name("Ricardo").lastName("Barco").build();
        //userRepository.save(user);
        return "Hello World";
    }

    @GetMapping(path = "/holaa")
    public Mono<User> getMeth() {
        //userRepository.findById("usuer02")
                //.subscribe(value -> System.out.println(value.getName()));
        //userRepository.findById("usuer02");
        return Mono.just(User.builder().build());
    }
    @GetMapping(path = "/moni")
    public Mono<Monitoring> monitoring() {
        return monitoringRepository.insertMonitoring(Monitoring.builder().id("m01").operation("operation").build());
    }
    @GetMapping(path = "/getMonitoringById/{id}")
    public Mono<Monitoring> getMonitoring(@PathVariable("id")String id) {
        return monitoringRepository.getMonitoringByID(id);
    }
    @PostMapping(path = "/saveMonitoring")
    public Mono<Monitoring> getMonitoring(@RequestBody ReqTest req) {
        Monitoring mon = Monitoring.builder()
                .id(req.getId())
                .request(req.getRequest())
                .customerAcquisitionId(req.getCustomerAcquisitionId())
                .reuses(req.getReuses())
                .dateInitOperation(req.getDateInitOperation())
                .build();
        System.out.println(req.getReuses());
        return monitoringRepository.insertMonitoring(mon);
    }
    @GetMapping(path = "/getAll")
    public Flux<Monitoring> getAllMonitoring() {
        return monitoringRepository.findAll();
    }
    @GetMapping(path = "/filterDate")
    public Flux<Monitoring> filterUntilDate(@PathVariable LocalDateTime date) {
        return monitoringRepository.filterUntilDate(date.toString());
    }

    @GetMapping(path = "/generalCatalogs")
    public Mono<CatalogResponse> getGeneralCatalog() {
        CatalogQuery catQuery = CatalogQuery.builder().build();
        return generalCatalogsRepository.getGeneralCatalogByParent(catQuery);
    }
    @PostMapping(path = "/saveCatalogs")
    public Mono<Void> saveMonitoringRest(@RequestBody CatalogResponse req) {

        List<Catalog> catalogList= Arrays.asList((Catalog.builder().parent("Saved").build()));

        CatalogResponse save = CatalogResponse.builder().generalCatalog(catalogList).build();
        return generalCatalogsRepository.saveCatalog(req);
    }

}
