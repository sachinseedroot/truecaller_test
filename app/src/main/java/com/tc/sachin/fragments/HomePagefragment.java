package com.tc.sachin.fragments;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tc.sachin.R;
import com.tc.sachin.retrofit.OnApiResponseListner;
import com.tc.sachin.retrofit.task.ApiTask;

import java.util.HashMap;


public class HomePagefragment extends Fragment implements OnApiResponseListner {
    private Context mcontext;
    private HashMap<String, String> noCacheHeaders;
    public String className = HomePagefragment.class.getSimpleName();

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

        new ApiTask().getContent(this);
    }

    @Override
    public void onResponseComplete(Object clsGson, int requestCode, int responseCode) {
        System.out.println("----clsGson--- "+clsGson);
    }

    @Override
    public void onResponseError(String errorMessage, int requestCode, int responseCode) {
        System.out.println("----errorMessage-- "+errorMessage);
    }
}