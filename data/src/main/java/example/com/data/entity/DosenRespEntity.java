package example.com.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class DosenRespEntity extends BaseResponseEntity{

    @SerializedName("dosen_list")
    private List<DosenEntity> dosenList;

}
