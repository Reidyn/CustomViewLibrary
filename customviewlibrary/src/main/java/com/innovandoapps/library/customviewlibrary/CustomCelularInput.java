package com.innovandoapps.library.customviewlibrary;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.Spinner;

public class CustomCelularInput extends LinearLayout {

    private Spinner spprefijo;
    private Spinner spsubprefijo;
    private TextInputEditText edtCelular;

    public CustomCelularInput(Context context) {
        super(context);
        init();
    }

    public CustomCelularInput(Context context,AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService(infService);
        layoutInflater.inflate(R.layout.custom_celular_input,this, true);

        spprefijo = (Spinner)findViewById(R.id.spprefijo);
        spsubprefijo = (Spinner)findViewById(R.id.spsubprefijo);
        edtCelular = (TextInputEditText)findViewById(R.id.edtCelular);
    }

    public String getCelularNumber(){
        String[] arr_prefijos = getContext().getResources().getStringArray(R.array.telefonia_sufijo_arrays);
        String[] arr_numeros = getContext().getResources().getStringArray(R.array.numero_arrays);
        String nrocelular = arr_prefijos[spprefijo.getSelectedItemPosition()] +
                            arr_numeros[spsubprefijo.getSelectedItemPosition()] +
                            edtCelular.getText().toString();
        return nrocelular;
    }

    public String getNumber(){
        return edtCelular.getText().toString();
    }
}
