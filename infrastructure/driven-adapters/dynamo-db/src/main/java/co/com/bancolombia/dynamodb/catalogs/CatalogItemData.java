package co.com.bancolombia.dynamodb.catalogs;

import lombok.*;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@Getter
@Setter
@DynamoDbBean
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CatalogItemData {

    private String code;

    private String name;

    private String icon;

    private String abbreviation;
}
