package com.example.mipokelist.services.utils;

public interface Callback<T> {
    void onSuccess(T response);
    void onError(String message);
}
