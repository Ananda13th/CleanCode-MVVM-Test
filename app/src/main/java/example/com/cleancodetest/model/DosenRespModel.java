package example.com.cleancodetest.model;

import java.util.List;

import lombok.Data;

@Data
public class DosenRespModel {
    private String errorCode;
    private String errorMesssage;
    private String epoch;
    private List<DosenModel> dosenList;
}

