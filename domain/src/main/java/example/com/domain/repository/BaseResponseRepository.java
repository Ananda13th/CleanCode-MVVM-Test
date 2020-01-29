package example.com.domain.repository;

import example.com.domain.model.BaseResponse;
import example.com.domain.model.Dosen;
import io.reactivex.Single;

public interface BaseResponseRepository {
    Single<BaseResponse> doDeleteDosen(String idDosen);
    Single<BaseResponse> doAddDosen(Dosen dosen);
    Single<BaseResponse> doUpdateDosen(String idDosen, Dosen dosen);
}
