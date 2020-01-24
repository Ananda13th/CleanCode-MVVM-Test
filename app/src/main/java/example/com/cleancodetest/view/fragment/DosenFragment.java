package example.com.cleancodetest.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import example.com.cleancodetest.model.DosenModel;
import example.com.cleancodetest.model.DosenRespModel;
import example.com.cleancodetest.view.ClickListener;
import example.com.cleancodetest.view.adapter.DosenAdapter;
import example.com.cleancodetest.R;
import example.com.cleancodetest.viewmodel.DosenRespViewModel;
import example.com.cleancodetest.viewmodel.DosenViewModel;

public class DosenFragment extends Fragment {

    private DosenAdapter adapter = new DosenAdapter();
    private DosenRespViewModel dosenRespViewModel;
    private DosenViewModel dosenViewModel;
    private DosenModel dosenModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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
        adapter.setOnClick(new ClickListener() {
            @Override
            public void onClickCardView(int position) {
                dosenModel = dosenRespViewModel.getRespDosen().getValue().getDosenList().get(position);
                detailDialog(dosenModel);
            }

            @Override
            public void onCLickDeleteButton(int position) {
                dosenModel = dosenRespViewModel.getRespDosen().getValue().getDosenList().get(position);
                Toast.makeText(requireActivity(), "Diklik "+ dosenModel, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onClickUpdateButton(int position) {
                dosenModel = dosenRespViewModel.getRespDosen().getValue().getDosenList().get(position);
                Toast.makeText(requireActivity(), "Diklik "+ dosenModel, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setDosenList() {
        dosenRespViewModel.getRespDosen().observe(requireActivity(), new Observer<DosenRespModel>() {
            @Override
            public void onChanged(DosenRespModel dosenRespModel) {
                adapter.setDosenList(dosenRespModel.getDosenList());
            }
        });
    }

    private void detailDialog (DosenModel dosenModel){
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        final View customLayout = getLayoutInflater().inflate(R.layout.detail_layout, null);
        builder.setView(customLayout);
        TextView view_nama = customLayout.findViewById(R.id.view_nama);
        TextView view_pelajaran = customLayout.findViewById(R.id.view_pelajaran);
        TextView view_id = customLayout.findViewById(R.id.view_id);
        ImageView view_foto = customLayout.findViewById(R.id.view_foto);
        view_nama.setText(dosenModel.getNama());
        view_id.setText(dosenModel.getId());
        view_pelajaran.setText(dosenModel.getPelajaran());
        Glide.with(requireActivity()).load(dosenModel.getFoto())
                .into(view_foto);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
//
//    //Alert Dialog Hapus Dosen
//    private AlertDialog deleteConfirmation ( final String id){
//        AlertDialog deleteDialog = new AlertDialog.Builder(getActivity())
//            // set message, title, and icon
//            .setTitle("Delete")
//            .setMessage("Do you want to Delete")
//            .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
//
//                public void onClick(DialogInterface dialog, int whichButton) {
//                    deleteDosen(id);
//                }
//            })
//            .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int which) {
//                    dialog.dismiss();
//                }
//            })
//            .create();
//        return deleteDialog;
//    }
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
//    //Delete Dosen
//    public void deleteDosen (String id){
//        //Ambil Method Dari ApiInterface
//        Call<BaseResponse> call = service.deleteDosen(id);
//        call.enqueue(new Callback<BaseResponse>() {
//            @Override
//            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
//                if (response.body() != null) {
//                    if (response.body().getErrorCode().equals("00"))
//                        Toast.makeText(getActivity(), "Dosen deleted!", Toast.LENGTH_SHORT).show();
//                } else
//                    Toast.makeText(getActivity(), "Salah pilih!", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<BaseResponse> call, Throwable t) {
//                Log.d("ERROR", t.getMessage());
//            }
//        });
//    }
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
