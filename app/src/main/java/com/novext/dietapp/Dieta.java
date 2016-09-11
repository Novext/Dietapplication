package com.novext.dietapp;

/**
 * Created by JULIO on 9/09/2016.
 */
public class Dieta {

    private int image;
    private String name;
    private int visitas;

    public Dieta(int image, String name, int visitas){
        this.image = image;
        this.name  =name;
        this.visitas = visitas;
    }
    public String getName(){
        return name;
    }
    public  int getImage(){
        return image;

    }
    public int getVisitas(){
        return visitas;
    }
}
