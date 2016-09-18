package com.novext.dietapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateDietActivity extends AppCompatActivity {

    EditText edtTitle, edtDescription, edtSubtitle,
                edtPreparation;

    Button btnRegisterDiet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_diet);

        edtTitle = (EditText) findViewById(R.id.edtTitle);
        edtDescription = (EditText) findViewById(R.id.edtDescription);
        edtSubtitle = (EditText) findViewById(R.id.edtSubtitle);
        edtPreparation = (EditText) findViewById(R.id.edtPreparation);

        btnRegisterDiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerDiet();
            }
        });

    }

    public void registerDiet(){

    }
}
