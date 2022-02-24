package co.com.bancolombia.model.catalogs.generalcatalogs;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CatalogQueryData {
    private String customerAcquisitionId;
    private String parent;
}
