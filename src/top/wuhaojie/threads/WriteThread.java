package top.wuhaojie.threads;

import com.jni.ControlHelper;
import top.wuhaojie.entities.InfoItem;
import top.wuhaojie.utils.JsonHelper;
import top.wuhaojie.utils.LogUtils;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * 将信息写入到远程服务端
 * Author: wuhaojie
 * E-mail: w19961009@126.com
 * Date: 2016/7/6 22:47
 * Version: 1.0
 */
public class WriteThread extends Thread {

    private BufferedWriter mBufferedWriter;
    private boolean mCanRunning;

    private static boolean canGetInfo = true;

    public static void setCanGetInfo(boolean can) {
        canGetInfo = can;
    }

    public WriteThread(BufferedWriter bufferedWriter) {
        mBufferedWriter = bufferedWriter;
    }

    @Override
    public void run() {
        super.run();

//        Timer timer = new Timer();
//        TimerTask timerTask = new TimerTask() {
//            @Override
//            public void run() {
//                try {
//                    // DEBUG
////                    InfoItem info = mInfoItem;
//                    InfoItem info = getInfo();
//                    String json = JsonHelper.toJson(info);
//                    mBufferedWriter.write(json);
//                    mBufferedWriter.newLine();
//                    mBufferedWriter.flush();
//                } catch (IOException e) {
//                    LogUtils.e("写入失败");
//                    timer.cancel();
//                }
//            }
//        };
//        timer.schedule(timerTask, 0, 60);

        mCanRunning = true;
        while (mCanRunning) {
            if (canGetInfo) {
                InfoItem info = getInfo();
                String json = JsonHelper.toJson(info);
                try {
                    mBufferedWriter.write(json);
                    mBufferedWriter.newLine();
                    mBufferedWriter.flush();
                } catch (IOException e) {
                    LogUtils.e("发送数据至服务器失败, 请检查服务器是否断开连接");
                    mCanRunning = false;
                }
            }
        }


    }

    private InfoItem mInfoItem = new InfoItem(0, 0, "26", "50", System.currentTimeMillis());

    private InfoItem getInfo() {
//        int temperature = 25;
        try {
            int temperature = ControlHelper.getTemperature();
            mInfoItem.setTemperature(temperature + "");
        } catch (Exception e) {
            LogUtils.e("温度读取失败");
        }

//        int humidity = 50;
        try {
            int humidity = ControlHelper.getHumidity();
            mInfoItem.setHumidity(humidity + "");
        } catch (Exception e) {
            LogUtils.e("相对湿度读取失败");
        }

//        int smoke = 800;
        try {
            int smoke = ControlHelper.getSmoke();
            mInfoItem.setSmoke(smoke + "");
        } catch (Exception e) {
            LogUtils.e("烟雾读取失败");
        }

//        float ultrasonicWave = 0;
        try {
            float ultrasonicWave = ControlHelper.getUltrasonicWave();
            mInfoItem.setUltrasonicWave(ultrasonicWave + "");
        } catch (Exception e) {
            LogUtils.e("声波读取失败");
        }

//        float windDirection = 0;
        try {
            float windDirection = ControlHelper.getWindDirection();
            mInfoItem.setWindDirection(windDirection + "");
        } catch (Exception e) {
            LogUtils.e("风向读取失败");
        }

//        float windSpeed = 0;
        try {
            float windSpeed = ControlHelper.getWindSpeed();
            mInfoItem.setWindSpeed(windSpeed + "");
        } catch (Exception e) {
            LogUtils.e("风速读取失败");
        }

        boolean isSafe = true;
        try {
//            boolean isSafe = ControlHelper.getIsSafe();
            mInfoItem.setIsSafe(isSafe + "");
        } catch (Exception e) {
            LogUtils.e("是否安全读取失败");
        }

//        int curtainState = 1;
//        try {
//            int curtainState = ControlHelper.getCurtainState();
//            mInfoItem.setCurtainState(curtainState + "");
//        } catch (Exception e) {
//            LogUtils.e("窗帘状态读取失败");
//        }

        mInfoItem.setTimeStamp(System.currentTimeMillis());

        System.out.println(mInfoItem.toString());

        try {
            Thread.sleep(60);
        } catch (InterruptedException e) {

            LogUtils.e("休眠失败");
        }

        return mInfoItem;
    }


}
