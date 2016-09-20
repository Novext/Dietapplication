package com.novext.dietapp;

import android.net.http.HttpResponseCache;
import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by JULIO on 13/09/2016.
 */
public class OKHttp {


    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private OkHttpClient client;

    public OKHttp(){
        client = new OkHttpClient();
    }



    public Response post(String url, JSONObject json) throws IOException {
        RequestBody body = RequestBody.create(JSON, String.valueOf(json));
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        return client.newCall(request).execute();
    }


    public Response get(String url) throws IOException{
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        return client.newCall(request).execute();

    }

}
