package com.tc.sachin.fragments;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tc.sachin.R;
import com.tc.sachin.retrofit.OnApiResponseListner;
import com.tc.sachin.retrofit.task.ApiTask;

import java.util.HashMap;


public class HomePagefragment extends Fragment {
    private Context mcontext;
    private HashMap<String, String> noCacheHeaders;
    public String className = HomePagefragment.class.getSimpleName();
    private Button btn_begin;
    private TextView tcr1,tcr2,tcr3;

    public static HomePagefragment newInstance() {
        HomePagefragment f = new HomePagefragment();

        // Supply index input as an argument.
        Bundle args = new Bundle();
        f.setArguments(args);

        return f;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mcontext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homepage, container, false);
//        url = getArguments().getString("url", "");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        View views = getActivity().getCurrentFocus();
//        if (views != null) {
//            InputMethodManager imm = (InputMethodManager) mcontext.getSystemService(Context.INPUT_METHOD_SERVICE);
//            imm.hideSoftInputFromWindow(views.getWindowToken(), 0);
//        }
        initializeView(view);

    }

    private void initializeView(View view){
        tcr1 = (TextView) view.findViewById(R.id.tcr1);
        tcr2 = (TextView) view.findViewById(R.id.tcr2);
        tcr3 = (TextView) view.findViewById(R.id.tcr3);
        btn_begin = (Button)view.findViewById(R.id.btn_begin);
        btn_begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ApiTask().getContent(new OnApiResponseListner() {
                    @Override
                    public void onResponseComplete(Object clsGson, int requestCode, int responseCode) {
                        System.out.println("----onResponseComplete--- "+clsGson);
                        tcr1.setText(clsGson+"");
                    }

                    @Override
                    public void onResponseError(String errorMessage, int requestCode, int responseCode) {
                        System.out.println("----onResponseError--- "+errorMessage);
                    }
                });
            }
        });
    }
}