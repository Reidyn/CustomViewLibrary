package com.innovandoapps.library.customviewlibrary.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.innovandoapps.library.customviewlibrary.CustomImagenView;
import com.innovandoapps.library.customviewlibrary.Listeners.OnAddGalleryAdapterListener;
import com.innovandoapps.library.customviewlibrary.Listeners.OnAddImagenListener;
import java.io.File;
import java.util.ArrayList;

public class GaleriaAdapter extends PagerAdapter {

    private Context context;
    private LinearLayout mContainer;
    private ArrayList<String> imagens;
    private OnAddGalleryAdapterListener onAddGalleryAdapterListener;
    private int inputtype;
    private ArrayList<Integer> origins;
    private int source = 0;

    public GaleriaAdapter(ArrayList<String> imagens,Context context, int inputtype,ArrayList<Integer> origins) {
        this.imagens = imagens;
        this.context = context;
        this.inputtype = inputtype;
        this.origins = origins;
    }

    public GaleriaAdapter(ArrayList<String> imagens,Context context, int inputtype,ArrayList<Integer> origins,int source) {
        this.imagens = imagens;
        this.context = context;
        this.inputtype = inputtype;
        this.origins = origins;
        this.source = source;
    }

    public void setOnAddGalleryAdapterListener(OnAddGalleryAdapterListener onAddGalleryAdapterListener){
        this.onAddGalleryAdapterListener = onAddGalleryAdapterListener;
    }

    @Override
    public int getCount() {
        if(inputtype == 0){
            return imagens.size() + 1 ;
        }else{
            return imagens.size();
        }
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout)object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager)container).removeView((LinearLayout)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        mContainer = new LinearLayout(context);
        mContainer.setOrientation(LinearLayout.VERTICAL);
        mContainer.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));

        if(position == (imagens.size())){
            CustomImagenView customImagenView = new CustomImagenView(context);
            LinearLayout.LayoutParams params = new  LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            customImagenView.setLayoutParams(params);
            customImagenView.setOnAddImagenListener(new OnAddImagenListener() {
                @Override
                public void OnAddImagen() {
                    if(inputtype == 0) {
                        onAddGalleryAdapterListener.OnAddGalleryAdapter();
                    }
                }
            });
            mContainer.addView(customImagenView);
        }else{
            ImageView iv = new ImageView(context);
            iv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            RequestOptions options = new RequestOptions();
            options.diskCacheStrategy(DiskCacheStrategy.ALL);
            options.centerCrop();
            if(origins != null && !origins.isEmpty()){
                if(origins.get(position) <= 1){
                    File file = new File(imagens.get(position));
                    Glide.with(context).load(file).apply(options).into(iv);
                }else{
                    Glide.with(context).load(imagens.get(position)).apply(options).into(iv);
                }
            }else{
                Glide.with(context).load(imagens.get(position)).apply(options).into(iv);
            }

            mContainer.addView(iv);
        }

        ((ViewPager)container).addView(mContainer, 0);
        return mContainer;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }
}
