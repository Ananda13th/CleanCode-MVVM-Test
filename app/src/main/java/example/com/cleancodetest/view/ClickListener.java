package example.com.cleancodetest.view;

import example.com.cleancodetest.model.DosenModel;

public interface ClickListener {

    void onClickCardView(DosenModel dosen);

    void onCLickDeleteButton(String idDosen);

    void onClickUpdateButton(String idDosen);
}

