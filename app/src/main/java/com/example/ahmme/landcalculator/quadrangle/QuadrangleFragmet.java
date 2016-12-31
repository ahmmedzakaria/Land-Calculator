package com.example.ahmme.landcalculator.quadrangle;


import android.content.Context;
import android.content.SharedPreferences;
import android.icu.text.DecimalFormat;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmme.landcalculator.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
@RequiresApi(api = Build.VERSION_CODES.N)
public class QuadrangleFragmet extends Fragment {

    private EditText length_1_ET;
    private EditText length_2_ET;
    private EditText width_1_ET;
    private EditText width_2_ET;
    private EditText captionET;
    private TextView result_TV;
    private Button resultBtn;


/*    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;*/

    Adapter adapter;
    ArrayList<LandInfo> arrayList;
    LandlInfoManager manager;
    LandInfo landInfo;
    String newline = System.getProperty("line.separator");
    DecimalFormat precision = new DecimalFormat("0.00");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle saveInstanceState) {
        View view= inflater.inflate(R.layout.fragment_quadrangle_fragmet, container, false);
        length_1_ET=(EditText) view.findViewById(R.id.length_1);
        length_2_ET=(EditText) view.findViewById(R.id.length_2);
        width_1_ET=(EditText) view.findViewById(R.id.width_1);
        width_2_ET=(EditText) view.findViewById(R.id.width_2);
        captionET=(EditText)view.findViewById(R.id.caption);
        result_TV=(TextView) view.findViewById(R.id.result_text);
        resultBtn=(Button)view.findViewById(R.id.result_btn);
        landInfo=new LandInfo();
        manager=new LandlInfoManager(getActivity());

        landInfo.sharedPreferences=getActivity().getSharedPreferences(landInfo.LAND_CALCULATOR,Context.MODE_PRIVATE);
        landInfo.editor=landInfo.sharedPreferences.edit();
        landInfo.editor.commit();

        float desim=landInfo.sharedPreferences.getFloat(landInfo.DESIM,435.6f);
        float gonda=landInfo.sharedPreferences.getFloat(landInfo.GONDA,762.3f);
        landInfo.setGondaUnit(gonda);
        landInfo.setDesimUnit(desim);
        resultBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getActivity().getWindow().getCurrentFocus().getWindowToken(), 0);
                try {
                    landInfo.setLength_1(Float.valueOf(length_1_ET.getText().toString()));
                    landInfo.setLength_2(Float.valueOf(length_2_ET.getText().toString()));
                    landInfo.setWidth_1(Float.valueOf(width_1_ET.getText().toString()));
                    landInfo.setWidth_2(Float.valueOf(width_2_ET.getText().toString()));

                    landInfo.setCaption(captionET.getText().toString());
                    landInfo.setArea( Math.sqrt(landInfo.getLength_1()*landInfo.getLength_2()*landInfo.getWidth_1()*landInfo.getWidth_2()));
                    result_TV.setText("Caption :"+landInfo.getCaption()+newline+"Area : "+ precision.format(landInfo.getArea())+" " +
                            "ft\u00B2"+newline+ "Desim : " + precision.format(landInfo.getArea()/landInfo.getDesimUnit())
                            +newline+ "Gonda : " + precision.format(landInfo.getArea()/landInfo.getGondaUnit()));

                    boolean value=landInfo.sharedPreferences.getBoolean(landInfo.SAVE_HISTORY,true);
                    if(value){
                        landInfo=new LandInfo(landInfo.getCaption(),landInfo.getLength_1(),landInfo.getLength_2(),landInfo.getWidth_1(),
                                landInfo.getWidth_2());
                        manager.addLandHistory(landInfo);
                    }

                }catch (Exception e){
                    Toast.makeText(getActivity(), "Fild is Empty", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }



}
