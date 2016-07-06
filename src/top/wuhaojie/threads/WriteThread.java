package top.wuhaojie.threads;

import top.wuhaojie.utils.LogUtils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Author: wuhaojie
 * E-mail: w19961009@126.com
 * Date: 2016/7/6 22:47
 * Version: 1.0
 */
public class WriteThread extends Thread {

    private BufferedWriter mBufferedWriter;

    public WriteThread(BufferedWriter bufferedWriter) {
        mBufferedWriter = bufferedWriter;
    }

    @Override
    public void run() {
        super.run();

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                try {
                    mBufferedWriter.write("0#Hello World");
                    mBufferedWriter.newLine();
                    mBufferedWriter.flush();
                } catch (IOException e) {
                    LogUtils.e("写入失败");
                    timer.cancel();
                }
            }
        };
        timer.schedule(timerTask, 0, 1000);

    }
}
