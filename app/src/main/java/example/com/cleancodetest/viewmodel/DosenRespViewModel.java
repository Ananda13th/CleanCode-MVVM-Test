package example.com.cleancodetest.viewmodel;

import android.util.Log;

import com.google.gson.Gson;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import example.com.cleancodetest.model.DosenRespModel;
import example.com.cleancodetest.model.mapper.DosenMapper;
import example.com.data.net.DosenService;
import example.com.data.net.ServiceGenerator;
import example.com.data.repository.DosenRepositoryImpl;
import example.com.domain.repository.DosenRepository;
import example.com.domain.usecase.dosen.GetDosenListUseCase;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


public class DosenRespViewModel extends ViewModel {
    private example.com.data.entity.mapper.DosenMapper dosenMapper = new example.com.data.entity.mapper.DosenMapper();
    private Scheduler scheduler = Schedulers.io();
    private DosenService dosenService = ServiceGenerator.getDosenService();
    private DosenRepositoryImpl dosenRepositoryImpl = new DosenRepositoryImpl(dosenMapper,scheduler,dosenService);
    private GetDosenListUseCase getDosenListUseCase = new GetDosenListUseCase(dosenRepositoryImpl);
    private example.com.cleancodetest.model.mapper.DosenMapper dosenMapperToView = new DosenMapper();
    private MutableLiveData<DosenRespModel> dosenResp;


    public LiveData<DosenRespModel> getRespDosen() {
        if(dosenResp==null) {
            dosenResp = new MutableLiveData<>();
            loadDosen();
        }
        return dosenResp;
    }

    private void loadDosen() {
        getDosenListUseCase.execute()
                .map(dosenMapperToView::dosenRespToView)
                .subscribeOn(scheduler)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<DosenRespModel>() {
                    @Override
                    public void onSuccess(DosenRespModel dosenRespModel) {
                        dosenResp.setValue(dosenRespModel);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

}
