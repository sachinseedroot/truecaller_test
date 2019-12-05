package com.tc.sachin.fragments;


import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tc.sachin.R;
import com.tc.sachin.apputilities.AppUtilities;
import com.tc.sachin.controller.MainBaseApplication;
import com.tc.sachin.retrofit.ConsumerCallBack;
import com.tc.sachin.retrofit.OnApiResponseListner;
import com.tc.sachin.retrofit.RequestCode;
import com.tc.sachin.retrofit.RetryWithDelay;
import com.tc.sachin.retrofit.call.ApiCall;
import com.tc.sachin.retrofit.task.ApiTask;

import java.util.HashMap;
import java.util.Observer;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.ReplaySubject;
import retrofit2.Retrofit;


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
                            System.out.println("----onResponseComplete--- " + clsGson);
//                            if (requestCode == RequestCode.Truecaller10thCharacterRequest) {
//                                tcr1.setText(Character.toString(((String) clsGson).charAt(10)));
//                                loadResults(clsGson+"");
                            letsgo();
//                            }


                            if (requestCode == RequestCode.TruecallerEvery10thCharacterRequest) {
                                tcr1.setText(Character.toString(((String) clsGson).charAt(10)));
                            }
                        }

                        @Override
                        public void onResponseError(String errorMessage, int requestCode, int responseCode) {
                            System.out.println("----errorMessage--- " + errorMessage);
                        }
                    });

//                    new ApiTask().getContent(new OnApiResponseListner() {
//                        @Override
//                        public void onResponseComplete(Object clsGson, int requestCode, int responseCode) {
//                            System.out.println("----onResponseComplete--- " + clsGson);
////                            if (requestCode == RequestCode.Truecaller10thCharacterRequest) {
////                                tcr1.setText(Character.toString(((String) clsGson).charAt(10)));
////                            }
//                            loadResults(clsGson+"");
//                        }
//
//                        @Override
//                        public void onResponseError(String errorMessage, int requestCode, int responseCode) {
//                            System.out.println("----onResponseError--- " + errorMessage);
//                        }
//
//
//
//                    });



//                    new ApiTask().getEvery10Content(new OnApiResponseListner() {
//                        @Override
//                        public void onResponseComplete(Object clsGson, int requestCode, int responseCode) {
//                            System.out.println("----onResponseComplete--- " + clsGson);
//
//                            if (requestCode == RequestCode.TruecallerEvery10thCharacterRequest) {
//                                String res = (String) clsGson;
//                                int val = 10;
//                                boolean run = true;
//                                StringBuilder sb = new StringBuilder();
//
//                                while (run) {
//                                    if (res.length()<val) {
//                                        run = false;
//                                        tcr2.setText(sb+"");
//                                        break;
//                                    } else {
//                                        sb.append(Character.toString(res.charAt(val)));
//                                    }
//                                    val = val + 10;
//                                }
//                            }
//                        }
//
//                        @Override
//                        public void onResponseError(String errorMessage, int requestCode, int responseCode) {
//                            System.out.println("----onResponseError--- " + errorMessage);
//                        }
//
//
//
//                    });
                } else {
                    Toast.makeText(mcontext, "No internet connection", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loadResults(final String resposne){
        CompositeDisposable mCompositeDisposable = new CompositeDisposable();
        ReplaySubject replaySubject = ReplaySubject.create();
        replaySubject.skip(9).buffer(1,10);


//        mCompositeDisposable.add(replaySubject
//                .firstElement()
//                .map(new Function() {
//                    @Override
//                    public Object apply(Object o) throws Exception {
//                        char[] respo = resposne.toCharArray();
//                        return Observable.fromArray(respo);
//                    }
//                })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeWith(new DisposableObserver<String>() {
//                    @Override
//                    public void onComplete() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                    }
//
//                    @Override
//                    public void onNext(String value) {
//                    }
//                }));
    }

    public void letsgo(){
        CompositeDisposable disposables = new CompositeDisposable();

        ReplaySubject replaySubject = ReplaySubject.create();
        replaySubject.skip(9).buffer(1,10);

// adding an Observable to the disposable
        disposables.add(sampleObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<String>() {
                    @Override
                    public void onComplete() {
                        System.out.println("----onComplete--- ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("----Throwable--- "+e);
                    }

                    @Override
                    public void onNext(String value) {
                        System.out.println("----onNext--- "+value);
                    }
                })
        );



    }
    static Observable<String> sampleObservable() {
        return Observable.defer(new Callable<ObservableSource<? extends String>>() {
            @Override
            public ObservableSource<? extends String> call() throws Exception {
                // Do some long running operation
//                    SystemClock.sleep(2000);
                return Observable.just("one", "two", "three", "four", "five");
            }
        });
    }
}