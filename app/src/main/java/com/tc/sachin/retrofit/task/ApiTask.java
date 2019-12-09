package com.tc.sachin.retrofit.task;

import com.tc.sachin.controller.MainBaseApplication;
import com.tc.sachin.retrofit.ApiCallBack;
import com.tc.sachin.retrofit.OnApiResponseListner;
import com.tc.sachin.retrofit.RetryWithDelay;
import com.tc.sachin.retrofit.call.ApiCall;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class ApiTask {

    private final ApiCall apiCall;

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