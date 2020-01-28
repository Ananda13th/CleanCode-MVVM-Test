package example.com.cleancodetest.view.adapter;

import android.content.Context;
import android.util.Log;
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
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import example.com.cleancodetest.R;
import example.com.cleancodetest.databinding.ListItemBinding;
import example.com.cleancodetest.model.DosenModel;
import example.com.cleancodetest.view.ClickListener;

public class DosenAdapter extends RecyclerView.Adapter<DosenAdapter.MyAdapterViewHolder> implements ClickListener{

    private List<DosenModel> dosenList = new ArrayList<>();
    private ClickListener clickListener;

    public DosenAdapter() { }

    public void setDosenList( List<DosenModel> listDosen) {
        this.dosenList = listDosen;
        notifyDataSetChanged();
    }

    public void clickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }


    @Override
    public DosenAdapter.MyAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemBinding listItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.list_item, parent, false);
        return new MyAdapterViewHolder(listItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull DosenAdapter.MyAdapterViewHolder holder, final int position) {

        DosenModel dosen = dosenList.get(position);
        holder.listItemBinding.setDosen(dosen);
        holder.listItemBinding.setOnClick(this);
    }

    @Override
    public int getItemCount() {
        return (dosenList != null) ? dosenList.size() : 0;
    }



    public class MyAdapterViewHolder extends RecyclerView.ViewHolder {

        ListItemBinding listItemBinding;

        private MyAdapterViewHolder(ListItemBinding listItemBinding) {
            super(listItemBinding.getRoot());
            this.listItemBinding = listItemBinding;
        }
    }

    public void setOnClick(ClickListener listener) {

        this.clickListener = listener;
    }

    @Override
    public void onClickCardView(DosenModel dosen) {
        clickListener.onClickCardView(dosen);
    }

    @Override
    public void onCLickDeleteButton(String idDosen) {
        clickListener.onCLickDeleteButton(idDosen);

    }

    @Override
    public void onClickUpdateButton(String idDosen) {
        clickListener.onClickUpdateButton(idDosen);

    }

}