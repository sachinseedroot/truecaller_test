package com.tc.sachin.retrofit.call;

import com.tc.sachin.retrofit.WebAPI;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiCall {


    @GET(WebAPI.GET_CONTENT)
    Observable<String> getContent();

}