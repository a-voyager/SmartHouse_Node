package top.wuhaojie.threads;

import top.wuhaojie.utils.LogUtils;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Author: wuhaojie
 * E-mail: w19961009@126.com
 * Date: 2016/7/6 22:46
 * Version: 1.0
 */
public class ReadThread extends Thread {

    private BufferedReader mBufferedReader;

    public ReadThread(BufferedReader bufferedReader) {
        mBufferedReader = bufferedReader;
    }

    @Override
    public void run() {
        super.run();
        while (true) {
            String line = null;
            try {
                line = mBufferedReader.readLine();
            } catch (IOException e) {
                LogUtils.e(getClass().getSimpleName(), "读取失败");
            }
            System.out.println(line);
        }
    }
}
