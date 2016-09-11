package com.novext.dietapp;

import android.content.ClipData;
import android.media.Image;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;

import java.util.List;

/**
 * Created by JULIO on 9/09/2016.
 */
public class HomeProfileAdapter extends RecyclerView.Adapter<HomeProfileAdapter.HomeProfileViewHolder> {

    private List<Dieta> items;



    public class HomeProfileViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView name;
        public TextView visit;

        public HomeProfileViewHolder(View itemView) {
            super(itemView);
            image    = (ImageView) itemView.findViewById(R.id.imgCardHome);
            name     = (TextView)  itemView.findViewById(R.id.nameCardHome);
            visit    = (TextView)  itemView.findViewById(R.id.visitCardHome);
        }
    }

    @Override
    public HomeProfileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v  = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_cardview_content,parent,false);

        return new HomeProfileViewHolder(v);

    }

    @Override
    public void onBindViewHolder(HomeProfileViewHolder holder, int position) {
        holder.image.setImageResource(items.get(position).getImage());
        holder.name.setText(items.get(position).getName());
        holder.visit.setText("Visitas : " + String.valueOf(items.get(position).getVisitas()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public HomeProfileAdapter(){
        this.items  = items;
    }
}
