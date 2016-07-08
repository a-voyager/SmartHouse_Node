package top.wuhaojie.utils;

import top.wuhaojie.constant.Constants;

import static com.jni.ControlHelper.*;

/**
 * Author: wuhaojie
 * E-mail: w19961009@126.com
 * Date: 2016/7/8 11:19
 * Version: 1.0
 */
public class ControlDao {

    public static void control(String command) {

        switch (command) {
            case Constants.COMMAND_OPEN_CURTAIN:
                LogUtils.d("开窗");
                if (getCurtainState() == 0) {
                    openCurtain();
                    LogUtils.d("成功！");
                }
                break;
            case Constants.COMMAND_CLOSE_CURTAIN:
                LogUtils.d("关窗");
                if (getCurtainState() == 1) {
                    closeCurtain();
                    LogUtils.d("成功！");
                }
                break;
            case Constants.COMMAND_OPEN_FAN:
                LogUtils.d("开风扇");
                if (getFanState() == 0) {
                    openFan();
                    LogUtils.d("成功！");
                }
                break;
            case Constants.COMMAND_CLOSE_FAN:
                LogUtils.d("关风扇");
                if (getFanState() == 1) {
                    closeFan();
                    LogUtils.d("成功！");
                }
                break;
            case Constants.COMMAND_OPEN_ALARM:
                LogUtils.d("报警");
                if (getAlarmSate() == 0) {
                    beginAlarm();
                    LogUtils.d("成功！");
                }
                break;
            case Constants.COMMAND_CLOSE_ALARM:
                LogUtils.d("停止报警");
                if (getAlarmSate() == 1) {
                    stopAlarm();
                    LogUtils.d("成功！");
                }
                break;
            default:
                break;
        }
    }

}
