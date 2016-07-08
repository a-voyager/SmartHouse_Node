package top.wuhaojie.utils;

import top.wuhaojie.constant.Constants;

import java.util.concurrent.ConcurrentLinkedDeque;

import static com.jni.ControlHelper.*;

/**
 * Author: wuhaojie
 * E-mail: w19961009@126.com
 * Date: 2016/7/8 11:19
 * Version: 1.0
 */
public class ControlDao extends Thread {

    private static ConcurrentLinkedDeque<String> mQueue = new ConcurrentLinkedDeque<>();
    private boolean canRun = true;

    public static void addCommand(String command) {
        mQueue.add(command);
//        mQueue.add(command);
    }

    private void control(String command) {

        switch (command) {
            case Constants.COMMAND_OPEN_CURTAIN:
                LogUtils.d("开窗");
//                if (getCurtainState() <= 0) {
                openCurtain();
//                while (getCurtainState() <= 0) {
//                    openCurtain();
//                }
                LogUtils.d("成功！");
//                }
                break;
            case Constants.COMMAND_CLOSE_CURTAIN:
                LogUtils.d("关窗");
                closeCurtain();
//                while (getCurtainState() >= 1) {
//                    closeCurtain();
//                }
                LogUtils.d("成功！");
                break;
            case Constants.COMMAND_OPEN_FAN:
                LogUtils.d("开风扇");
//                if (getFanState() <= 0) {
                openFan();
//                while (getFanState() <= 0) {
//                    openFan();
//                }
                LogUtils.d("成功！");
//                }
                break;
            case Constants.COMMAND_CLOSE_FAN:
                LogUtils.d("关风扇");
                closeFan();
//                while (getFanState() >= 1) {
//                    closeFan();
//                }
                LogUtils.d("成功！");
                break;
            case Constants.COMMAND_OPEN_ALARM:
                LogUtils.d("报警");
                beginAlarm();
//                while (getAlarmSate() <= 0) {
//                    beginAlarm();
//                }
                LogUtils.d("成功！");
                break;
            case Constants.COMMAND_CLOSE_ALARM:
                LogUtils.d("停止报警");
                stopAlarm();
//                while (getAlarmSate() >= 1) {
//                    stopAlarm();
//                }
                LogUtils.d("成功！");
                break;
            default:
                break;
        }
    }


    @Override
    public void run() {
        super.run();
        while (canRun) {
            if (!mQueue.isEmpty()) {
                String s = mQueue.poll();
                control(s);

            }
        }
    }

    public void close() {
        canRun = false;
    }

}
