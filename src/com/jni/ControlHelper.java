package com.jni;

/**
 * Author: wuhaojie
 * E-mail: w19961009@126.com
 * Date: 2016/7/5 12:14
 * Version: 1.0
 */
public class ControlHelper {

    static {
        System.loadLibrary("jni");
    }

    public static native void openLED();
    public static native int[] getTmpHmp();

    public static void main(String[] args) {
        ControlHelper.openLED();
    }

}
