package com.innovandoapps.library.customviewlibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.innovandoapps.library.customviewlibrary.Listeners.OnClickLoginListener;
import com.innovandoapps.library.customviewlibrary.Listeners.OnClickLostPasswordListener;
import com.innovandoapps.library.customviewlibrary.utils.ValidadorInput;

public class SimpleLoginView extends LinearLayout {

    private TextInputLayout TxtInputUser;
    private TextInputEditText edtUser;
    private TextInputLayout txtInputPass;
    private TextInputEditText edtPassword;
    private CheckBox chVerPass;
    private TextView lblolvpass;
    private Button btnIngresar;

    private OnClickLoginListener onClickLoginListener;
    private OnClickLostPasswordListener onClickLostPasswordListener;

    private boolean mshowRestartPass = true;
    private String stringLabelUser;
    private String stringLabelPass;
    private String stringLabelBtnAccess;
    private int inputtype;

    public SimpleLoginView(Context context) {
        super(context);
        inicializar();
    }

    public SimpleLoginView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SimpleLoginView, 0, 0);
        try {
            mshowRestartPass = a.getBoolean(R.styleable.SimpleLoginView_showRestartPass1, true);
            stringLabelUser = a.getString(R.styleable.SimpleLoginView_stringLabelUser1);
            stringLabelPass = a.getString(R.styleable.SimpleLoginView_stringLabelPass1);
            stringLabelBtnAccess = a.getString(R.styleable.SimpleLoginView_stringLabelBtnAccess1);
            inputtype = a.getInteger(R.styleable.SimpleLoginView_inputtype, 0);
        }finally {
            a.recycle();
        }

        if(stringLabelUser == null || stringLabelUser.equals("")){
            stringLabelUser = context.getString(R.string.lbl_usuario);
        }

        if(stringLabelPass == null || stringLabelPass.equals("")){
            stringLabelPass = context.getString(R.string.lbl_password);
        }

        if(stringLabelBtnAccess == null || stringLabelBtnAccess.equals("")){
            stringLabelBtnAccess = context.getString(R.string.lbl_ingresar);
        }

        inicializar();
    }

    private void inicializar(){
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService(infService);
        layoutInflater.inflate(R.layout.simple_view_login,this, true);

        TxtInputUser = (TextInputLayout)findViewById(R.id.TxtInputUser);
        edtUser = (TextInputEditText)findViewById(R.id.edtUser);
        txtInputPass = (TextInputLayout)findViewById(R.id.txtInputPass);
        edtPassword = (TextInputEditText)findViewById(R.id.edtPassword);
        chVerPass = (CheckBox)findViewById(R.id.chVerPass);
        lblolvpass = (TextView)findViewById(R.id.lblolvpass);
        btnIngresar = (Button)findViewById(R.id.btnLogin);

        if(mshowRestartPass){
            lblolvpass.setVisibility(VISIBLE);
        }else{
            lblolvpass.setVisibility(GONE);
        }
        TxtInputUser.setHint(stringLabelUser);
        txtInputPass.setHint(stringLabelPass);
        btnIngresar.setText(stringLabelBtnAccess);
        switch (inputtype){
            case 0:
                edtUser.setInputType(InputType.TYPE_CLASS_TEXT);
            break;
            case 1:
                edtUser.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
            break;
            case 2:
                edtUser.setInputType(InputType.TYPE_CLASS_NUMBER);
            break;
        }

        asignarEventos();
    }

    private void asignarEventos(){

        chVerPass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    edtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }else{
                    edtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });

        btnIngresar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onClickLoginListener != null){
                    if(validar()){
                        onClickLoginListener.OnClickLogin(edtUser.getText().toString(),edtPassword.getText().toString());
                    }
                }
            }
        });

        lblolvpass.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onClickLostPasswordListener != null){
                    onClickLostPasswordListener.OnClickLostPassword();
                }
            }
        });
    }

    public void setOnClickLoginListener(OnClickLoginListener onClickLoginListener){
        this.onClickLoginListener = onClickLoginListener;
    }

    public void setOnClickLostPasswordListener(OnClickLostPasswordListener onClickLostPasswordListener){
        this.onClickLostPasswordListener = onClickLostPasswordListener;
    }

    private boolean validar(){
        boolean result = true;
        if(edtUser.getText().toString().equals("")){
            edtUser.setError(getContext().getString(R.string.lbl_text_vacio));
            edtUser.requestFocus();
            result = false;
        }else{
            if(inputtype == 1){
                if(!ValidadorInput.isValidEmail(edtUser.getText().toString())){
                    edtUser.setError(getContext().getString(R.string.lbl_text_vacio));
                    edtUser.requestFocus();
                    result = false;
                }
            }
        }

        if(edtPassword.getText().toString().equals("")){
            edtPassword.setError(getContext().getString(R.string.lbl_pass_vacio));
            edtPassword.requestFocus();
            return false;
        }

        return result;
    }
}