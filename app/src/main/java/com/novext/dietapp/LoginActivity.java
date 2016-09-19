package com.novext.dietapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestAsyncTask;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.auth.api.*;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener,View.OnClickListener {

    GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_IN = 9001;
    CallbackManager callbackManager;
    LoginButton loginButton;
    SignInButton btnLoginGoogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                //.requestIdToken(getString(R.string.server_client_id))
                .requestEmail()
                .build();

        btnLoginGoogle = (SignInButton) findViewById(R.id.sign_in_button);
        btnLoginGoogle.setOnClickListener(this);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this , this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        callbackManager = CallbackManager.Factory.create();

        loginButton = (LoginButton) findViewById(R.id.login_button);

        loginButton.setReadPermissions("email");



        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(LoginActivity.this, loginResult.getAccessToken().getUserId(), Toast.LENGTH_SHORT).show();
                Toast.makeText(LoginActivity.this, loginResult.getAccessToken().getToken(), Toast.LENGTH_SHORT).show();

                final AccessToken accessToken = loginResult.getAccessToken();

                final JSONObject obj = new JSONObject();
                final OKHttp okHttp = new OKHttp();
                final String url  = "https://dietapplication.herokuapp.com/api/users/social";

                GraphRequestAsyncTask request_faccebook = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject user, GraphResponse graphResponse) {


                        try {
                            obj.put("firstname",user.optString("name"));
                            obj.put("lastname",user.optString("email"));
                            Log.e("AQUI OBTENIENDO LOS DATOS DEL USUARIO", String.valueOf(obj));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }).executeAsync();
            }
            @Override
            public void onCancel() {

            }
            @Override
            public void onError(FacebookException error) {
                Log.e("AQUI ERROR 2", String.valueOf(error));
            }
        });
        String txtGoggle = "Inciar sesión con Goggle";
        setGooglePlusButtonText(btnLoginGoogle,txtGoggle);
    }


    protected void setGooglePlusButtonText(SignInButton signInButton,String buttonText) {
        for (int i = 0; i < signInButton.getChildCount(); i++) {
            View v = signInButton.getChildAt(i);

            if (v instanceof TextView) {
                TextView tv = (TextView) v;
                tv.setTextSize(15);
                tv.setTypeface(null, Typeface.NORMAL);
                tv.setText(buttonText);
                return;
            }
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
            startActivity(new Intent(this, MainActivity.class));
        }

        callbackManager.onActivityResult(requestCode,resultCode,data);

    }

    private void handleSignInResult(GoogleSignInResult result) {
        JSONObject obj = new JSONObject();
        OKHttp okHttp = new OKHttp();
        String url = "https://dietapplication.herokuapp.com/api/users/social";
        Log.d("TAG", "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {

            GoogleSignInAccount account = result.getSignInAccount();
            if (account!=null){
                try {
                    Toast.makeText(LoginActivity.this, account.getDisplayName(), Toast.LENGTH_SHORT).show();
                    obj.put("firstname",account.getDisplayName());
                    obj.put("email",account.getEmail());
                    Log.d("OBTENIENDO EN EMAIL :: ", account.getEmail());
                    Log.d("OBTENIENDO EN NAME  :: ", account.getDisplayName());

                    final String MY_PREFS_NAME = "MyPrefsFile";

                    SharedPreferences.Editor editor_profile = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                    editor_profile.putString("firstname",account.getDisplayName());
                    editor_profile.putString("email",account.getEmail());

                    Log.d("ENVIANDO TODOS LOS DATOS ==========>", String.valueOf(editor_profile));
                    editor_profile.commit();

                    //okHttp.post(url,obj);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }else{
            String a  = "JULIO";
            Log.d("HOLA HAY ERRORES :D",a);
        }
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }




}
