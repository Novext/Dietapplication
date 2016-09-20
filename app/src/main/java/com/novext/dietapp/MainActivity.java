package com.novext.dietapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.novext.dietapp.Adapter.DietCardView;
import com.novext.dietapp.FragmentNavigationDrawer.FirstFragment;
import com.novext.dietapp.FragmentNavigationDrawer.FourFragment;
import com.novext.dietapp.FragmentNavigationDrawer.HomeFragment;
import com.novext.dietapp.FragmentNavigationDrawer.SecondFragment;
import com.novext.dietapp.FragmentNavigationDrawer.ThirdFragment;
import com.novext.dietapp.Models.Diet;

import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Response;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private RecyclerView recyclerView;
    private DietCardView adapter;


    ArrayList<Diet> items = new ArrayList();
    OKHttp okHttp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AddToolbar();

        okHttp = new OKHttp();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView  = (NavigationView) findViewById(R.id.nvView);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewDiet);
        recyclerView.setHasFixedSize(true);

        getDiets();


//        if (navigationView!= null){
//
//            AddDrawer(navigationView);
//            selectItem(navigationView.getMenu().getItem(0));
//        }

    }

    public void getDiets(){
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
                            recyclerView.setAdapter(adapter);


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
    }

//    public void selectItem (MenuItem item){
//        Fragment fragmentGeneric = null;
//        FragmentManager fragmentManager = getSupportFragmentManager();
//
//        switch (item.getItemId()){
//            case R.id.nav_home_fragment:
//                fragmentGeneric = new HomeFragment();
//                break;
//            case R.id.nav_first_fragment:
//                fragmentGeneric = new FirstFragment();
//                break;
//            case R.id.nav_second_fragment:
//                fragmentGeneric = new SecondFragment();
//                break;
//            case R.id.nav_third_fragment:
//                fragmentGeneric = new ThirdFragment();
//                break;
//            case R.id.nav_four_fragment:
//                fragmentGeneric = new FourFragment();
//        }
//        if (fragmentGeneric !=null){
//            fragmentManager.beginTransaction().replace(R.id.frameContent,fragmentGeneric).commit();
//        }
//        setTitle(item.getTitle());
//    }
//    private void AddDrawer(NavigationView navigationView){
//        navigationView.setNavigationItemSelectedListener(
//                new NavigationView.OnNavigationItemSelectedListener() {
//                    @Override
//                    public boolean onNavigationItemSelected(MenuItem item) {
//                        item.setCheckable(true);
//                        selectItem(item);
//                        drawerLayout.closeDrawers();
//                        return true;
//                    }
//                }
//        );
//
//    }

    private void AddToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab  = getSupportActionBar();
        if (ab!=null){
            ab.setHomeAsUpIndicator(R.mipmap.drawer_toggle);
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.profile_user ,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.userProfile:
                Intent intent = new Intent(this,ProfileActivity.class);
                this.startActivity(intent);
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }
}