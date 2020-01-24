package example.com.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class DosenRespEntity {
    @SerializedName("error_code")
    private String errorCode;
    @SerializedName("error_message")
    private String errorMesssage;
    private String epoch;
    @SerializedName("dosen_list")
    private List<DosenEntity> dosenList;

}
