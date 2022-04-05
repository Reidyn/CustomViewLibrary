package com.innovandoapps.library.customviewlibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomRounedEditText extends LinearLayout {

    protected TextView txttitulo;
    protected EditText edtCuadroTxt;
    protected  Context context;

    protected boolean mshowTitle;
    protected String title;
    protected int cpadding;
    protected boolean enableEditable;
    protected int inputtype;

    public CustomRounedEditText(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public CustomRounedEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomRounedEditText, 0, 0);
        try {
            mshowTitle = a.getBoolean(R.styleable.CustomRounedEditText_showTitle, true);
            title = a.getString(R.styleable.CustomRounedEditText_stringTitle);
            cpadding = a.getInt(R.styleable.CustomRounedEditText_cpadding,10);
            enableEditable = a.getBoolean(R.styleable.CustomRounedEditText_enableEditable, true);
            inputtype = a.getInteger(R.styleable.CustomRounedEditText_inputtype_edt, 0);
        }finally {
            a.recycle();
        }

        if(title == null){
            title = "";
        }

        init();
    }

    protected void init(){
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService(infService);
        layoutInflater.inflate(R.layout.rouned_focus_custom_edittex,this, true);

        txttitulo = (TextView)findViewById(R.id.txttitulo);
        edtCuadroTxt = (EditText)findViewById(R.id.edtCuadroTxt);

        if(!mshowTitle){
            txttitulo.setVisibility(GONE);
        }else{
            txttitulo.setVisibility(VISIBLE);
            txttitulo.setText(title);
        }

        float density = getContext().getResources().getDisplayMetrics().density;
        int paddingPixel = (int)(cpadding * density);
        edtCuadroTxt.setPadding(0,paddingPixel,0,0);

        if(!enableEditable){
            edtCuadroTxt.setClickable(false);
            edtCuadroTxt.setCursorVisible(false);
            edtCuadroTxt.setFocusable(false);
            edtCuadroTxt.setFocusableInTouchMode(false);
        }

        switch (inputtype){
            case 0:
                edtCuadroTxt.setInputType(InputType.TYPE_CLASS_TEXT);
                break;
            case 1:
                edtCuadroTxt.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                break;
            case 2:
                edtCuadroTxt.setInputType(InputType.TYPE_CLASS_NUMBER);
                break;
            case 3:
                float desity = context.getResources().getDisplayMetrics().density;
                int alto = 200;
                if(desity >= 3.0){
                    alto = 400;
                }
                LinearLayout.LayoutParams params = new  LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,alto);
                edtCuadroTxt.setLayoutParams(params);
                edtCuadroTxt.setSingleLine(false);
                edtCuadroTxt.setImeOptions(EditorInfo.IME_FLAG_NO_ENTER_ACTION);
                edtCuadroTxt.setGravity(Gravity.TOP);
                edtCuadroTxt.setTextColor(Color.BLACK);
             break;
            case 4:
                edtCuadroTxt.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
            break;
        }

        asignarEventos();
    }

    protected void asignarEventos(){
        edtCuadroTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    public String getText(){
        return edtCuadroTxt.getText().toString().trim();
    }

    public void setText(String text){
        edtCuadroTxt.setText("  " + text);
    }

    public void setError(String error){
        edtCuadroTxt.setError(error);
    }
}
