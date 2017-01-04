package com.example.ahmme.landcalculator.quadrangle;


import android.app.Activity;
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
public class QuadrangleFragmet extends Fragment implements View.OnClickListener{

    private EditText length_1_ET;
    private EditText length_2_ET;
    private EditText width_1_ET;
    private EditText width_2_ET;
    private EditText length_1_inch_ET;
    private EditText length_2_inch_ET;
    private EditText width_1_inch_ET;
    private EditText width_2_inch_ET;
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
        getActivity().setTitle(R.string.quadrangle_area);
        length_1_ET=(EditText) view.findViewById(R.id.length_1);
        length_2_ET=(EditText) view.findViewById(R.id.length_2);
        width_1_ET=(EditText) view.findViewById(R.id.width_1);
        width_2_ET=(EditText) view.findViewById(R.id.width_2);

        length_1_inch_ET=(EditText)view.findViewById(R.id.length_1_inch) ;
        length_2_inch_ET=(EditText)view.findViewById(R.id.length_2_inch);
        width_1_inch_ET=(EditText)view.findViewById(R.id.width_1_inch);
        width_2_inch_ET=(EditText)view.findViewById(R.id.width_2_inch);

        captionET=(EditText)view.findViewById(R.id.caption);
        result_TV=(TextView) view.findViewById(R.id.result_text);
        resultBtn=(Button)view.findViewById(R.id.result_btn);
        landInfo=new LandInfo();
        manager=new LandlInfoManager(getActivity());




        resultBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basicInitialization();
                float desim=landInfo.sharedPreferences.getFloat(landInfo.DESIM,435.6f);
                float gonda=landInfo.sharedPreferences.getFloat(landInfo.GONDA,762.3f);
                landInfo.setGondaUnit(gonda);
                landInfo.setDesimUnit(desim);
                result_TV.setText("");
                try {
                   /* landInfo.setLength_1(Float.valueOf(length_1_ET.getText().toString()
                            +"."+landInfo.inchToFtConvert(length_1_inch_ET.getText().toString())));
                    landInfo.setLength_2(Float.valueOf(length_2_ET.getText().toString()
                            +"."+landInfo.inchToFtConvert(length_2_inch_ET.getText().toString())));
                    landInfo.setWidth_1(Float.valueOf(width_1_ET.getText().toString()
                            +"."+landInfo.inchToFtConvert(width_1_inch_ET.getText().toString())));
                    landInfo.setWidth_2(Float.valueOf(width_2_ET.getText().toString()
                            +"."+landInfo.inchToFtConvert(width_2_inch_ET.getText().toString())));*/

                    landInfo.setLength_1(landInfo.getFootValue(length_1_ET.getText().toString(),length_1_inch_ET.getText().toString()));
                    landInfo.setLength_2(landInfo.getFootValue(length_2_ET.getText().toString(),length_2_inch_ET.getText().toString()));
                    landInfo.setWidth_1(landInfo.getFootValue(width_1_ET.getText().toString(),width_1_inch_ET.getText().toString()));
                    landInfo.setWidth_2(landInfo.getFootValue(width_2_ET.getText().toString(),width_2_inch_ET.getText().toString()));

                    landInfo.setCaption(captionET.getText().toString());
                    landInfo.setArea( Math.sqrt(landInfo.getLength_1()*landInfo.getLength_2()*landInfo.getWidth_1()*landInfo.getWidth_2()));
                   /* result_TV.setText(getResources().getString(R.string.title)+" :"+landInfo.getCaption()+newline+
                            getResources().getString(R.string.area) +" : "+ precision.format(landInfo.getArea())+" " + "ft\u00B2"+newline+
                            getResources().getString(R.string.desim)+" : " + precision.format(landInfo.getArea()/landInfo.getDesimUnit())
                            +newline+newline+ R.string.gonda+" : " + precision.format(landInfo.getArea()/landInfo.getGondaUnit())
                           +newline+R.string.length_1+" : "+ landInfo.getLength_1()+R.string.ft+"      "
                           +R.string.width_1+" : "+landInfo.getWidth_1()+R.string.ft
                           +newline+R.string.length_2+" : "+ landInfo.getLength_2()+R.string.ft+"      "
                           +R.string.width_2+" : "+landInfo.getWidth_1()+R.string.ft);*/

                    result_TV.setText(landInfo.getConcatData(R.string.title,landInfo.getCaption())+newline
                            +landInfo.getConcatData(R.string.area,landInfo.getArea(),R.string.ft_sqare)+newline
                           /* +landInfo.getConcatData(R.string.desim,landInfo.getArea()/landInfo.getDesimUnit())+newline
                            +landInfo.getConcatData(R.string.gonda,landInfo.getArea()/landInfo.getGondaUnit())+newline*/

                            +landInfo.getConcatData(R.string.desim,landInfo.getDesimValue())+newline
                            +landInfo.getConcatData(R.string.gonda,landInfo.getGondaValue())+newline
                            +landInfo.getConcatData(R.string.length_1,landInfo.getLength_1(),+R.string.ft)+"      "
                            +landInfo.getConcatData(R.string.width_1,landInfo.getWidth_1(),+R.string.ft)+newline
                            +landInfo.getConcatData(R.string.length_2,landInfo.getLength_2(),+R.string.ft)+"      "
                            +landInfo.getConcatData(R.string.width_2,landInfo.getWidth_2(),+R.string.ft)
                            );

                    boolean value=landInfo.sharedPreferences.getBoolean(landInfo.SAVE_HISTORY,true);
                    if(value){
                        landInfo=new LandInfo(landInfo.getCaption(),landInfo.getLength_1(),landInfo.getLength_2(),landInfo.getWidth_1(),
                                landInfo.getWidth_2());
                        manager.addLandHistory(landInfo);

                    }

                }catch(ArithmeticException e){
                    Toast.makeText(getActivity(), "Give inch value between 0 to 12", Toast.LENGTH_SHORT).show();
                }catch (NumberFormatException e){
                    Toast.makeText(getActivity(), "Fild is Empty", Toast.LENGTH_SHORT).show();
                }

            }

            public void basicInitialization() {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getActivity().getWindow().getCurrentFocus().getWindowToken(), 0);

                landInfo.setContext(getContext());
                landInfo.sharedPreferences=getActivity().getSharedPreferences(landInfo.LAND_CALCULATOR,Context.MODE_PRIVATE);
                landInfo.editor=landInfo.sharedPreferences.edit();
                landInfo.editor.commit();
            }
        });

        return view;
    }



    @Override
    public void onClick(View view) {
        int id =view.getId();
        View view1=view.findViewById(id);
    }
}
