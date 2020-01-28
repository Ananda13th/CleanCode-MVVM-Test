package example.com.domain.model;

import lombok.Data;

@Data
public class Dosen extends BaseResponse{
    private String nama;
    private String id;
    private String pelajaran;
    private String foto;
}
