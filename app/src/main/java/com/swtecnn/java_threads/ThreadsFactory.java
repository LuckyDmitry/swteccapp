package com.swtecnn.java_threads;

public class ThreadsFactory {

    MyThread thread;

    public ThreadsFactory(MyThread thread){
        this.thread = thread;
    }

    void startThread(){
        thread.runMyThread();
    }
}
