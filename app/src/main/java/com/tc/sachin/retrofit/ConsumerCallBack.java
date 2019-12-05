package com.tc.sachin.retrofit;


import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class ConsumerCallBack implements Consumer {

    private OnApiResponseListner onApiResponseListner;
    private int requestCode = 0;

    public ConsumerCallBack(OnApiResponseListner onApiResponseListner, int requestCode) {
        this.onApiResponseListner = onApiResponseListner;
        this.requestCode = requestCode;
    }

//    @Override
//    public void onSubscribe(Disposable d) {
//
//    }
//
//    @Override
//    public void onNext(Object t) {
//        if (onApiResponseListner != null)
//            onApiResponseListner.onResponseComplete(t, requestCode, 200);
//        else {
//            onApiResponseListner.onResponseError("Error", requestCode, 4444);
//        }
//    }
//
//    @Override
//    public void onError(Throwable t) {
//        if (onApiResponseListner != null)
//            onApiResponseListner.onResponseError(t.getMessage() + "", requestCode, 3333);
//    }
//
//    @Override
//    public void onComplete() {
//
//    }


    @Override
    public void accept(Object o) throws Exception {
        onApiResponseListner.onResponseComplete(o, requestCode, 200);
    }
}
