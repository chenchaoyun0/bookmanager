package com.sttx.bookmanager.util.pages;

public class ThreadLocalContext {
    private static final ThreadLocal<Long> controllerExcuTime = new ThreadLocal<Long>();

    /**
     * @return the controllerexcutime
     */
    public static ThreadLocal<Long> getControllerexcutime() {
        return controllerExcuTime;
    }

}
