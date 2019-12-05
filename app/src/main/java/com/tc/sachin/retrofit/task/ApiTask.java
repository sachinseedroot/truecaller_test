package com.tc.sachin.retrofit.task;

import com.tc.sachin.controller.MainBaseApplication;
import com.tc.sachin.retrofit.ApiCallBack;
import com.tc.sachin.retrofit.ConsumerCallBack;
import com.tc.sachin.retrofit.OnApiResponseListner;
import com.tc.sachin.retrofit.RequestCode;
import com.tc.sachin.retrofit.RetryWithDelay;
import com.tc.sachin.retrofit.call.ApiCall;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.ReplaySubject;
import retrofit2.Retrofit;

public class ApiTask {

    private final ApiCall apiCall;
    private ReplaySubject<Object> replaySubject;

    public ApiTask() {
        Retrofit retrofit = MainBaseApplication.getRetrofitInstance();
        apiCall = retrofit.create(ApiCall.class);
    }

    public Observable<?> getContent(OnApiResponseListner onApiResponseListner) {
        Observable<?> callApi = apiCall.getContent()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(0, 3000));
        callApi.subscribe(new ApiCallBack(onApiResponseListner, 100));
        return callApi;
    }
}