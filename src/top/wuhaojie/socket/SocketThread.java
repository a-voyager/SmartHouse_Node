package top.wuhaojie.socket;

import top.wuhaojie.constant.Constants;
import top.wuhaojie.threads.ReadThread;
import top.wuhaojie.threads.WriteThread;
import top.wuhaojie.utils.ConfigUtils;
import top.wuhaojie.utils.LogUtils;

import java.io.*;
import java.net.Socket;

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
            String serverIpAddr = ConfigUtils.readConfig("SERVER_IP_ADDR");
            String serverPortStr = ConfigUtils.readConfig("SERVER_PORT");
            int port = 0;
            if (serverIpAddr == null || serverPortStr == null || serverIpAddr.isEmpty() || serverPortStr.isEmpty()) {
                serverIpAddr = Constants.SERVER_IP_ADDR;
                port = Constants.SERVER_PORT;
            } else {
                port = Integer.valueOf(serverPortStr);
            }
            Socket socket = new Socket(serverIpAddr, port);
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));

            LogUtils.d("连接成功");

            new ReadThread(reader).start();
            new WriteThread(writer).start();

            /*
            Timer timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    try {
                        writer.write("0#Hello World");
                        writer.newLine();
                        writer.flush();
                    } catch (IOException e) {
                        LogUtils.e("写入失败");
                        timer.cancel();
                    }
                }
            };
            timer.schedule(timerTask, 0, 1000);
            while (true) {
                String line = reader.readLine();
                System.out.println(line);
            }*/

        } catch (IOException e) {
            LogUtils.e("连接失败, 请确保服务器已开启");
//            e.printStackTrace();
            this.start();
        }

    }

    public static void main(String[] args) {
        new SocketThread().start();
    }

}
