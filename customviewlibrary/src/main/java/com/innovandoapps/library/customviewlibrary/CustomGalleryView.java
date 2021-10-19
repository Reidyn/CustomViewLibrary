package com.innovandoapps.library.customviewlibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.viewpager.widget.ViewPager;
import com.innovandoapps.library.customviewlibrary.Listeners.OnAddGalleryAdapterListener;
import com.innovandoapps.library.customviewlibrary.Listeners.OnAddImgGalleryListener;
import com.innovandoapps.library.customviewlibrary.adapter.GaleriaAdapter;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import me.relex.circleindicator.CircleIndicator;

public class CustomGalleryView extends LinearLayout {

    private ViewPager pager;
    private CircleIndicator indicator;
    private ArrayList<String> imagenes;
    private ArrayList<Integer> origins;
    private GaleriaAdapter galeriaAdapter;
    private OnAddImgGalleryListener onAddImgGalleryListener;
    private Button btnborrar;
    protected int inputtype;
    protected int source;

    public CustomGalleryView(Context context) {
        super(context);
        init();
    }

    public CustomGalleryView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomGalleryView, 0, 0);
        try {
            inputtype = a.getInteger(R.styleable.CustomGalleryView_inputtype_view, 0);
            source = a.getInteger(R.styleable.CustomGalleryView_source_view, 0);
        }finally {
            a.recycle();
        }
        init();
    }

    public void setOnAddImgGalleryListener(OnAddImgGalleryListener onAddImgGalleryListener){
        this.onAddImgGalleryListener = onAddImgGalleryListener;
    }

    private void init(){
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService(infService);
        layoutInflater.inflate(R.layout.view_imagen,this, true);

        btnborrar = (Button)findViewById(R.id.btnborrar);
        pager = (ViewPager)findViewById(R.id.viewpager1);
        indicator = (CircleIndicator) findViewById(R.id.indicator);
        imagenes = new ArrayList<>();
        origins = new ArrayList<>();
        cargarDatos();

        if(inputtype == 0){
            btnborrar.setVisibility(VISIBLE);
        }else{
            btnborrar.setVisibility(GONE);
        }

        btnborrar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = pager.getCurrentItem();
                if(origins.get(position) == 1){
                    File file = new File(imagenes.get(position));
                    if(file.exists()){
                        file.delete();
                    }
                }
                imagenes.remove(position);
                cargarDatos();
            }
        });

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
            @Override
            public void onPageSelected(int position) {
                if (position == (pager.getAdapter().getCount() - 1)) {
                    btnborrar.setVisibility(GONE);
                }else{
                    if(inputtype == 0){
                        btnborrar.setVisibility(VISIBLE);
                    }else{
                        btnborrar.setVisibility(GONE);
                    }
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {}
        });
    }

    public void setAddImgGallery(String pathimg,int origin){
        imagenes.add(pathimg);
        origins.add(origin);
        cargarDatos();
    }

    public void setAddImgGallery(String pathimg){
        imagenes.add(pathimg);
        cargarDatos();
    }

    public void setArrImagenes(ArrayList<String> imagenes){
        this.imagenes = imagenes;
        cargarDatos();
    }

    public void setArrImagenes(ArrayList<String> imagenes,int source){
        this.imagenes = imagenes;
        this.source = source;
        cargarDatos();
    }

    private void cargarDatos(){
        /*if(imagenes.isEmpty()){
            btnborrar.setVisibility(GONE);
        }else{
            if(inputtype == 0){
                btnborrar.setVisibility(VISIBLE);
            }else{
                btnborrar.setVisibility(GONE);
            }
        }*/

        galeriaAdapter = new GaleriaAdapter(imagenes,getContext(),inputtype,origins,source);
        galeriaAdapter.setOnAddGalleryAdapterListener(new OnAddGalleryAdapterListener() {
            @Override
            public void OnAddGalleryAdapter() {
                onAddImgGalleryListener.OnAddImgGallery();
            }
        });
        pager.setAdapter(galeriaAdapter);
        indicator.setViewPager(pager);
    }

    public List<String> getListImg(){
        return imagenes;
    }

    public List<Integer> getListOriginImg(){return origins;}
}