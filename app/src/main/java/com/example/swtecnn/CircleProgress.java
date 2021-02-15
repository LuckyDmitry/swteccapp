package com.example.swtecnn;

public interface CircleProgress {

    /***
     *
     *increment value by one percent
     */
    void increment();

    /***
     *
     * @return current value
     */
    int getValue();

    /***
     *
     * reset value
     */

    void reset();
}
