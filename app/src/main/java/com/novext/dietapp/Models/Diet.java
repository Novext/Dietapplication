package com.novext.dietapp.Models;

import android.widget.RatingBar;

/**
 * Created by JULIO on 18/09/2016.
 */
public class Diet {

    private String title;
    private String subtitle;
    private String description;
    private float ratingBar;

    public Diet(String hola, String txtDescription, String txtTitle, String txtSubTitle, float ratingBar) {
        this.description = txtDescription;
        this.title = txtTitle;
        this.subtitle = txtSubTitle;
        this.ratingBar = ratingBar;
    }

    public Diet(){

    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRatingBar(float ratingBar) {
        this.ratingBar = ratingBar;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getDescription() {
        return description;
    }
    public float getRatingBar(){
        return ratingBar;
    }


}
