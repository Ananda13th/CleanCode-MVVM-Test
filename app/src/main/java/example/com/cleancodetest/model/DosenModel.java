package example.com.cleancodetest.model;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

import androidx.databinding.BindingAdapter;
import lombok.Data;

@Data
public class DosenModel {
    private String nama;
    private String id;
    private String pelajaran;
    private String foto;

    @BindingAdapter("loadFotoDosen")
    public  static void loadImage(ImageView imageView, String imageUrl) {
        Glide.with(imageView.getContext())
                .load(imageUrl)
                .into(imageView);
    }
}
