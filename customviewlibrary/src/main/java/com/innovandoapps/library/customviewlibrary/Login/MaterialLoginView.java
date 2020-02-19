package com.innovandoapps.library.customviewlibrary.Login;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.innovandoapps.library.customviewlibrary.AbstractCustomViewGroup;
import com.innovandoapps.library.customviewlibrary.R;

public class MaterialLoginView extends AbstractCustomViewGroup implements Login{


    public MaterialLoginView(Context context) {
        super(context);
        inicializar();
    }

    public MaterialLoginView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inicializar();
    }

    private void inicializar(){
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService(infService);
        layoutInflater.inflate(getLayoutView(),this, true);
    }

    @Override
    protected int getLayoutView() {
        return R.layout.material_login;
    }

    @Override
    public void loguearse(String usuario, String password) {

    }

    @Override
    public void verPassword() {

    }

    @Override
    public void olvidePassword() {

    }
}
