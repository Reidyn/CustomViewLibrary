package com.innovandoapps.customviewlibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //startActivity(new Intent(getApplicationContext(),CustomInputExampleActivity.class));
        startActivity(new Intent(getApplicationContext(),CustomLoginExampleActivity.class));
        //startActivity(new Intent(getApplicationContext(),CustomGalleryExmpleActivity.class));
        finish();
    }
}
