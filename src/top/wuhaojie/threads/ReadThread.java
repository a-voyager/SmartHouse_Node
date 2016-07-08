package top.wuhaojie.threads;

import top.wuhaojie.utils.ControlDao;
import top.wuhaojie.utils.LogUtils;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * 读取服务端发来的控制信息
 * Author: wuhaojie
 * E-mail: w19961009@126.com
 * Date: 2016/7/6 22:46
 * Version: 1.0
 */
public class ReadThread extends Thread {

    private BufferedReader mBufferedReader;
    private ControlDao mControlDao = new ControlDao();

    public ReadThread(BufferedReader bufferedReader) {
        mBufferedReader = bufferedReader;
    }

    @Override
    public void run() {
        super.run();
        mControlDao.start();
        while (true) {
            String line = null;
            try {
                line = mBufferedReader.readLine();
                ControlDao.addCommand(line);
            } catch (IOException e) {
                LogUtils.e(getClass().getSimpleName(), "读取失败");
            }
            System.out.println(line);
        }
    }
}
