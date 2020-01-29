package example.com.data.repository;

import example.com.data.entity.mapper.DosenMapper;
import example.com.data.net.DosenService;
import example.com.domain.model.BaseResponse;
import example.com.domain.model.Dosen;
import example.com.domain.repository.BaseResponseRepository;
import io.reactivex.Scheduler;
import io.reactivex.Single;

public class BaseResponseRepositoryImpl implements BaseResponseRepository {

    private final DosenMapper dosenMapper;
    private final Scheduler scheduler;
    private final DosenService dosenService;

    public BaseResponseRepositoryImpl(DosenMapper dosenMapper, Scheduler scheduler, DosenService dosenService) {
        this.dosenMapper = dosenMapper;
        this.scheduler = scheduler;
        this.dosenService = dosenService;
    }


    @Override
    public Single<BaseResponse> doDeleteDosen(String idDosen) {
        return Single.defer(()->dosenService.deleteDosen(idDosen))
                .map(dosenMapper::baseResponseToDomain)
                .subscribeOn(scheduler);
    }


    @Override
    public Single<BaseResponse> doAddDosen(Dosen dosen) {
        return Single.defer(()->dosenService.addDosen(dosenMapper.dosenToData(dosen)))
                .map(dosenMapper::baseResponseToDomain)
                .subscribeOn(scheduler);
    }

    @Override
    public Single<BaseResponse> doUpdateDosen(String idDosen, Dosen dosen) {
        return Single.defer(()->dosenService.updateDosen(dosen.getId(), dosenMapper.dosenToData(dosen)))
                .map(dosenMapper::baseResponseToDomain)
                .subscribeOn(scheduler);
    }
}
