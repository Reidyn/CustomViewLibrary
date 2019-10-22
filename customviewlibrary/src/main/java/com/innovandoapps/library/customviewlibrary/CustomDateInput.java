package com.innovandoapps.library.customviewlibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.innovandoapps.library.kernel.dialogs.DialogDatePicker;
import com.innovandoapps.library.kernel.dialogs.listener.OnDataSelectListener;
import com.innovandoapps.library.kernel.utils.FechaUtil;
import java.util.Date;

public class CustomDateInput extends LinearLayout {

    protected TextView txttitulo;
    protected EditText edtCuadroTxt;
    protected ImageButton btnImgCalendar;
    protected boolean mshowTitle;
    protected String title;

    public CustomDateInput(Context context) {
        super(context);
        init();
    }

    public CustomDateInput(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomInputDate, 0, 0);
        try {
            mshowTitle = a.getBoolean(R.styleable.CustomInputDate_showTitle_date, true);
            title = a.getString(R.styleable.CustomInputDate_stringTitle_date);
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
        layoutInflater.inflate(R.layout.custom_date_input,this, true);

        txttitulo = (TextView)findViewById(R.id.txttitulo);
        edtCuadroTxt = (EditText)findViewById(R.id.edtCuadroTxt);
        btnImgCalendar = (ImageButton)findViewById(R.id.btnImgCalendar);
        edtCuadroTxt.setImeOptions(EditorInfo.IME_FLAG_NO_ENTER_ACTION);
        edtCuadroTxt.setInputType(InputType.TYPE_CLASS_DATETIME | InputType.TYPE_DATETIME_VARIATION_DATE);
        edtCuadroTxt.setClickable(true);

        if(!mshowTitle){
            txttitulo.setVisibility(GONE);
        }else{
            txttitulo.setVisibility(VISIBLE);
            txttitulo.setText(title);
        }
        asignarEventos();
    }

    protected void asignarEventos(){
        btnImgCalendar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogDatePicker dialog = new DialogDatePicker();
                dialog.setOnDataSelectListener(new OnDataSelectListener() {
                    @Override
                    public void OnDataSelect(String fech) {
                        edtCuadroTxt.setText(fech);
                    }
                });
                dialog.show(((AppCompatActivity) getContext()).getSupportFragmentManager(),"");
            }
        });
    }

    public String getStringFecha(){
        return edtCuadroTxt.getText().toString();
    }

    public Date getFecha(){
        return FechaUtil.stringToDate(edtCuadroTxt.getText().toString());
    }

    public void setError(String error){
        edtCuadroTxt.setError(error);
    }

    public void setDate(Date date){
        edtCuadroTxt.setText(FechaUtil.DateToString(date));
    }

    public void setEnabled(boolean value){
        edtCuadroTxt.setEnabled(value);
        btnImgCalendar.setEnabled(value);
    }
}
