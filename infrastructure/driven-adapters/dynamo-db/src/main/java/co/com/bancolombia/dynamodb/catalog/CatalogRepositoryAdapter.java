package co.com.bancolombia.dynamodb.catalog;

import co.com.bancolombia.dynamodb.catalogs.CatalogData;
import co.com.bancolombia.dynamodb.helper.TemplateAdapterOperations;
import co.com.bancolombia.model.catalogs.generalcatalogs.CatalogQuery;
import co.com.bancolombia.model.catalogs.generalcatalogs.CatalogResponse;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;

@Repository
public class CatalogRepositoryAdapter extends TemplateAdapterOperations<CatalogResponse,Long, CatalogData>
          {


    protected CatalogRepositoryAdapter(DynamoDbAsyncTable<CatalogData> dynamoDbAsyncTable, ObjectMapper mapper) {
        super(dynamoDbAsyncTable, mapper, data -> mapper.map(data,CatalogResponse.class));
    }

    String GENERAL_CATALOG_CODE = "general-catalog";

    //@Override
    public Mono<CatalogResponse> getGeneralCatalogByParent(CatalogQuery query) {
        return Mono.fromCompletionStage(dynamoDbAsyncTable.getItem(Key.builder().partitionValue(GENERAL_CATALOG_CODE).build()))
                .switchIfEmpty(Mono.error(new Exception("Catalogo no encontrado")))
                .map(this::toModel);
    }

    //@Override
    public String getStrategyCode() {
        return null;
    }

   // @Override
    public Mono<CatalogResponse> saveCatalog(CatalogResponse catalogs){
        CatalogData catalogData = this.toEntity(catalogs);
        catalogData.setCode("Save test new");
        return this.save(catalogData)
                .map(c->this.toModel(c));
    }
}
