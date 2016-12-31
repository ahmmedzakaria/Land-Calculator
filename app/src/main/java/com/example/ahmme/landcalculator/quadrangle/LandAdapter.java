package com.example.ahmme.landcalculator.quadrangle;

import android.content.Context;
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


        viewHolder.captionTV.setText(infoList.get(position).getCaption());
        viewHolder.lenth_1TV.setText("Length 1: "+String.valueOf(infoList.get(position).getLength_1()));
        viewHolder.length_2TV.setText("Length 2: "+String.valueOf(infoList.get(position).getLength_2()));
        viewHolder.width_1TV.setText("Width 1: "+String.valueOf(infoList.get(position).getWidth_1()));
        viewHolder.width2TV.setText("Width 2: "+String.valueOf(infoList.get(position).getWidth_1()));
        viewHolder.captionTV.setText(infoList.get(position).getCaption());
        viewHolder.areaTV.setText("Area: "+String.valueOf(precision.format(area(infoList.get(position).getLength_1(),
                infoList.get(position).getLength_2(),infoList.get(position).getWidth_1(),
                infoList.get(position).getWidth_1())))+" ft\u00B2");
        viewHolder.desimTV.setText("Desim: "+String.valueOf(precision.format(desim())));
        viewHolder.gondaTV.setText("Gonda: "+String.valueOf(precision.format(gonda())));
        return convertView;
    }
    private double area(float length_1, float length_2, float width_1, float width_2){
        area=Math.sqrt(length_1*length_2*width_1*width_2);
        return area;
    }
    private double desim(){
        double desim=area/landInfo.getDesimUnit();
        return desim;
    }
    private double gonda(){
        double gonda=area/landInfo.getGondaUnit();
        return gonda;

    }

}