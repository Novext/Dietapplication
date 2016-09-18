package com.novext.dietapp.FragmentHomePage;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.novext.dietapp.Adapter.DietCardView;
import com.novext.dietapp.Models.Diet;
import com.novext.dietapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DietFragment extends Fragment {


    public DietFragment() {
        // Required empty public constructor
    }
    List items = new ArrayList();
    RecyclerView recycler;
    DietCardView adapter;
    LinearLayoutManager lManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_diet, container, false);

        items.add(new Diet("HOLA","HOLA 1","HOLA 2", "Angel Beats", 2.0f));
        items.add(new Diet("HOLA","HOLA 1","HOLA 2", "Angel Beats", 2.0f));
        items.add(new Diet("HOLA","HOLA 1","HOLA 2", "Angel Beats", 2.0f));
        items.add(new Diet("HOLA","HOLA 1","HOLA 2", "Angel Beats", 2.0f));
        items.add(new Diet("HOLA","HOLA 1","HOLA 2", "Angel Beats", 2.0f));
        items.add(new Diet("HOLA","HOLA 1","HOLA 2", "Angel Beats", 2.0f));
        items.add(new Diet("HOLA","HOLA 1","HOLA 2", "Angel Beats", 2.0f));


        recycler = (RecyclerView) view.findViewById(R.id.recyclerViewDiet);
        recycler.setHasFixedSize(true);

        lManager = new LinearLayoutManager(getActivity());
        recycler.setLayoutManager(lManager);


        adapter = new DietCardView(items);
        recycler.setAdapter(adapter);
        return view;
    }

}
