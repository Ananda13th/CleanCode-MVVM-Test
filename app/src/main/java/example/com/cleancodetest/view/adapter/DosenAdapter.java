package example.com.cleancodetest.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import example.com.cleancodetest.R;
import example.com.cleancodetest.model.DosenModel;
import example.com.cleancodetest.view.ClickListener;

public class DosenAdapter extends RecyclerView.Adapter<DosenAdapter.MyAdapterViewHolder> {

    private List<DosenModel> dosenList = new ArrayList<>();
    private ClickListener clickListener;
    private Context context;

    public DosenAdapter() { }

    public void setDosenList( List<DosenModel> listDosen) {
        this.dosenList = listDosen;
        //Refresh Data Di RecycleView
        //Bila Tidak Pakai Ini, RecycleView Kebuat Dulu,, Baru Data Masuk
        //RecycleView Harus Refresh Biar Tampil Data
        notifyDataSetChanged();
    }


    @Override
    public DosenAdapter.MyAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item, parent, false);
        context = parent.getContext();
        return new MyAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DosenAdapter.MyAdapterViewHolder holder, final int position) {
        //final DosenModel responseDosen = dosenList.get(position);
        holder.txtNama.setText(dosenList.get(position).getNama());
        holder.txtId.setText(dosenList.get(position).getId());
        holder.txtPelajaran.setText(dosenList.get(position).getPelajaran());
        Glide.with(this.context)
                .load(dosenList.get(position).getFoto())
                .into(holder.image);
        holder.cardView.setOnClickListener(view -> clickListener.onClickCardView(position));

        holder.btnDelete.setOnClickListener(view -> clickListener.onCLickDeleteButton(position));

        holder.btnUpdate.setOnClickListener(view -> clickListener.onClickUpdateButton(position));
    }

    @Override
    public int getItemCount() {
        return (dosenList != null) ? dosenList.size() : 0;
    }

    public class MyAdapterViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNama, txtId, txtPelajaran;
        private CardView cardView;
        private ImageView image;
        private Button btnDelete;
        private Button btnUpdate;

        private MyAdapterViewHolder(View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.txt_id);
            txtNama = itemView.findViewById(R.id.txt_nama);
            txtPelajaran = itemView.findViewById(R.id.txt_pelajaran);
            btnDelete = itemView.findViewById(R.id.button_delete);
            btnUpdate = itemView.findViewById(R.id.button_update);
            cardView = itemView.findViewById(R.id.card_view_dosen);
            image = itemView.findViewById(R.id.image_view);
        }
    }

    public void setOnClick(ClickListener listener) {

        this.clickListener = listener;
    }

}