package co.com.bancolombia.model.secrets;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class DynamoSecretModel {
    private String acesKeyID;
    private String secretAcesKey;
}
