package com.innovandoapps.library.customviewlibrary;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public abstract  class AbstractCustomLinearLayout extends LinearLayout {

    public AbstractCustomLinearLayout(Context context) {
        super(context);
        init();
    }

    public AbstractCustomLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    protected void init(){
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService(infService);
        layoutInflater.inflate(getLayoutView(),this, true);
        bindeViews();
    }

    protected abstract int getLayoutView();

    protected abstract void bindeViews();
}
