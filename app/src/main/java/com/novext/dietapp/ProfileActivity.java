package com.novext.dietapp;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_profile);
        setSupportActionBar(toolbar);

        if (toolbar!=null){

            toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.vector));
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
        final String MY_PREFS_NAME = "MyPrefsFile";
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String restoredText = prefs.getString("text", null);
        if (restoredText != null) {
            TextView first_name = (TextView) findViewById(R.id.firstname_profile);
            TextView email = (TextView) findViewById(R.id.email_profile);

            String first_name_pref = prefs.getString("name", "firstname");
            String email_pref = prefs.getString("idName", "email");
            Log.d(" OBTENIENDO EL NOMBRE DEL PREFERENCE",first_name_pref);
            Log.d(" OBTENIENDO EL  EMAIL DEL PREFERENCE ",email_pref);

            first_name.setText(first_name_pref);
            email.setText(email_pref);

        }

    }
}
