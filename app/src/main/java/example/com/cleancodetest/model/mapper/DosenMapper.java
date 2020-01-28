package example.com.cleancodetest.model.mapper;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import example.com.cleancodetest.model.DosenModel;
import example.com.cleancodetest.model.DosenRespModel;
import example.com.domain.model.Dosen;
import example.com.domain.model.DosenResp;

public class DosenMapper {

    public DosenRespModel dosenRespToView(DosenResp dosenResp) {

        DosenRespModel dosenRespModel = new DosenRespModel();
        List<DosenModel> listDosen = new ArrayList<>();
        for(int i = 0;i<dosenResp.getDosenList().size();i++) {
            Dosen dosen = dosenResp.getDosenList().get(i);
            DosenModel dosenModel = new DosenModel();
            dosenModel.setNama(dosen.getNama());
            dosenModel.setFoto(dosen.getFoto());
            dosenModel.setId(dosen.getId());
            dosenModel.setPelajaran(dosen.getPelajaran());

            listDosen.add(dosenModel);
        }
        dosenRespModel.setDosenList(listDosen);
        return dosenRespModel;
    }

    public DosenModel dosenModelToView(Dosen dosen) {
        DosenModel dosenModel = new DosenModel();
        dosenModel.setPelajaran(dosen.getPelajaran());
        dosenModel.setId(dosen.getId());
        dosenModel.setFoto(dosen.getFoto());
        dosenModel.setNama(dosen.getNama());

        return dosenModel;
    }

    public Dosen dosenModelToDomain(DosenModel dosenModel) {
        Dosen dosen = new Dosen();
        dosen.setId(dosenModel.getId());
        dosen.setNama(dosenModel.getNama());
        dosen.setPelajaran(dosenModel.getPelajaran());
        dosen.setFoto(dosenModel.getFoto());

        return dosen;
    }
}
