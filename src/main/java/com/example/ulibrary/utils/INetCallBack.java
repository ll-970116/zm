package com.example.ulibrary.utils;

public interface INetCallBack<T> {
    public void OnSuccess (T t);
    public void  OnFail(String err);
}
