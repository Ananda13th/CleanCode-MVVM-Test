package example.com.data.net;

import example.com.data.entity.DosenEntity;
import example.com.data.entity.DosenRespEntity;
import io.reactivex.Single;
import retrofit2.Call;

import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DosenService {
    @GET("dosens")
    Single<DosenRespEntity> getDosenList();

    @GET("dosens/{id}")
    Single<DosenEntity> getDosen(@Path("id") String id);

}
