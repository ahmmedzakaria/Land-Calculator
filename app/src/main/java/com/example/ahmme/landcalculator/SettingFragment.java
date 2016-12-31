package com.example.ahmme.landcalculator;


import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.NotificationCompatSideChannelService;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.example.ahmme.landcalculator.R;
import com.example.ahmme.landcalculator.quadrangle.LandAdapter;
import com.example.ahmme.landcalculator.quadrangle.LandInfo;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment {
    private EditText desimUnitET;
    private EditText gondaUnitEt;
    private Button saveUnitBtn;
    private CheckBox checkBox;
    LandInfo landInfo;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_setting, container, false);
        desimUnitET= (EditText) view.findViewById(R.id.desim_unit);
        gondaUnitEt= (EditText) view.findViewById(R.id.gonda_unit);
        saveUnitBtn=(Button) view.findViewById(R.id.save_units);
        checkBox=(CheckBox)view.findViewById(R.id.checkBox);
        landInfo=new LandInfo();
        landInfo.sharedPreferences= getActivity().getSharedPreferences(landInfo.LAND_CALCULATOR, Context.MODE_PRIVATE);
        landInfo.editor=landInfo.sharedPreferences.edit();
        boolean value=landInfo.sharedPreferences.getBoolean(landInfo.SAVE_HISTORY,true);
        desimUnitET.setText(String.valueOf(landInfo.sharedPreferences.getFloat(landInfo.DESIM,435.6f)));
        gondaUnitEt.setText(String.valueOf(landInfo.sharedPreferences.getFloat(landInfo.GONDA,762.3f)));
        if(value){
            checkBox.setChecked(true);
        }else {
            checkBox.setChecked(false);
        }
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkBox.isChecked()){
                    landInfo.editor.putBoolean(landInfo.SAVE_HISTORY,true);

                }else {
                    landInfo.editor.putBoolean(landInfo.SAVE_HISTORY,false);


                }
                landInfo.editor.commit();
            }
        });


        saveUnitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(getActivity())
                        .setTitle("Change default Unit")
                        .setMessage("All calculation will follow new Unit?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(getActivity().getWindow().getCurrentFocus().getWindowToken(), 0);
                                try {
                                    landInfo.editor.putFloat(landInfo.DESIM,Float.valueOf(desimUnitET.getText().toString()));
                                    landInfo.editor.putFloat(landInfo.GONDA,Float.valueOf(gondaUnitEt.getText().toString()));
                                    landInfo.editor.commit();

//                                    landInfo.setDesimUnit(Float.valueOf(desimUnitET.getText().toString()));
//                                    landInfo.setGondaUnit(Float.valueOf(gondaUnitEt.getText().toString()));
                                } catch (Exception e) {

                                }
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            }
        });
        return view;
    }

}
