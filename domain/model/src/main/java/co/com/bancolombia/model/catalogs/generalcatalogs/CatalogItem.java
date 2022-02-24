package co.com.bancolombia.model.catalogs.generalcatalogs;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CatalogItem {

    private String code;

    private String name;

    private String icon;

    private String abbreviation;

}
