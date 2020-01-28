package example.com.cleancodetest.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import example.com.cleancodetest.model.DosenModel;
import example.com.cleancodetest.model.DosenRespModel;
import example.com.cleancodetest.model.mapper.DosenMapper;
import example.com.domain.repository.DosenRepository;
import example.com.domain.usecase.dosen.GetDosenUseCase;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class DosenViewModel extends ViewModel {


    private MutableLiveData<DosenModel> dosen;
    public DosenMapper dosenMapper;
    public GetDosenUseCase getDosenUseCase;

    public MutableLiveData<DosenModel> getDosen(String dosenId) {
        if(dosen==null) {
            dosen = new MutableLiveData<>();
            loadDosen(dosenId);
        }

        return dosen;
    }

    private void loadDosen(String idDosen) {
        getDosenUseCase.execute(idDosen)
                .map(dosenMapper::dosenModelToView)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<DosenModel>() {
                    @Override
                    public void onSuccess(DosenModel dosenModel) {

                        dosen.setValue(dosenModel);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
}
