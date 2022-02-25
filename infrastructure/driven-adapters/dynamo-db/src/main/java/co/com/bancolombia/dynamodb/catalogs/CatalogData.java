package co.com.bancolombia.dynamodb.catalogs;

import lombok.*;
import software.amazon.awssdk.enhanced.dynamodb.internal.converter.attribute.MapAttributeConverter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbConvertedBy;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.util.List;

@Getter
@Setter
@DynamoDbBean
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CatalogData {
    @Getter(AccessLevel.NONE)
    private String code;
    @Getter(AccessLevel.NONE)
    private List<CatalogMapData> generalCatalog;
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private String mapfield;

    @DynamoDbPartitionKey
    public String getCode() {
        return this.code;
    }

    public List<CatalogMapData> getGeneralCatalog() {
        return generalCatalog;
    }
    public void setGeneralCatalog(List<CatalogMapData> generalCatalog) {
        this.generalCatalog = generalCatalog;
    }


    public String getMapfield() {
        return mapfield;
    }

    public void setMapfield(String mapfield) {
        this.mapfield = mapfield;
    }
}

