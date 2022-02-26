package co.com.bancolombia.dynamodb.catalogs;

import co.com.bancolombia.dynamodb.helper.AdapterOperations;
import co.com.bancolombia.model.catalogs.gateways.GeneralCatalogsRepository;
import co.com.bancolombia.model.catalogs.generalcatalogs.CatalogQuery;
import co.com.bancolombia.model.catalogs.generalcatalogs.CatalogResponse;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;

@Repository
public class CatalogsRepositoryAdapter extends AdapterOperations<CatalogResponse,String, CatalogData>
        implements GeneralCatalogsRepository {

    public CatalogsRepositoryAdapter(DynamoDbEnhancedAsyncClient connectionFactory, ObjectMapper mapper,@Value("${aws.dynamodb.tableName}") String tableName) {

        super(connectionFactory, mapper, d -> mapper.map(d, CatalogResponse.class), tableName);
    }

    private static String GENERAL_CATALOG_CODE = "general-catalog";

    @Override
    public Mono<CatalogResponse> getGeneralCatalogByParent(CatalogQuery query) {
        return this.getById(GENERAL_CATALOG_CODE)
                .switchIfEmpty(Mono.error(new Exception("Catalogo no encontrado")));
    }

    @Override
    public String getStrategyCode() {
        return null;
    }

    @Override
    public Mono<Void> saveCatalog(CatalogResponse catalogs) {
        CatalogData catalogData = this.toEntity(catalogs);
        catalogData.setCode("TestCode02");
        return this.saveEntity(catalogData);
    }

    @Override
    public Mono<Void> communicationTest() {
        return this.saveEntity(CatalogData.builder().code("test01catalog").build());
    }

}
