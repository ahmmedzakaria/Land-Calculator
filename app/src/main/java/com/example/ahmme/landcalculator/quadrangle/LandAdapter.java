package com.example.ahmme.landcalculator.quadrangle;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ahmme.landcalculator.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Laptop Dream on 08-Jul-16.
 */
@RequiresApi(api = Build.VERSION_CODES.N)
public class LandAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<LandInfo> infoList;
    ViewHolder viewHolder;
    LandInfo landInfo=new LandInfo();

    DecimalFormat precision = new DecimalFormat("0.00");
    double area;
    public LandAdapter(Context context, ArrayList<LandInfo> infoList) {
        super(context, R.layout.row_layout,infoList);
        this.context=context;
        this.infoList=infoList;
        landInfo.setContext(context);

        landInfo.sharedPreferences=context.getSharedPreferences(landInfo.LAND_CALCULATOR, Context.MODE_PRIVATE);
        landInfo.editor=landInfo.sharedPreferences.edit();

        float desim=landInfo.sharedPreferences.getFloat(landInfo.DESIM,435.6f);
        float gonda=landInfo.sharedPreferences.getFloat(landInfo.GONDA,762.3f);
        landInfo.setGondaUnit(gonda);
        landInfo.setDesimUnit(desim);
    }
    public class ViewHolder{
        private TextView lenth_1TV;
        private TextView length_2TV;
        private TextView width_1TV;
        private TextView width2TV;
        private TextView captionTV;
        private TextView areaTV;
        private TextView desimTV;
        private TextView gondaTV;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row_layout,null);
            viewHolder = new ViewHolder();
            viewHolder.lenth_1TV = (TextView) convertView.findViewById(R.id.length_1rl);
            viewHolder.length_2TV = (TextView) convertView.findViewById(R.id.length_2rl);
            viewHolder.width_1TV = (TextView) convertView.findViewById(R.id.width_1rl);
            viewHolder.width2TV =(TextView)convertView.findViewById(R.id.width_2rl);
            viewHolder.captionTV =(TextView)convertView.findViewById(R.id.caption);
            viewHolder.areaTV=(TextView)convertView.findViewById(R.id.area);
            viewHolder.desimTV=(TextView)convertView.findViewById(R.id.desim);
            viewHolder.gondaTV =(TextView)convertView.findViewById(R.id.gonda);

        }
        else {
            convertView.getTag();
        }


       if(infoList.get(position).getWidth_1()<=0){
            viewHolder.captionTV.setText(infoList.get(position).getCaption());
            viewHolder.lenth_1TV.setText("Base: " + String.valueOf(infoList.get(position).getLength_1()));
            viewHolder.length_2TV.setText("Height: " + String.valueOf(infoList.get(position).getLength_2()));
           viewHolder.width_1TV.setText("");
           viewHolder.width2TV.setText("");
            viewHolder.areaTV.setText("Area: " + String.valueOf(precision.format(triangleArea(infoList.get(position).getLength_1(),
                    infoList.get(position).getLength_2()))) + " ft\u00B2");
            viewHolder.desimTV.setText("Desim: " + precision.format(desim()));
            viewHolder.gondaTV.setText("Gonda: " + precision.format(gonda()));

        }else {
            viewHolder.captionTV.setText(infoList.get(position).getCaption());
            viewHolder.lenth_1TV.setText("Length 1: " + String.valueOf(infoList.get(position).getLength_1()));
            viewHolder.length_2TV.setText("Length 2: " + String.valueOf(infoList.get(position).getLength_2()));
            viewHolder.width_1TV.setText("Width 1: " + String.valueOf(infoList.get(position).getWidth_1()));
            viewHolder.width2TV.setText("Width 2: " + String.valueOf(infoList.get(position).getWidth_1()));
//            viewHolder.captionTV.setText(infoList.get(position).getCaption());
            viewHolder.areaTV.setText("Area: " + String.valueOf(precision.format(area(infoList.get(position).getLength_1(),
                    infoList.get(position).getLength_2(), infoList.get(position).getWidth_1(),
                    infoList.get(position).getWidth_1()))) + " ft\u00B2");
            viewHolder.desimTV.setText("Desim: " + precision.format(desim()));
            viewHolder.gondaTV.setText("Gonda: " + precision.format(gonda()));
        }




        /*if(infoList.get(position).getWidth_1()<=0){
            viewHolder.captionTV.setText(infoList.get(position).getCaption());
            viewHolder.lenth_1TV.setText(landInfo.getConcatData(R.string.base,infoList.get(position).getLength_1(),+R.string.ft));
            viewHolder.length_2TV.setText(landInfo.getConcatData(R.string.height,infoList.get(position).getLength_2(),+R.string.ft));
            viewHolder.areaTV.setText(landInfo.getConcatData(R.string.area,
                    landInfo.getTriangleArea(landInfo.getLength_1(),landInfo.getLength_2()),R.string.ft_sqare));
            viewHolder.desimTV.setText(landInfo.getConcatData(R.string.desim,landInfo.getDesimValue()));
            viewHolder.gondaTV.setText(landInfo.getConcatData(R.string.gonda,landInfo.getGondaValue()));

        }else {
            viewHolder.captionTV.setText(infoList.get(position).getCaption());
            viewHolder.lenth_1TV.setText(landInfo.getConcatData(R.string.length_1,infoList.get(position).getLength_1(),+R.string.ft));
            viewHolder.length_2TV.setText(landInfo.getConcatData(R.string.length_2,infoList.get(position).getLength_2(),+R.string.ft));
            viewHolder.width_1TV.setText(landInfo.getConcatData(R.string.width_1,infoList.get(position).getWidth_1(),+R.string.ft));
            viewHolder.width2TV.setText(landInfo.getConcatData(R.string.width_2,infoList.get(position).getWidth_2(),+R.string.ft));
            viewHolder.areaTV.setText(landInfo.getConcatData(R.string.area, landInfo.getQuadrangleArea(
                    landInfo.getLength_1(),landInfo.getLength_2(), landInfo.getWidth_1(),landInfo.getWidth_2()),R.string.ft_sqare));
            viewHolder.desimTV.setText(landInfo.getConcatData(R.string.desim,landInfo.getDesimValue()));
            viewHolder.gondaTV.setText(landInfo.getConcatData(R.string.gonda,landInfo.getGondaValue()));
        }*/
        return convertView;
    }
    private double area(float length_1, float length_2, float width_1, float width_2){
        area=Math.sqrt(length_1*length_2*width_1*width_2);
        return area;
    }

    private double triangleArea(float base, float height){
        area=0.5*base*height;
        return area;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private double desim(){
        double desim=area/landInfo.getDesimUnit();
        return desim;
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private double gonda(){
        double gonda=area/landInfo.getGondaUnit();
        return gonda;

    }

}