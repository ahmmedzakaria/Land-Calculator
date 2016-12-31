package com.example.ahmme.landcalculator.quadrangle;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ahmme on 12/25/2016.
 */

public class LandInfo {
    public static final String LAND_CALCULATOR="land_calculator";
    public static final String SAVE_HISTORY="save_history";
    public static final String DESIM="desim";
    public static final String GONDA="gonda";
    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;

    private int id;
    private String caption;
    private float length_1;
    private float length_2;
    private float width_1;
    private float width_2;
    private double area;
    private float desimUnit;
    private float gondaUnit;
    private Context context;


    public LandInfo(int id, String caption, float length_1, float length_2, float width_1, float width_2) {
        this.id = id;
        this.caption = caption;
        this.length_1 = length_1;
        this.length_2 = length_2;
        this.width_1 = width_1;
        this.width_2 = width_2;

    }

    public LandInfo(String caption, float length_1, float length_2, float width_1, float width_2) {
        this.caption = caption;
        this.length_1 = length_1;
        this.length_2 = length_2;
        this.width_1 = width_1;
        this.width_2 = width_2;
    }

    public LandInfo(String caption, float length_1, float length_2) {
        this.caption = caption;
        this.length_1 = length_1;
        this.length_2 = length_2;
    }

    public LandInfo() {
    }

    public int getId() {
        return id;
    }

    public float getLength_1() {
        return length_1;
    }

    public void setLength_1(float length_1) {
        this.length_1 = length_1;
    }

    public float getLength_2() {
        return length_2;
    }

    public void setLength_2(float length_2) {
        this.length_2 = length_2;
    }

    public float getWidth_1() {
        return width_1;
    }

    public void setWidth_1(float width_1) {
        this.width_1 = width_1;
    }

    public float getWidth_2() {
        return width_2;
    }

    public void setWidth_2(float width_2) {
        this.width_2 = width_2;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public float getDesimUnit() {
        return desimUnit;
    }

    public void setDesimUnit(float desimUnit) {
        if (desimUnit<=0){
            this.desimUnit = 435.6f;
        }
        this.desimUnit = desimUnit;
    }

    public float getGondaUnit() {
        return gondaUnit;
    }

    public void setGondaUnit(float gondaUnit) {
        if (gondaUnit<=0){
            this.gondaUnit = 762.3f;
        }
        this.gondaUnit = gondaUnit;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
