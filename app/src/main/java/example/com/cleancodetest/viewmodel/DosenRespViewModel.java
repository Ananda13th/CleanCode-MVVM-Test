package example.com.cleancodetest.viewmodel;

import android.util.Log;

import com.google.gson.Gson;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import example.com.cleancodetest.model.BaseResponseModel;
import example.com.cleancodetest.model.DosenRespModel;
import example.com.cleancodetest.model.mapper.BaseResponseMapper;
import example.com.cleancodetest.model.mapper.DosenMapper;
import example.com.data.net.DosenService;
import example.com.data.net.ServiceGenerator;
import example.com.data.repository.BaseResponseRepositoryImpl;
import example.com.data.repository.DosenRepositoryImpl;
import example.com.domain.usecase.dosen.DeleteDosenUseCase;
import example.com.domain.usecase.dosen.GetDosenListUseCase;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


public class DosenRespViewModel extends ViewModel {

    //Mapper
    private example.com.data.entity.mapper.DosenMapper dosenMapper = new example.com.data.entity.mapper.DosenMapper();
    private example.com.cleancodetest.model.mapper.DosenMapper dosenMapperToView = new DosenMapper();
    private BaseResponseMapper baseResponseMapper = new BaseResponseMapper();
    //Scheduler
    private Scheduler scheduler = Schedulers.io();
    //Service
    private DosenService dosenService = ServiceGenerator.getDosenService();
    //RepositoryImpl
    private DosenRepositoryImpl dosenRepositoryImpl = new DosenRepositoryImpl(dosenMapper,scheduler,dosenService);
    private BaseResponseRepositoryImpl baseResponseRepositoryImpl = new BaseResponseRepositoryImpl(new example.com.data.entity.mapper.BaseResponseMapper(),scheduler, dosenService);
    //UseCase
    private GetDosenListUseCase getDosenListUseCase = new GetDosenListUseCase(dosenRepositoryImpl);
    private DeleteDosenUseCase deleteDosenUseCase = new DeleteDosenUseCase(baseResponseRepositoryImpl);
    //MutableLiveData
    private MutableLiveData<DosenRespModel> dosenResp;
    private MutableLiveData<BaseResponseModel> baseResp;


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

}
