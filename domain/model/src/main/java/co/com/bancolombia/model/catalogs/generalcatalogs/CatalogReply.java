package co.com.bancolombia.model.catalogs.generalcatalogs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder(toBuilder = true)
public class CatalogReply {
    private CatalogResponse data;
}
