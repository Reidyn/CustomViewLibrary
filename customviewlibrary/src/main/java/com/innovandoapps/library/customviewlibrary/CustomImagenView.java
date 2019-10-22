package com.innovandoapps.library.customviewlibrary;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.bumptech.glide.Glide;
import com.innovandoapps.library.customviewlibrary.Listeners.OnAddImagenListener;

/**
 * Created by desarrollo on 09/01/18.
 */

public class CustomImagenView extends LinearLayout {

    private ImageView btn_customImg;
    private Context context;
    private OnAddImagenListener onAddImagenListener;

    public CustomImagenView(Context context) {
        super(context);
        this.context = context;
        inicializar();
    }

    public CustomImagenView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        inicializar();
    }

    public void setOnAddImagenListener(OnAddImagenListener onAddImagenListener){
        this.onAddImagenListener = onAddImagenListener;
    }

    private void inicializar() {
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        li.inflate(R.layout.custom_image_view, this, true);

        btn_customImg = (ImageView)findViewById(R.id.btn_customImg);
        Glide.with(context)
                .load(R.drawable.camera)
                .into(btn_customImg);
        asignarEventos();
    }

    private void asignarEventos(){
        btn_customImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddImagenListener.OnAddImagen();
            }
        });
    }
}
