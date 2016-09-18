package com.novext.dietapp.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.novext.dietapp.Models.Diet;
import com.novext.dietapp.R;

import java.util.List;

/**
 * Created by JULIO on 18/09/2016.
 */
public class DietCardView extends RecyclerView.Adapter<DietCardView.ViewHolder>{



    private List<Diet> items;

    public DietCardView(List<Diet> items) {
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_card_diet,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title_diet.setText(items.get(position).getTxtTitle());
        holder.subTitle_diet.setText(items.get(position).getTxtSubTitle());
        holder.description_diet.setText(items.get(position).getTxtDescription());
        holder.rantingbar_diet.setRating(items.get(position).getRatingBar());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title_diet;
        public TextView subTitle_diet;
        public TextView description_diet;
        public RatingBar rantingbar_diet;

        public ViewHolder(View view) {
            super(view);
            title_diet = (TextView)view.findViewById(R.id.txtDietTitle);
            subTitle_diet = (TextView) view.findViewById(R.id.txtDietSubTitle);
            description_diet = (TextView) view.findViewById(R.id.txtDietDescription);
            rantingbar_diet = (RatingBar) view.findViewById(R.id.ratingBar);
        }
    }
}
