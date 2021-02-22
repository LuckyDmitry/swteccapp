package com.swtec.concurrent;

import com.swtec.data.Weather;

import java.util.List;

public interface OnResultListener {

    void onSuccess(List<Weather> weathers);

    void onFailure(Throwable t);

}
