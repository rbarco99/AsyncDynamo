package co.com.bancolombia.model.catalogs.gateways;

import co.com.bancolombia.model.catalogs.generalcatalogs.CatalogQuery;
import co.com.bancolombia.model.catalogs.generalcatalogs.CatalogResponse;
import reactor.core.publisher.Mono;

public interface GeneralCatalogsRepository {

    Mono<CatalogResponse> getGeneralCatalogByParent(CatalogQuery query);

    String getStrategyCode();

    Mono<Void> saveCatalog(CatalogResponse catalogs);

    Mono<Void>communicationTest();


}
