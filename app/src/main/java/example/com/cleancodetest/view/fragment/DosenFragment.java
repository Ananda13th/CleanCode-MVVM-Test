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
import example.com.cleancodetest.model.BaseResponseModel;
import example.com.cleancodetest.model.DosenModel;
import example.com.cleancodetest.model.DosenRespModel;
import example.com.cleancodetest.view.ClickListener;
import example.com.cleancodetest.view.adapter.DosenAdapter;
import example.com.cleancodetest.R;
import example.com.cleancodetest.viewmodel.DosenRespViewModel;

public class DosenFragment extends Fragment{

    private DosenAdapter adapter = new DosenAdapter();
    private DosenRespViewModel dosenRespViewModel;
    private DetailLayoutBinding detailLayoutBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        detailLayoutBinding = DataBindingUtil.inflate(this.getLayoutInflater(), R.layout.detail_layout, container, false);
        return inflater.inflate(R.layout.activity_main, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dosenRespViewModel = ViewModelProviders.of(requireActivity()).get(DosenRespViewModel.class);

        setDosenList();
        RecyclerView recyclerView = requireActivity().findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        final AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setView(detailLayoutBinding.getRoot());
        AlertDialog dialog = builder.create();
        adapter.setOnClick(new ClickListener() {
            @Override
            public void onClickCardView(DosenModel dosen) {
                detailLayoutBinding.setDosen(dosen);
                dialog.show();
            }

            @Override
            public void onCLickDeleteButton(String idDosen) {
                //dosenRespViewModel.deleteDosen(idDosen);
                deleteConfirmation(idDosen);
            }

            @Override
            public void onClickUpdateButton(String idDosen) {

            }
        });
        deleteDosenObserver();
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


    private void deleteConfirmation (final String id){
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
//
//    //Dialog Update Dosen
//    public void updateDialog ( final String id){
//        // Inisialisasi Alert Dialog
//        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//        builder.setTitle("Edit Dosen");
//        // Pemilihan Layout Yang Dipakai
//        final View customLayout = getLayoutInflater().inflate(R.layout.update_layout, null);
//        builder.setView(customLayout);
//        // Pemberian Tombol Opsi
//        builder.setPositiveButton("UPDATE", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                //Inisialisasi Elemen Pada Layout
//                EditText editNama = customLayout.findViewById(R.id.editNama);
//                EditText editPelajaran = customLayout.findViewById(R.id.editPelajaran);
//                Dosen dosen = new Dosen(editNama.getText().toString(), id, editPelajaran.getText().toString(), null);
//                updateDosen(id, dosen);
//            }
//        });
//        AlertDialog dialog = builder.create();
//        dialog.show();
//    }
//

//
//    //Update Data Dosen
//    public void updateDosen (String id, Dosen dosen){
//        //Ambil Method Dari ApiInterface
//        Call<Dosen> call = service.updateDosen(id, dosen);
//        call.enqueue(new Callback<Dosen>() {
//            @Override
//            public void onResponse(Call<Dosen> call, Response<Dosen> response) {
//                if (response.body()!= null && response.body().getErrorCode().equals("00")) {
//                    Toast.makeText(getActivity(), "Dosen Updated!", Toast.LENGTH_SHORT).show();
//                }
//                else
//                    Toast.makeText(getActivity(), "Salah pilih!", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<Dosen> call, Throwable t) {
//                Log.e("ERROR: ", t.getMessage());
//            }
//        });
//    }
//

}
