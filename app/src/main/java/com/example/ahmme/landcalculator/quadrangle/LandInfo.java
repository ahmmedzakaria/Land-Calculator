package com.example.ahmme.landcalculator.quadrangle;

import android.content.Context;
import android.content.SharedPreferences;
import android.icu.text.DecimalFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

/**
 * Created by ahmme on 12/25/2016.
 */

@RequiresApi(api = Build.VERSION_CODES.N)
public class LandInfo {
    public static final String LAND_CALCULATOR="land_calculator";
    public static final String SAVE_HISTORY="save_history";
    public static final String DESIM="desim";
    public static final String GONDA="gonda";
    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    DecimalFormat precision=new DecimalFormat("0.00");
    DecimalFormat precisionSkipDecimal=new DecimalFormat("0");

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
    public double getQuadrangleArea(float length_1, float length_2, float width_1, float width_2){
        setArea(Math.sqrt(length_1*length_2*width_1*width_2));
        return getArea();
    }

    public double getTriangleArea(float base, float height){
       setArea(0.5*base*height);
        return getArea();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public double getDesimValue(){
        double desim=getArea()/getDesimUnit();
        return desim;
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public double getGondaValue(){
        double gonda=getArea()/getGondaUnit();
        return gonda;

    }

    public void setContext(Context context) {
        this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String inchToFtConvert(String string) throws ArithmeticException{

        int value=Integer.valueOf(skipNullValue(string));
        if(value>12){
            throw new ArithmeticException();
        }
        double doublevalue=value*8.333+0.5;
        return precisionSkipDecimal.format(doublevalue);
    }

    public String skipNullValue(String string){
        String value="0";
        if(string.length()>0){
            value=string;
        }
       return value;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public float getFootValue(String footValue, String inchValue)throws NumberFormatException{
        if(footValue.length()<=0){
            throw new NumberFormatException();
        }
        String inchTofoot=inchToFtConvert(inchValue);
        return Float.valueOf(footValue+"."+inchTofoot);
    }

    public String getConcatData(int resourseId, String name){
        String s= getContext().getResources().getString(resourseId)+" :"+name;
        return s;
    }

    public String getConcatData(int resourseId, double value) {
        return getContext().getResources().getString(resourseId) + " : " + precision.format(value);
    }

    public String getConcatData(int resourseId, double value, int resourseIdforUnit){
        return getContext().getResources().getString(resourseId) +" : "+ precision.format(value)
                +getContext().getResources().getString(resourseIdforUnit);
    }
    public String getConcatData(int resourseId, float value){
        return getContext().getResources().getString(resourseId) +" : "+ precision.format(value);
    }
    public String getConcatData(int resourseId, float value,int resourseIdforUnit){
        return getContext().getResources().getString(resourseId) +" : "+ precision.format(value)
        +getContext().getResources().getString(resourseIdforUnit);
    }


}
