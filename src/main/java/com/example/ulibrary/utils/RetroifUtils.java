package com.example.ulibrary.utils;


import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;


public class RetroifUtils implements INewWorkInterface {
    public static volatile RetroifUtils retroifUtils;
    private final ApiServise apiServise;


    public RetroifUtils() {
        apiServise = new Retrofit.Builder()
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
    public <T> void getdata(String url, INetCallBack<T> callBack) {
        apiServise.getData(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            Type[] genericInterfaces = callBack.getClass().getGenericInterfaces();
                            Type[] actualTypeArguments = ((ParameterizedType) genericInterfaces[0]).getActualTypeArguments();
                            Type t = actualTypeArguments[0];
                            T Goson = new Gson().fromJson(string, t);
                            callBack.OnSuccess(Goson);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        callBack.OnFail("网络异常：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
