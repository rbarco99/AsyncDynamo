package co.com.bancolombia.model.catalogs.generalcatalogs;

import co.com.bancolombia.model.error.MetaData;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder(toBuilder = true)
public class CatalogQuery {
    private MetaData meta;
    private CatalogQueryData data;
}
