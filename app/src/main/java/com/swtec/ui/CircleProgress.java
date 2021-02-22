package com.swtec.ui;

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
