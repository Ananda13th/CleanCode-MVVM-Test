package example.com.cleancodetest.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import example.com.cleancodetest.model.DosenModel;
import example.com.cleancodetest.model.DosenRespModel;
import example.com.cleancodetest.model.mapper.DosenMapper;
import example.com.data.net.DosenService;
import example.com.data.net.ServiceGenerator;
import example.com.data.repository.DosenRepositoryImpl;
import example.com.domain.repository.DosenRepository;
import example.com.domain.usecase.dosen.GetDosenListUseCase;
import example.com.domain.usecase.dosen.GetDosenUseCase;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class DosenViewModel extends ViewModel {

    private example.com.data.entity.mapper.DosenMapper dosenMapper = new example.com.data.entity.mapper.DosenMapper();
    private Scheduler scheduler = Schedulers.io();
    private DosenService dosenService = ServiceGenerator.getDosenService();
    private DosenRepositoryImpl dosenRepositoryImpl = new DosenRepositoryImpl(dosenMapper,scheduler,dosenService);
    private GetDosenUseCase getDosenUseCase = new GetDosenUseCase(dosenRepositoryImpl);
    private MutableLiveData<DosenModel> dosen;
    public DosenMapper dosenMapperView;

    public LiveData<DosenModel> getDosen(String dosenId) {
        if(dosen==null) {
            dosen = new MutableLiveData<>();
            loadDosen(dosenId);
        }
        return dosen;
    }

    private void loadDosen(String idDosen) {
        getDosenUseCase.execute(idDosen)
                .map(dosenMapperView::dosenModelToView)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<DosenModel>() {
                    @Override
                    public void onSuccess(DosenModel dosenModel) {
                        dosen.setValue(dosenModel);
                    }
                    @Override
                    public void onError(Throwable e) {
                        e.getMessage();

                    }
                });
    }
}
