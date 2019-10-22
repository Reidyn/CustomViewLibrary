package com.innovandoapps.library.customviewlibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.innovandoapps.library.customviewlibrary.Listeners.OnClickLoginListener;
import com.innovandoapps.library.customviewlibrary.Listeners.OnClickLostPasswordListener;

/**
 * Created by desarrollo on 23/11/17.
 */

public class LoginView extends LinearLayout{

    private CardView card_view;
    private TextView lblmsjingreso;
    private LinearLayout lineDivisor;
    private EditText edtUsuario;
    private EditText edtPassword;
    private CheckBox chVerPass;
    private TextView lblolvpass;
    private Button btnIngresar;

    private OnClickLoginListener onClickLoginListener;
    private OnClickLostPasswordListener onClickLostPasswordListener;

    private boolean mshowRestartPass = true;
    private int loginColor;
    private boolean showLabelTop;
    private String stringLabelTop;
    private String stringLabelUser;
    private String stringLabelPass;
    private String stringLabelBtnAccess;

    public LoginView(Context context) {
        super(context);
        inicializar();
    }

    public LoginView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.LoginView, 0, 0);
        try {
            mshowRestartPass = a.getBoolean(R.styleable.LoginView_showRestartPass, true);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                loginColor = a.getColor(R.styleable.LoginView_loginColor, context.getColor(R.color.azul2));
            }else{
                loginColor = a.getColor(R.styleable.LoginView_loginColor, ContextCompat.getColor(context,R.color.azul2));
            }
            showLabelTop = a.getBoolean(R.styleable.LoginView_showLabelTop, true);
            stringLabelTop = a.getString(R.styleable.LoginView_stringLabelTop);
            stringLabelUser = a.getString(R.styleable.LoginView_stringLabelUser);
            stringLabelPass = a.getString(R.styleable.LoginView_stringLabelPass);
            stringLabelBtnAccess = a.getString(R.styleable.LoginView_stringLabelBtnAccess);
        } finally {
            a.recycle();
        }

        if(stringLabelTop == null || stringLabelTop.equals("")){
            stringLabelTop = context.getString(R.string.lbl_msj_ingreso);
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

    public void inicializar(){
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService(infService);
        layoutInflater.inflate(R.layout.view_login,this, true);

        card_view = (CardView)findViewById(R.id.card_view);
        lblmsjingreso = (TextView)findViewById(R.id.lblmsjingreso);
        lineDivisor = (LinearLayout)findViewById(R.id.lineDivisor);
        edtUsuario = (EditText)findViewById(R.id.edtUsuario);
        edtPassword = (EditText)findViewById(R.id.edtPassword);
        chVerPass = (CheckBox)findViewById(R.id.chVerPass);
        lblolvpass = (TextView)findViewById(R.id.lblolvpass);
        btnIngresar = (Button)findViewById(R.id.btnIngresar);

        card_view.setCardBackgroundColor(loginColor);
        if(mshowRestartPass){
            lblolvpass.setVisibility(VISIBLE);
        }else{
            lblolvpass.setVisibility(GONE);
        }
        if(showLabelTop){
            lblmsjingreso.setVisibility(VISIBLE);
            lineDivisor.setVisibility(VISIBLE);
        }else{
            lblmsjingreso.setVisibility(GONE);
            lineDivisor.setVisibility(GONE);
        }

        lblmsjingreso.setText(stringLabelTop);
        edtUsuario.setHint(stringLabelUser);
        edtPassword.setHint(stringLabelPass);
        btnIngresar.setText(stringLabelBtnAccess);

        asignarEventos();
    }

    public void setOnClickLoginListener(OnClickLoginListener onClickLoginListener){
        this.onClickLoginListener = onClickLoginListener;
    }

    public void setOnClickLostPasswordListener(OnClickLostPasswordListener onClickLostPasswordListener){
        this.onClickLostPasswordListener = onClickLostPasswordListener;
    }

    private void asignarEventos(){
        btnIngresar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onClickLoginListener != null){
                    onClickLoginListener.OnClickLogin(edtUsuario.getText().toString(),edtPassword.getText().toString());
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
    }
}
