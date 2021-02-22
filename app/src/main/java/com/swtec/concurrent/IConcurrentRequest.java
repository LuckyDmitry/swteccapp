package com.swtec.concurrent;

public interface IConcurrentRequest {

    void setOnResultListener(OnResultListener listener);

    void removeOnResultLister();

    void performRequest();

}
