package com.example.ahmme.landcalculator;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ahmme.landcalculator.quadrangle.LandAdapter;
import com.example.ahmme.landcalculator.quadrangle.LandInfo;
import com.example.ahmme.landcalculator.quadrangle.LandlInfoManager;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {
    ListView listView;
    ArrayList<LandInfo> arrayList;
    LandlInfoManager manager;
    Adapter adapter;
    Button clearHistryBtn;
    LandInfo landInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.fragment_history, container, false);
        getActivity().setTitle(R.string.history);
        manager=new LandlInfoManager(getActivity());
        listView=(ListView)view.findViewById(R.id.list_view);
        clearHistryBtn=(Button)view.findViewById(R.id.clea_history);

        arrayList=manager.getAllLandHistory();
        adapter = new LandAdapter(getActivity(), arrayList);
        listView.setAdapter((ListAdapter) adapter);
        clearHistryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("Delete All")
                        .setMessage("Are you sure you want to delete all History?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    manager.deleteAllHistory();
                                    arrayList=manager.getAllLandHistory();
                                    adapter = new LandAdapter(getActivity(), arrayList);
                                    listView.setAdapter((ListAdapter) adapter);
                                } catch (Exception e) {
                                    Toast.makeText(getActivity(), "All Data Deleted", Toast.LENGTH_SHORT).show();

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
