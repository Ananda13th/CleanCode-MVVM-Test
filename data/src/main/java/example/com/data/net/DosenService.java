package example.com.data.net;

import example.com.data.entity.BaseResponseEntity;
import example.com.data.entity.DosenEntity;
import example.com.data.entity.DosenRespEntity;
import example.com.domain.model.BaseResponse;
import io.reactivex.Single;
import retrofit2.Call;

import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DosenService {
    @GET("dosens")
    Single<DosenRespEntity> getDosenList();

    @GET("dosens/{id}")
    Single<DosenEntity> getDosen(@Path("id") String id);

    @DELETE("dosens/{id}")
    Single<BaseResponseEntity> deleteDosen(@Path("id") String id);

}
