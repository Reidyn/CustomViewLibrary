package com.innovandoapps.library.customviewlibrary;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public abstract class AbstractCustomViewGroup extends FrameLayout {

    public AbstractCustomViewGroup(Context context) {
        super(context);
        init();
    }

    public AbstractCustomViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

    }

    protected void init(){
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService(infService);
        layoutInflater.inflate(getLayoutView(),this, true);
    }

    protected abstract int getLayoutView();
}