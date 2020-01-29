package example.com.cleancodetest.view.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import example.com.cleancodetest.databinding.DetailLayoutBinding;
import example.com.cleancodetest.databinding.UpdateLayoutBinding;
import example.com.cleancodetest.model.BaseResponseModel;
import example.com.cleancodetest.model.DosenModel;
import example.com.cleancodetest.model.DosenRespModel;
import example.com.cleancodetest.view.ClickListener;
import example.com.cleancodetest.view.UpdateClickListener;
import example.com.cleancodetest.view.adapter.DosenAdapter;
import example.com.cleancodetest.R;
import example.com.cleancodetest.viewmodel.DosenRespViewModel;

public class DosenFragment extends Fragment implements UpdateClickListener {

    private DosenAdapter adapter = new DosenAdapter();
    private DosenRespViewModel dosenRespViewModel;
    private DetailLayoutBinding detailLayoutBinding;
    private UpdateLayoutBinding updateLayoutBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        detailLayoutBinding = DataBindingUtil.inflate(this.getLayoutInflater(), R.layout.detail_layout, container, false);
        updateLayoutBinding = DataBindingUtil.inflate(this.getLayoutInflater(), R.layout.update_layout, container, false);
        return inflater.inflate(R.layout.fragment_dosen, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dosenRespViewModel = ViewModelProviders.of(requireActivity()).get(DosenRespViewModel.class);
        setDosenList();
        RecyclerView recyclerView = requireActivity().findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        //Dialog Untuk Detail
        final AlertDialog.Builder detailBuilder = new AlertDialog.Builder(requireActivity());
        detailBuilder.setView(detailLayoutBinding.getRoot());
        AlertDialog detailDialog = detailBuilder.create();
        //Dialog Untuk Update
        updateLayoutBinding.setOnClick(this);
        final AlertDialog.Builder updateBuilder = new AlertDialog.Builder(requireActivity());
        updateBuilder.setView(updateLayoutBinding.getRoot());
        AlertDialog updateDialog = updateBuilder.create();
        updateDialog.setTitle("UPDATE DOSEN");
        adapter.setOnClick(new ClickListener() {
            @Override
            public void onClickCardView(DosenModel dosen) {
                detailLayoutBinding.setDosen(dosen);
                detailDialog.show();
            }

            @Override
            public void onCLickDeleteButton(String idDosen) {
                deleteConfirmation(idDosen);
                deleteDosenObserver();
            }

            @Override
            public void onClickUpdateButton(DosenModel dosen) {
                updateLayoutBinding.setDosen(dosen);
                updateDialog.show();

            }
        });

    }

    private void setDosenList() {
        dosenRespViewModel.getRespDosen().observe(requireActivity(), new Observer<DosenRespModel>() {
            @Override
            public void onChanged(DosenRespModel dosenRespModel) {
                adapter.setDosenList(dosenRespModel.getDosenList());
            }
        });
    }

    private void deleteDosenObserver() {
        dosenRespViewModel.getBaseResponse().observe(requireActivity(), new Observer<BaseResponseModel>() {
            @Override
            public void onChanged(BaseResponseModel baseResponseModel) {
                Toast.makeText(getActivity(), baseResponseModel.getErrorMesssage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void deleteConfirmation(final String id) {
        AlertDialog deleteDialog = new AlertDialog.Builder(requireActivity())
                .setTitle("Delete")
                .setMessage("Do you want to Delete")
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        dosenRespViewModel.deleteDosen(id);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        deleteDialog.show();
    }

    @Override
    public void onClickUpdateButton(DosenModel dosen) {
        Log.d("Dosen", dosen.toString());
        dosenRespViewModel.UpdateDosen(dosen);
        updateDosenObserver();
    }

    private void updateDosenObserver() {
        dosenRespViewModel.getBaseResponse().observe(requireActivity(), new Observer<BaseResponseModel>() {
            @Override
            public void onChanged(BaseResponseModel baseResponseModel) {
                Toast.makeText(getActivity(), baseResponseModel.getErrorMesssage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
