package example.com.cleancodetest.model;

import lombok.Data;

@Data
public class BaseResponseModel {
    private String errorCode;
    private String errorMesssage;
    private String epoch;
}
