package top.wuhaojie.socket;

import top.wuhaojie.constant.Constants;
import top.wuhaojie.utils.LogUtils;

import java.io.*;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Author: wuhaojie
 * E-mail: w19961009@126.com
 * Date: 2016/7/5 14:58
 * Version: 1.0
 */
public class SocketThread extends Thread {

    @Override
    public void run() {
        super.run();
        try {
            Socket socket = new Socket(Constants.SERVER_IP_ADDR, Constants.SERVER_PORT);
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));

            LogUtils.d("连接成功");

            Timer timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    try {
                        writer.write("Hello World");
                        writer.newLine();
                        writer.flush();
                    } catch (IOException e) {
                        LogUtils.e("写入失败");
                        timer.cancel();
                    }
                }
            };
            timer.schedule(timerTask, 0, 1000);
        } catch (IOException e) {
            LogUtils.e("连接失败");
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new SocketThread().start();
    }

}
