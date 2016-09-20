package com.novext.dietapp;

import android.os.AsyncTask;
import android.support.v4.os.AsyncTaskCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Response;

public class CreateDietActivity extends AppCompatActivity {

    EditText edtTitle, edtDescription, edtSubtitle,
                edtPreparation;

    Button btnRegisterDiet;

    OKHttp okHttp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_diet);

        edtTitle = (EditText) findViewById(R.id.edtTitle);
        edtDescription = (EditText) findViewById(R.id.edtDescription);
        edtSubtitle = (EditText) findViewById(R.id.edtSubtitle);
        edtPreparation = (EditText) findViewById(R.id.edtPreparation);

        okHttp = new OKHttp();

        btnRegisterDiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerDiet();
            }
        });

    }

    public void registerDiet(){


        final String title = edtTitle.getText().toString();
        final String description = edtDescription.getText().toString();
        final String subtitle = edtSubtitle.getText().toString();
        final String preparation = edtPreparation.getText().toString();

        new AsyncTask<Void,Void, Response>(){

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Response doInBackground(Void... params) {



                JSONObject values = new JSONObject();
                try {
                    values.put("title",title);
                    values.put("subtitle",subtitle);
                    values.put("description",description);
                    values.put("subtitle",subtitle);
                    values.put("preparation",preparation);

                    try{

                        return okHttp.post("http://localhost:3001/diet",values);
                    }catch (IOException e){
                        
                    }

                }catch (JSONException e){

                }

                return null;
            }

            @Override
            protected void onPostExecute(Response res) {

                if(res.code()==200){
                    finish();
                }
                
                if(res.code()==503){
                    Toast.makeText(CreateDietActivity.this, "No se pudo crear la dieta correctamente", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute(null,null,null);

    }
}
