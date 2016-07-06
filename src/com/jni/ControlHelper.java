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

    /**
     * 获取当前温度
     *
     * @return 整数类型 单位: 摄氏度
     */
    public static native int getTemperature();

    /**
     * 获取当前湿度
     *
     * @return 整数类型
     */
    public static native int getHumidity();

    /**
     * 获取风速
     *
     * @return 浮点数类型 单位: 米每秒
     */
    public static native float getWindSpeed();

    /**
     * 获取风向
     *
     * @return 浮点数类型
     */
    public static native float getWindDirection();

    /**
     * 获取窗帘状态
     *
     * @return 整数类型 0代表遮阳状态 1代表透光状态
     */
    public static native int getCurtainState();

    /**
     * 当前是否安全
     *
     * @return true 安全 false 不安全
     */
    public static native boolean getIsSafe();

    /**
     * 获取烟雾浓度
     *
     * @return 整数类型
     */
    public static native int getSmoke();

    /**
     * 获取超声波距离
     *
     * @return 浮点数类型 单位: 米
     */
    public static native float getUltrasonicWave();


    /**
     * 关闭窗帘 遮光
     */
    public static native void closeCurtain();

    /**
     * 开启窗帘 透光
     */
    public static native void openCurtain();

    /**
     * 关闭风扇
     */
    public static native void closeFan();

    /**
     * 开启风扇
     */
    public static native void openFan();

    /**
     * 自检硬件
     *
     * @return 硬件是否正常工作
     */
    public static native boolean checkHardware();

}
