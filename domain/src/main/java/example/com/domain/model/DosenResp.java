package example.com.domain.model;

import java.util.List;

import lombok.Data;

@Data
public class DosenResp extends BaseResponse{

    private List<Dosen> dosenList;
}
