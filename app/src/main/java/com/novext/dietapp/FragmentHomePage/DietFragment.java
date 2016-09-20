package com.novext.dietapp.FragmentHomePage;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.novext.dietapp.Adapter.DietCardView;
import com.novext.dietapp.App;
import com.novext.dietapp.Models.Diet;
import com.novext.dietapp.OKHttp;
import com.novext.dietapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DietFragment extends Fragment {


    public DietFragment() {
        // Required empty public constructor
    }

    ArrayList<Diet> items = new ArrayList();
    RecyclerView recycler;
    DietCardView adapter;
    LinearLayoutManager lManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_diet, container, false);
        final OKHttp okHttp = new OKHttp();

        recycler = (RecyclerView) view.findViewById(R.id.recyclerViewDiet);
        recycler.setHasFixedSize(true);

        lManager = new LinearLayoutManager(getActivity());
        recycler.setLayoutManager(lManager);
        adapter = new DietCardView();


        new AsyncTask<Void,Void, Response>(){

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Response doInBackground(Void... params) {

                try{
                    return okHttp.get("https://dietapplication.herokuapp.com/api/diets");
                }catch (IOException e){

                }
                return null;

            }

            @Override
            protected void onPostExecute(Response res) {
                if(res!=null){
                    Log.i("RESPONSE REQUEST",""+res.code());
                    if(res.code()==200){

                        try {

                            JSONArray jsonArray = new JSONArray(res.body().string().toString());
                            for (int i = 0; i < jsonArray.length() ; i++) {
                                Diet diet = new Diet();
                                diet.setTitle(jsonArray.getJSONObject(i).getString("title"));
                                diet.setDescription(jsonArray.getJSONObject(i).getString("description"));
                                diet.setSubtitle(jsonArray.getJSONObject(i).getString("subtitle"));
                                items.add(diet);
                            }

                            adapter.setData(items);
                            recycler.setAdapter(adapter);


                        }catch (Exception e){
                            Log.e("ERROR",e.toString());
                        }
                    }

                    if(res.code()==503){
                        Toast.makeText(App.getInstance(), "No se pudo crear la dieta correctamente", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }.execute(null,null,null);

        return view;
    }

}
