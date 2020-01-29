package example.com.data;

import org.junit.Assert;
import org.junit.Test;

import example.com.data.entity.BaseResponseEntity;
import example.com.data.entity.DosenEntity;
import example.com.data.entity.DosenRespEntity;
import example.com.data.entity.mapper.DosenMapper;
import example.com.data.net.DosenService;
import example.com.data.net.ServiceGenerator;
import example.com.data.repository.BaseResponseRepositoryImpl;
import example.com.data.repository.DosenRepositoryImpl;
import example.com.domain.model.BaseResponse;
import example.com.domain.model.Dosen;
import example.com.domain.model.DosenResp;
import example.com.domain.usecase.dosen.AddDosenUseCase;
import example.com.domain.usecase.dosen.DeleteDosenUseCase;
import example.com.domain.usecase.dosen.GetDosenListUseCase;
import example.com.domain.usecase.dosen.GetDosenUseCase;
import example.com.domain.usecase.dosen.UpdateDosenUseCase;
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
    public void T003_DeleteDosenIntergrationTest() {
        DosenService dosenService = ServiceGenerator.getDosenService();
        Single<BaseResponseEntity> resp = dosenService.deleteDosen("D002");

        TestObserver<BaseResponseEntity> testObserver = new TestObserver<>();
        resp.subscribe(testObserver);
        testObserver.assertComplete();
        testObserver.assertNoErrors();
        testObserver.values();
    }

    @Test
    public void T004_AddDosenIntergrationTest() {
        DosenService dosenService = ServiceGenerator.getDosenService();
        DosenEntity dosenEntity = new DosenEntity();
        dosenEntity.setPelajaran("BK");
        dosenEntity.setNama("Beni");
        dosenEntity.setFoto("www.pixiv.com");
        dosenEntity.setId("D009");
        Single<BaseResponseEntity> resp = dosenService.addDosen(dosenEntity);


        TestObserver<BaseResponseEntity> testObserver = new TestObserver<>();
        resp.subscribe(testObserver);
        testObserver.assertComplete();
        testObserver.assertNoErrors();
    }

    @Test
    public void T005_UpdateDosenIntergrationTest() {
        DosenService dosenService = ServiceGenerator.getDosenService();
        DosenEntity dosenEntity = new DosenEntity();
        String id = "D001";
        Single<BaseResponseEntity> resp = dosenService.updateDosen(id, dosenEntity);
        TestObserver<BaseResponseEntity> testObserver = new TestObserver<>();
        resp.subscribe(testObserver);
        testObserver.assertComplete();
        testObserver.assertNoErrors();

    }

    @Test
    public void T006_GetDosenListImplementationTest() {

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
    public void T007_GetDosenImplementationTest() {

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
    public void T008_DeleteDosenImplementationTest() {
        DosenMapper dosenMapper = new DosenMapper();
        Scheduler scheduler = Schedulers.io();
        DosenService dosenService = ServiceGenerator.getDosenService();
        BaseResponseRepositoryImpl baseResponseRepository = new BaseResponseRepositoryImpl(dosenMapper,scheduler,dosenService);

        Single<BaseResponse> resp = baseResponseRepository.doDeleteDosen("D002");

        TestObserver<BaseResponse> testObserver = new TestObserver<>();
        resp.subscribe(testObserver);
        testObserver.awaitTerminalEvent();
        testObserver.assertComplete();
        testObserver.assertNoErrors();
        testObserver.values();
    }

    @Test
    public void T009_AddDosenImplementationTest() {
        DosenMapper dosenMapper = new DosenMapper();
        Dosen dosen = new Dosen();
        Scheduler scheduler = Schedulers.io();
        DosenService dosenService = ServiceGenerator.getDosenService();
        BaseResponseRepositoryImpl baseResponseRepository = new BaseResponseRepositoryImpl(dosenMapper,scheduler,dosenService);

        dosen.setPelajaran("BK");
        dosen.setNama("Beni");
        dosen.setFoto("www.pixiv.com");
        dosen.setId("D009");

        Single<BaseResponse> resp = baseResponseRepository.doAddDosen(dosen);
        TestObserver<BaseResponse> testObserver = new TestObserver<>();
        resp.subscribe(testObserver);
        testObserver.awaitTerminalEvent();
        testObserver.assertComplete();
        testObserver.assertNoErrors();
        testObserver.values();
    }

    @Test
    public void T010_UpdateDosenImplementationTest() {
        DosenMapper dosenMapper = new DosenMapper();
        Scheduler scheduler = Schedulers.io();
        DosenService dosenService = ServiceGenerator.getDosenService();
        BaseResponseRepositoryImpl baseResponseRepository = new BaseResponseRepositoryImpl(dosenMapper,scheduler,dosenService);

        Single<BaseResponse> resp = baseResponseRepository.doDeleteDosen("D002");

        TestObserver<BaseResponse> testObserver = new TestObserver<>();
        resp.subscribe(testObserver);
        testObserver.awaitTerminalEvent();
        testObserver.assertComplete();
        testObserver.assertNoErrors();
        testObserver.values();
    }

    @Test
    public void T011_GetDosenListUseCaseTest(){
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
    public void T012_GetDosenUseCaseTest(){
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

    @Test
    public void T013_DeleteDosenUseCaseTest(){
        DosenMapper dosenMapper = new DosenMapper();
        Scheduler scheduler = Schedulers.io();
        DosenService dosenService = ServiceGenerator.getDosenService();
        BaseResponseRepositoryImpl baseResponseRepository = new BaseResponseRepositoryImpl(dosenMapper,scheduler,dosenService);

        DeleteDosenUseCase dosenUseCase = new DeleteDosenUseCase(baseResponseRepository);

        Single<BaseResponse> resp = dosenUseCase.execute("D002");

        TestObserver<BaseResponse> testObserver = new TestObserver<>();
        resp.subscribe(testObserver);
        testObserver.awaitTerminalEvent();
        testObserver.assertComplete();
        testObserver.assertNoErrors();
    }

    @Test
    public void T014_AddDosenUseCaseTest(){
        DosenMapper dosenMapper = new DosenMapper();
        Dosen dosen = new Dosen();
        Scheduler scheduler = Schedulers.io();
        DosenService dosenService = ServiceGenerator.getDosenService();
        BaseResponseRepositoryImpl baseResponseRepository = new BaseResponseRepositoryImpl(dosenMapper,scheduler,dosenService);

        AddDosenUseCase dosenUseCase = new AddDosenUseCase(baseResponseRepository);

        dosen.setPelajaran("BK");
        dosen.setNama("Beni");
        dosen.setFoto("www.pixiv.com");
        dosen.setId("D009");

        Single<BaseResponse> resp = dosenUseCase.execute(dosen);
        TestObserver<BaseResponse> testObserver = new TestObserver<>();
        resp.subscribe(testObserver);
        testObserver.awaitTerminalEvent();
        testObserver.assertComplete();
        testObserver.assertNoErrors();
    }

    @Test
    public void T015_UpdateDosenUseCaseTest(){
        DosenMapper dosenMapper = new DosenMapper();
        Dosen dosen = new Dosen();
        Scheduler scheduler = Schedulers.io();
        DosenService dosenService = ServiceGenerator.getDosenService();
        BaseResponseRepositoryImpl baseResponseRepository = new BaseResponseRepositoryImpl(dosenMapper,scheduler,dosenService);

        UpdateDosenUseCase dosenUseCase = new UpdateDosenUseCase(baseResponseRepository);

        dosen.setId("D001");

        Single<BaseResponse> resp = dosenUseCase.execute(dosen);
        TestObserver<BaseResponse> testObserver = new TestObserver<>();
        resp.subscribe(testObserver);
        testObserver.awaitTerminalEvent();
        testObserver.assertComplete();
        testObserver.assertNoErrors();
    }
}
