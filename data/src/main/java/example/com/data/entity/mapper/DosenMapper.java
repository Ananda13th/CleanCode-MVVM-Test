package example.com.data.entity.mapper;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import example.com.data.entity.DosenEntity;
import example.com.data.entity.DosenRespEntity;
import example.com.domain.model.Dosen;
import example.com.domain.model.DosenResp;

public class DosenMapper {

    public DosenResp dosenListRespToDomain(DosenRespEntity dosenRespEntity) {

        DosenResp dosenResp = new DosenResp();
        List<Dosen> listDosen = new ArrayList<>();
        dosenResp.setEpoch(dosenRespEntity.getEpoch());
        dosenResp.setErrorCode(dosenRespEntity.getErrorCode());
        dosenResp.setErrorMesssage(dosenRespEntity.getErrorMesssage());

        for(int i=0;i<dosenRespEntity.getDosenList().size();i++) {
            DosenEntity dosenEntity = dosenRespEntity.getDosenList().get(i);
            Dosen dosen = new Dosen();
            dosen.setId(dosenEntity.getId());
            dosen.setFoto(dosenEntity.getFoto());
            dosen.setNama(dosenEntity.getNama());
            dosen.setPelajaran(dosenEntity.getPelajaran());
            listDosen.add(dosen);
        }

        dosenResp.setDosenList(listDosen);
        return  dosenResp;
    }

    public Dosen dosenRespToDomain(DosenEntity dosenEntity) {
        Dosen dosen = new Dosen();
        dosen.setId(dosenEntity.getId());
        dosen.setFoto(dosenEntity.getFoto());
        dosen.setNama(dosenEntity.getNama());
        dosen.setPelajaran(dosenEntity.getPelajaran());

        return dosen;
    }




}
