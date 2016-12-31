package com.example.ahmme.landcalculator;


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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmme.landcalculator.quadrangle.LandInfo;
import com.example.ahmme.landcalculator.quadrangle.LandlInfoManager;


/**
 * A simple {@link Fragment} subclass.
 */
@RequiresApi(api = Build.VERSION_CODES.N)
public class TriangleFragment extends Fragment {
    private EditText baseET;
    private EditText heightET;
    private EditText captionET;
    private TextView resultTV;
    private Button resultBtn;
    private Button HistoryBtn;



    String newline = System.getProperty("line.separator");
    DecimalFormat precision = new DecimalFormat("0.00");

    LandInfo landInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_triangle, container, false);
        baseET= (EditText) view.findViewById(R.id.base);
        heightET= (EditText) view.findViewById(R.id.height);
        captionET= (EditText) view.findViewById(R.id.caption_tr);
        resultBtn=(Button)view.findViewById(R.id.result_btn_tr);
        resultTV=(TextView)view.findViewById(R.id.result_text_tr);
        landInfo=new LandInfo();
        landInfo.sharedPreferences=getActivity().getSharedPreferences(landInfo.LAND_CALCULATOR,Context.MODE_PRIVATE);
//        sharedPreferences=getActivity().getSharedPreferences(landInfo.LAND_CALCULATOR,Context.MODE_PRIVATE);
        float desim=landInfo.sharedPreferences.getFloat(landInfo.GONDA,435.6f);
        float gonda=landInfo.sharedPreferences.getFloat(landInfo.GONDA,762.3f);
        landInfo.setGondaUnit(gonda);
        landInfo.setDesimUnit(desim);
        resultBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getActivity().getWindow().getCurrentFocus().getWindowToken(), 0);
               try {
                   landInfo.setLength_1(Float.valueOf(baseET.getText().toString()));
                   landInfo.setLength_2(Float.valueOf(heightET.getText().toString()));
                   landInfo.setCaption(captionET.getText().toString());
                   landInfo.setArea(0.5*landInfo.getLength_1()*landInfo.getLength_2());

                   resultTV.setText("Caption :"+landInfo.getCaption()+newline+"Area : "+ precision.format(landInfo.getArea())
                           +" ft\u00B2"+newline+ "Desim : " + precision.format(landInfo.getArea()/landInfo.getDesimUnit())
                           +newline+ "Gonda : " + precision.format(landInfo.getArea()/landInfo.getGondaUnit()));
               }catch (Exception e){
                   Toast.makeText(getActivity(), "Fild is Empty", Toast.LENGTH_SHORT).show();
               }
            }
        });

        return view;
    }

}
