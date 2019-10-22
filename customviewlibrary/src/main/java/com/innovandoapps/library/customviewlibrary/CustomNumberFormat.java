package com.innovandoapps.library.customviewlibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.innovandoapps.library.customviewlibrary.utils.NumberTextWatcher;

public class CustomNumberFormat extends LinearLayout {

    protected TextView txttitulo;
    protected EditText edtCuadroTxt;

    protected boolean mshowTitle;
    protected String title;
    protected int cpadding;
    protected boolean enableEditable;
    protected long valor;

    public CustomNumberFormat(Context context) {
        super(context);
        init();
    }

    public CustomNumberFormat(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomRounedEditText, 0, 0);
        try {
            mshowTitle = a.getBoolean(R.styleable.CustomRounedEditText_showTitle, true);
            title = a.getString(R.styleable.CustomRounedEditText_stringTitle);
            cpadding = a.getInt(R.styleable.CustomRounedEditText_cpadding,10);
            enableEditable = a.getBoolean(R.styleable.CustomRounedEditText_enableEditable, true);
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
        edtCuadroTxt.setInputType(InputType.TYPE_CLASS_NUMBER);

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
        asignarEventos();
    }

    protected void asignarEventos(){
        //edtCuadroTxt.addTextChangedListener(new NumberTextWatcher(edtCuadroTxt,valor));
    }

    public long getValor(){
        if(edtCuadroTxt.equals("")){
            return 0L;
        }else{
            return Long.parseLong(edtCuadroTxt.getText().toString());
        }
    }

    public void setError(String error){
        edtCuadroTxt.setError(error);
    }

    public void setValor(long valor){
        this.valor = valor;
        edtCuadroTxt.setText(Long.toString(valor));
    }
}
