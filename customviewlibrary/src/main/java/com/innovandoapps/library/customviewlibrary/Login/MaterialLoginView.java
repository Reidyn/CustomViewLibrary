package com.innovandoapps.library.customviewlibrary.Login;

import android.content.Context;
import android.graphics.Paint;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import com.google.android.material.textfield.TextInputEditText;
import com.innovandoapps.library.customviewlibrary.AbstractCustomLinearLayout;
import com.innovandoapps.library.customviewlibrary.Listeners.OnClickLoginListener;
import com.innovandoapps.library.customviewlibrary.Listeners.OnClickLostPasswordListener;
import com.innovandoapps.library.customviewlibrary.R;
import com.mindorks.editdrawabletext.DrawablePosition;
import com.mindorks.editdrawabletext.EditDrawableText;

public class MaterialLoginView extends AbstractCustomLinearLayout implements Login{

    private TextInputEditText edtUsuario;
    private EditDrawableText edtPassword;
    private AppCompatTextView txtRestarPassword;
    private Button btnIngresar;
    private boolean showPassword = false;

    private OnClickLoginListener onClickLoginListener;
    private OnClickLostPasswordListener onClickLostPasswordListener;

    public MaterialLoginView(Context context) {
        super(context);
    }

    public MaterialLoginView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int getLayoutView() {
        return R.layout.material_login;
    }

    @Override
    protected void bindeViews() {
        edtUsuario = (TextInputEditText)findViewById(R.id.edtUsuario);

        edtPassword = (EditDrawableText)findViewById(R.id.edtPassword);
        edtPassword.setDrawableClickListener(new com.mindorks.editdrawabletext.onDrawableClickListener(){
            @Override
            public void onClick(DrawablePosition drawablePosition) {
                if(drawablePosition.equals(DrawablePosition.RIGHT)){
                    verPassword();
                }
            }
        });

        txtRestarPassword = (AppCompatTextView)findViewById(R.id.txtRestarPassword);
        txtRestarPassword.setPaintFlags(txtRestarPassword.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        txtRestarPassword.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                olvidePassword();
            }
        });

        btnIngresar = (Button)findViewById(R.id.btnIngresar);
        btnIngresar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                loguearse(edtUsuario.getText().toString(),edtPassword.getText().toString());
            }
        });
    }

    @Override
    public void loguearse(String usuario, String password) {
        if(onClickLoginListener != null){
            if(validar()){
                onClickLoginListener.OnClickLogin(usuario,password);
            }
        }
    }

    @Override
    public void verPassword() {
        if(showPassword){
            edtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            showPassword = false;
        }else{
            edtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            showPassword = true;
        }
    }

    @Override
    public void olvidePassword() {
        if(onClickLostPasswordListener != null){
            onClickLostPasswordListener.OnClickLostPassword();
        }
    }

    private boolean validar(){
        boolean result = true;
        if(edtUsuario.getText().toString().equals("")){
            edtUsuario.setError(getContext().getString(R.string.lbl_text_vacio));
            edtUsuario.requestFocus();
            result = false;
        }

        if(edtPassword.getText().toString().equals("")){
            edtPassword.setError(getContext().getString(R.string.lbl_pass_vacio));
            edtPassword.requestFocus();
            return false;
        }

        return result;
    }
}