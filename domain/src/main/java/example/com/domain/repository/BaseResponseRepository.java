package example.com.domain.repository;

import example.com.domain.model.BaseResponse;
import io.reactivex.Single;

public interface BaseResponseRepository {
    Single<BaseResponse> doDeleteDosen(String idDosen);
}
