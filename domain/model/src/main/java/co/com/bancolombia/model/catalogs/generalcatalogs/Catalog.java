package co.com.bancolombia.model.catalogs.generalcatalogs;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Catalog {

    private String parent;

    private List<CatalogItem> generalCatalogList;

}
