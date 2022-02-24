package co.com.bancolombia.model.error;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder(toBuilder = true)
public class MetaData {

    private String messageId;

    private String systemId;

    private String version;

    private String requestDate;

    private String service;

    private String operation;

    private String usrMod;

    private String ip;

}
