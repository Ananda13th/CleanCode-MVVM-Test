package example.com.domain.repository;


import example.com.domain.model.Dosen;
import example.com.domain.model.DosenResp;
import io.reactivex.Single;

public interface DosenRepository{
    //Single Digunakan Untuk Asinc Data
    //Bisa Berjalan Di Background Aplikasi
    Single<DosenResp> doGetListDosen();

    Single<Dosen> doGetDosen(String idDosen);
}
