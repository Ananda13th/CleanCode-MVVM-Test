package example.com.data.repository;

import example.com.data.entity.mapper.DosenMapper;
import example.com.data.net.DosenService;
import example.com.data.net.ServiceGenerator;
import example.com.domain.model.Dosen;
import example.com.domain.model.DosenResp;
import example.com.domain.repository.DosenRepository;
import io.reactivex.Scheduler;
import io.reactivex.Single;

public class DosenRepositoryImpl implements DosenRepository {

    private final DosenMapper dosenMapper;
    private  final Scheduler scheduler;
    private final DosenService dosenService;

    public DosenRepositoryImpl(DosenMapper dosenMapper, Scheduler scheduler, DosenService dosenService) {
        this.dosenMapper = dosenMapper;
        this.scheduler = scheduler;
        this.dosenService = dosenService;
    }

    @Override
    public Single<DosenResp> doGetListDosen() {
        DosenService dosenService = ServiceGenerator.getDosenService();
        //Defer Untuk Menetukan Sumber Data
        return Single.defer(()->dosenService.getDosenList())
                //Menerjemahkan Data Dari dosenService.getDosenList() dari Entity ke Model di Domain
                .map(dosenMapper::dosenListRespToDomain)
                .subscribeOn(scheduler);
    }

    @Override
    public Single<Dosen> doGetDosen(String idDosen) {
        DosenService dosenService = ServiceGenerator.getDosenService();
        //Defer Untuk Menetukan Sumber Data
        return Single.defer(()->dosenService.getDosen(idDosen))
                .map(dosenMapper::dosenRespToDomain)
                .subscribeOn(scheduler);
    }
}
