package co.com.bancolombia.dynamodb.catalogs;

import co.com.bancolombia.dynamodb.catalogs.CatalogItemData;
import lombok.*;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@DynamoDbBean
@AllArgsConstructor
@Builder(toBuilder = true)
public class CatalogMapData {
    private String parent;
    private List<CatalogItemData> generalCatalogList;

}
