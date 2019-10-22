package com.innovandoapps.library.kernel.acivitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

/**
 * @Autor Marcos Ramirez
 * Clase abstracta para generalizar los activitys con Toolbar
 * con el icono de retorno
 */
public abstract class BaseToolbarActivity extends BaseActivity{

    protected Toolbar appbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        appbar = (Toolbar)findViewById(getToolbarId());
        setSupportActionBar(appbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(getTitleToolbar());

    }

    /**
     * Metodo abstracto para obtener el id del toolbar
     * @return Integer del id
     */
    protected abstract int getToolbarId();

    /**
     * Metodo abstracto que retorna el String del titulo del toolbar
     * @return String titulo de toolbar
     */
    protected abstract String getTitleToolbar();

    /**
     * Sobreescribe el evento click sobre icono de retorno
     * ejecuta el onBackPressed
     * @return
     */
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
