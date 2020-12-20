package com.example.ulibrary.utils;


import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiServise {
   @GET
    Observable<ResponseBody> getData(@Url String url);

}
