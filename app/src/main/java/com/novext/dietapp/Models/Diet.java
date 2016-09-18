package com.novext.dietapp.Models;

import android.widget.RatingBar;

/**
 * Created by JULIO on 18/09/2016.
 */
public class Diet {

    private String txtTitle;
    private String txtSubTitle;
    private String txtDescription;
    private float ratingBar;

    public Diet(String hola, String txtDescription, String txtTitle, String txtSubTitle, float ratingBar) {
        this.txtDescription = txtDescription;
        this.txtTitle = txtTitle;
        this.txtSubTitle = txtSubTitle;
        this.ratingBar = ratingBar;
    }

    public String getTxtTitle() {
        return txtTitle;
    }

    public String getTxtSubTitle() {
        return txtSubTitle;
    }

    public String getTxtDescription() {
        return txtDescription;
    }
    public float getRatingBar(){
        return ratingBar;
    }
}
