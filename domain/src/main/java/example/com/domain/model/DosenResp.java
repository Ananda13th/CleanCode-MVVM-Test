package example.com.domain.model;

import java.util.List;

import lombok.Data;

@Data
public class DosenResp {
    private String errorCode;
    private String errorMesssage;
    private String epoch;
    private List<Dosen> dosenList;
}
