package example.com.cleancodetest.viewmodel;

import javax.inject.Inject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import example.com.cleancodetest.di.DaggerDosenComponent;
import example.com.cleancodetest.model.BaseResponseModel;
import example.com.cleancodetest.model.DosenModel;
import example.com.cleancodetest.model.DosenRespModel;
import example.com.cleancodetest.model.mapper.BaseResponseMapper;
import example.com.cleancodetest.model.mapper.DosenMapper;
import example.com.data.net.DosenService;
import example.com.data.net.ServiceGenerator;
import example.com.data.repository.BaseResponseRepositoryImpl;
import example.com.data.repository.DosenRepositoryImpl;
import example.com.domain.usecase.dosen.AddDosenUseCase;
import example.com.domain.usecase.dosen.DeleteDosenUseCase;
import example.com.domain.usecase.dosen.GetDosenListUseCase;
import example.com.domain.usecase.dosen.UpdateDosenUseCase;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


public class DosenRespViewModel extends ViewModel {

    //Mapper
//    private example.com.cleancodetest.model.mapper.DosenMapper dosenMapperToView = new DosenMapper();
//    private BaseResponseMapper baseResponseMapper = new BaseResponseMapper();
//    //Scheduler
//    private Scheduler scheduler = Schedulers.io();
//    //Service
//    private DosenService dosenService = ServiceGenerator.getDosenService();
//    //RepositoryImpl
//    private DosenRepositoryImpl dosenRepositoryImpl = new DosenRepositoryImpl(dosenMapper,scheduler,dosenService);
//    private BaseResponseRepositoryImpl baseResponseRepositoryImpl = new BaseResponseRepositoryImpl(dosenMapper,scheduler, dosenService);
//    //UseCase
//    private GetDosenListUseCase getDosenListUseCase = new GetDosenListUseCase(dosenRepositoryImpl);
//    private DeleteDosenUseCase deleteDosenUseCase = new DeleteDosenUseCase(baseResponseRepositoryImpl);
//    private AddDosenUseCase addDosenUseCase = new AddDosenUseCase(baseResponseRepositoryImpl);
//    private UpdateDosenUseCase updateDosenUseCase = new UpdateDosenUseCase(baseResponseRepositoryImpl);
    //MutableLiveData
    private MutableLiveData<DosenRespModel> dosenResp;
    private MutableLiveData<BaseResponseModel> baseResp;

    public DosenRespViewModel() {
        DaggerDosenComponent.create().inject(this);
    }

    @Inject
    public Scheduler scheduler;

    @Inject
    public DosenMapper dosenMapperToView;

    @Inject
    public BaseResponseMapper baseResponseMapper;

    @Inject
    public GetDosenListUseCase getDosenListUseCase;

    @Inject
    public UpdateDosenUseCase updateDosenUseCase;

    @Inject
    public AddDosenUseCase addDosenUseCase;

    @Inject
    public DeleteDosenUseCase deleteDosenUseCase;


    public LiveData<DosenRespModel> getRespDosen() {
        if(dosenResp==null) {
            dosenResp = new MutableLiveData<>();
            loadDosen();
        }
        return dosenResp;
    }

    public LiveData<BaseResponseModel> getBaseResponse() {
        if(baseResp==null) {
            baseResp = new MutableLiveData<>();
        }

        return baseResp;
    }

    private void loadDosen() {
        getDosenListUseCase.execute()
                .map(dosenMapperToView::dosenRespToView)
                .subscribeOn(scheduler)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<DosenRespModel>() {
                    @Override
                    public void onSuccess(DosenRespModel dosenRespModel) {
                        //Dari setValue memanggil onChanged di ViewModel
                        dosenResp.setValue(dosenRespModel);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    public void deleteDosen(String idDosen) {
        deleteDosenUseCase.execute(idDosen)
                .map(baseResponseMapper::baseResponseToView)
                .subscribeOn(scheduler)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<BaseResponseModel>() {
                    @Override
                    public void onSuccess(BaseResponseModel baseResponseModel) {
                        baseResp.setValue(baseResponseModel);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    public void addDosen(DosenModel dosenModel) {
        addDosenUseCase.execute(dosenMapperToView.dosenModelToDomain(dosenModel))
                .map(baseResponseMapper::baseResponseToView)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<BaseResponseModel>() {
                    @Override
                    public void onSuccess(BaseResponseModel baseResponseModel) {
                        baseResp.setValue(baseResponseModel);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                });
    }

    public void UpdateDosen(DosenModel dosenModel) {
        updateDosenUseCase.execute(dosenMapperToView.dosenModelToDomain(dosenModel))
                .map(baseResponseMapper::baseResponseToView)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<BaseResponseModel>() {
                    @Override
                    public void onSuccess(BaseResponseModel baseResponseModel) {
                        baseResp.setValue(baseResponseModel);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

}
