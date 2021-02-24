package com.swtec.concurrent;

import com.swtec.data.Weather;

import java.util.List;

public interface OnResultListener<T> {

    void onSuccess(T element);

    void onFailure(Throwable t);

}
