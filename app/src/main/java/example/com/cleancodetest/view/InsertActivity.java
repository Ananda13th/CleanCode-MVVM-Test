package example.com.cleancodetest.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import example.com.cleancodetest.R;
import example.com.cleancodetest.databinding.ActivityInsertBinding;
import example.com.cleancodetest.model.BaseResponseModel;
import example.com.cleancodetest.model.DosenModel;
import example.com.cleancodetest.viewmodel.DosenRespViewModel;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity implements InsertClickListener{


    private DosenRespViewModel dosenRespViewModel = new DosenRespViewModel();
    private ActivityInsertBinding insertBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        insertBinding = DataBindingUtil.setContentView(this, R.layout.activity_insert);
        insertBinding.setClickListener(this);
        insertBinding.setDosen(new DosenModel());
        dosenRespViewModel = ViewModelProviders.of(this).get(DosenRespViewModel.class);
    }

    public  void addDosenObserver(DosenModel dosen) {
        dosenRespViewModel.getBaseResponse().observe(this, new Observer<BaseResponseModel>() {
            @Override
            public void onChanged(BaseResponseModel baseResponseModel) {
                Toast.makeText(InsertActivity.this, baseResponseModel.getErrorMesssage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClickInsertButton(DosenModel dosen) {
        insertBinding.getDosen();
        //Log.d("Dosen", dosen.getNama());
        dosenRespViewModel.addDosen(dosen);
        addDosenObserver(dosen);
    }
}
