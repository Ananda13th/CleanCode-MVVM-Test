package example.com.data;

import com.google.gson.Gson;

import org.junit.Assert;
import org.junit.Test;

import example.com.data.entity.DosenEntity;
import example.com.data.entity.DosenRespEntity;
import example.com.data.entity.mapper.DosenMapper;
import example.com.data.net.DosenService;
import example.com.data.net.ServiceGenerator;
import example.com.data.repository.DosenRepositoryImpl;
import example.com.domain.model.Dosen;
import example.com.domain.model.DosenResp;
import example.com.domain.repository.DosenRepository;
import example.com.domain.usecase.dosen.GetDosenListUseCase;
import example.com.domain.usecase.dosen.GetDosenUseCase;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.Schedulers;

public class DosenTest {

    @Test
    public  void T001_GetDosenListIntergrationTest(){
        DosenService dosenService = ServiceGenerator.getDosenService();
        Single<DosenRespEntity> resp = dosenService.getDosenList();

        TestObserver<DosenRespEntity> testObserver = new TestObserver<>();
        resp.subscribe(testObserver);
        testObserver.assertComplete();
        testObserver.assertNoErrors();
        testObserver.values();
    }

    @Test
    public  void T002_GetDosenIntegrationTest() {
        DosenService dosenService = ServiceGenerator.getDosenService();
        Single<DosenEntity> resp = dosenService.getDosen("D003");

        TestObserver<DosenEntity> testObserver = new TestObserver<>();
        resp.subscribe(testObserver);
        testObserver.assertComplete();
        testObserver.assertNoErrors();
        testObserver.values();
    }

    @Test
    public void T003_GetDosenListImplementationTest() {

        DosenMapper dosenMapper = new DosenMapper();
        Scheduler scheduler = Schedulers.io();
        DosenService dosenService = ServiceGenerator.getDosenService();
        DosenRepositoryImpl dosenRepository = new DosenRepositoryImpl(dosenMapper,scheduler,dosenService);
        Single<DosenResp> resp = dosenRepository.doGetListDosen();

        TestObserver<DosenResp> testObserver = new TestObserver<>();
        resp.subscribe(testObserver);
        testObserver.awaitTerminalEvent();
        testObserver.assertComplete();
        testObserver.assertNoErrors();
        testObserver.values();
    }

    @Test
    public void T004_GetDosenImplementationTest() {

        DosenMapper dosenMapper = new DosenMapper();
        Scheduler scheduler = Schedulers.io();
        DosenService dosenService = ServiceGenerator.getDosenService();
        DosenRepositoryImpl dosenRepository = new DosenRepositoryImpl(dosenMapper,scheduler,dosenService);
        Single<Dosen> resp = dosenRepository.doGetDosen("D003");

        TestObserver<Dosen> testObserver = new TestObserver<>();
        resp.subscribe(testObserver);
        testObserver.awaitTerminalEvent();
        testObserver.assertComplete();
        testObserver.assertNoErrors();
        testObserver.values();
    }

    @Test
    public void T005_GetDosenListUseCaseTest(){
        DosenMapper dosenMapper = new DosenMapper();
        Scheduler scheduler = Schedulers.io();
        DosenService dosenService = ServiceGenerator.getDosenService();
        DosenRepositoryImpl dosenRepositoryImpl = new DosenRepositoryImpl(dosenMapper,scheduler,dosenService);

        GetDosenListUseCase dosenListUseCase = new GetDosenListUseCase(dosenRepositoryImpl);

        Single<DosenResp> resp = dosenListUseCase.execute();

        TestObserver<DosenResp> testObserver = new TestObserver<>();
        resp.subscribe(testObserver);
        testObserver.awaitTerminalEvent();
        testObserver.assertComplete();
        testObserver.assertNoErrors();
        Assert.assertEquals("00", testObserver.values().get(0).getErrorCode());
        //testObserver.assertEmpty();
    }

    @Test
    public void T006_GetDosenUseCaseTest(){
        DosenMapper dosenMapper = new DosenMapper();
        Scheduler scheduler = Schedulers.io();
        DosenService dosenService = ServiceGenerator.getDosenService();
        DosenRepositoryImpl dosenRepositoryImpl = new DosenRepositoryImpl(dosenMapper,scheduler,dosenService);

        GetDosenUseCase dosenUseCase = new GetDosenUseCase(dosenRepositoryImpl);

        Single<Dosen> resp = dosenUseCase.execute("D003");

        TestObserver<Dosen> testObserver = new TestObserver<>();
        resp.subscribe(testObserver);
        testObserver.awaitTerminalEvent();
        testObserver.assertComplete();
        testObserver.assertNoErrors();
        Assert.assertEquals("Angga", testObserver.values().get(0).getNama());
        //testObserver.assertEmpty();
    }
}
