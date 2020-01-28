package example.com.data.repository;

import example.com.data.entity.mapper.BaseResponseMapper;
import example.com.data.net.DosenService;
import example.com.domain.model.BaseResponse;
import example.com.domain.repository.BaseResponseRepository;
import io.reactivex.Scheduler;
import io.reactivex.Single;

public class BaseResponseRepositoryImpl implements BaseResponseRepository {

    private final BaseResponseMapper baseResponseMapper;
    private final Scheduler scheduler;
    private final DosenService dosenService;

    public BaseResponseRepositoryImpl(BaseResponseMapper baseResponseMapper, Scheduler scheduler, DosenService dosenService) {
        this.baseResponseMapper = baseResponseMapper;
        this.scheduler = scheduler;
        this.dosenService = dosenService;
    }


    @Override
    public Single<BaseResponse> doDeleteDosen(String idDosen) {
        return Single.defer(()->dosenService.deleteDosen(idDosen))
                .map(baseResponseMapper::baseResponseToDomain)
                .subscribeOn(scheduler);
    }
}
