package com.example.ulibrary.utils;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class RetroifUtils implements INewWorkInterface {
    public static volatile RetroifUtils retroifUtils;
    private final ApiServise servise;

    public RetroifUtils() {
        servise = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(URLConstant.URLBASE)
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
    public <T> void getData(String url, INetCallBack<T> callBack) {

    }
}
