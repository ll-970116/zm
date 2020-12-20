package com.example.ulibrary.utils;

public class RetroifUtils implements INewWorkInterface{
    public static  volatile  RetroifUtils retroifUtils;

    public RetroifUtils() {

    }

    public static RetroifUtils getRetroifUtils() {
        if (retroifUtils==null){
            
        }
        return retroifUtils;
    }

    @Override
    public <T> void getData(String url, INetCallBack<T> callBack) {

    }
}
