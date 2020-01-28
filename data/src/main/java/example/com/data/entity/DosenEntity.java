package example.com.data.entity;

import lombok.Data;

@Data
public class DosenEntity extends BaseResponseEntity{
    private String nama;
    private String id;
    private String pelajaran;
    private String foto;
}
