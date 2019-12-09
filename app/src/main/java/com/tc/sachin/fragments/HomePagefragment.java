package com.tc.sachin.fragments;


import android.app.ActionBar;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tc.sachin.R;
import com.tc.sachin.apputilities.AppUtilities;
import com.tc.sachin.retrofit.OnApiResponseListner;
import com.tc.sachin.retrofit.task.ApiTask;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;


public class HomePagefragment extends Fragment {
    private Context mcontext;
    private HashMap<String, String> noCacheHeaders;
    public String className = HomePagefragment.class.getSimpleName();
    private Button btn_begin;
    private TextView tcr1, tcr2, tcr3;

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

    private void initializeView(View view) {
        tcr1 = (TextView) view.findViewById(R.id.tcr1);
        tcr2 = (TextView) view.findViewById(R.id.tcr2);
        tcr3 = (TextView) view.findViewById(R.id.tcr3);
        btn_begin = (Button) view.findViewById(R.id.btn_begin);
        btn_begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AppUtilities.isNetworkAvailable(mcontext)) {
                    tcr1.setText("Loading...");
                    tcr2.setText("Loading...");
                    tcr3.setText("Loading...");

                    new ApiTask().getContent(new OnApiResponseListner() {
                        @Override
                        public void onResponseComplete(Object clsGson, int requestCode, int responseCode) {
                            new Task1().execute((String) clsGson);
                            new Task2().execute((String) clsGson);
                            new Task3().execute((String) clsGson);
                        }

                        @Override
                        public void onResponseError(String errorMessage, int requestCode, int responseCode) {
                           Toast.makeText(mcontext,errorMessage,Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(mcontext, "No internet connection", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public class Task1 extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... s) {
            return Character.toString(((String) s[0]).charAt(10));
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            tcr1.setText(s);
        }
    }

    private class Task2 extends AsyncTask<String, Void, StringBuilder> {

        @Override
        protected StringBuilder doInBackground(String... strings) {
            int val = 10;
            boolean run = true;
            StringBuilder sb = new StringBuilder();

            while (run) {
                if (strings[0].length() < val) {
                    run = false;
                    return sb;

                } else {
                    sb.append(Character.toString(strings[0].charAt(val)));
                }
                val = val + 10;
            }
            return null;
        }

        @Override
        protected void onPostExecute(StringBuilder s) {
            super.onPostExecute(s);
            tcr2.setText(s.toString());
        }
    }

    private class Task3 extends AsyncTask<String,Void,StringBuilder>{

        @Override
        protected StringBuilder doInBackground(String... strings) {
            String[]abc = strings[0].split("\\s+");
            HashMap<String,Integer> stringIntegerHashMap = new HashMap<>();
            for(int i=0;i<abc.length;i++){
                if(stringIntegerHashMap.containsKey(abc[i])) {
                    stringIntegerHashMap.put(abc[i], stringIntegerHashMap.get(abc[i]) + 1);
                }else{
                    stringIntegerHashMap.put(abc[i], 1);
                }
            }
            StringBuilder sb = new StringBuilder();
            for ( Map.Entry<String, Integer> entry : stringIntegerHashMap.entrySet()) {
                String key = entry.getKey();
                String val = Integer.toString(entry.getValue());
                sb = sb.append(key).append(" = ").append(val).append("\n");

            }
            return sb;
        }

        @Override
        protected void onPostExecute(StringBuilder aVoid) {
            super.onPostExecute(aVoid);
            tcr3.setText(aVoid.toString());
        }
    }
}