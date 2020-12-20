package com.example.ulibrary.utils;


import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.http.Url;

public class RetroifUtils implements INewWorkInterface {
    public static volatile RetroifUtils retroifUtils;
    private final ApiServise apiServise;


    public RetroifUtils() {
        apiServise = new Retrofit.Builder()
                .baseUrl(URLConstant.URLBASE)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiServise.class);

    }

    public static RetroifUtils getRetroifUtils() {
        if (retroifUtils == null) {
            synchronized (RetroifUtils.class) {
                if (retroifUtils == null) {
                    retroifUtils = new RetroifUtils();
                }
            }
        }
        return retroifUtils;
    }

    @Override
    public <T> void getdata(String url, INetCallBack<T> callBack) {
        
    }
}
