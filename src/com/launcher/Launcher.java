package com.launcher;

import top.wuhaojie.socket.SocketThread;
import top.wuhaojie.utils.ConfigUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Author: wuhaojie
 * E-mail: w19961009@126.com
 * Date: 2016/7/8 11:44
 * Version: 1.0
 */
public class Launcher {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome to use!");
        try {
            showMenu(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void showMenu(BufferedReader reader) throws IOException {
        System.out.println("[1] start   [2] config");
        switch (reader.readLine()) {
            case "1":
                new SocketThread().start();
                break;
            case "2":
                System.out.println("\nplease input server ip ('114.215.144.204'):");
                ConfigUtils.saveConfig("SERVER_IP_ADDR", reader.readLine());
                System.out.println("\nnext input server port ('5000'):");
                ConfigUtils.saveConfig("SERVER_PORT", reader.readLine());
                System.out.println("Good Job! press [Enter] to return");
                reader.readLine();
                showMenu(reader);
                break;


        }
    }
}
