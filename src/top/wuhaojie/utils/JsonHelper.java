package top.wuhaojie.utils;

import com.google.gson.Gson;

/**
 * Author: wuhaojie
 * E-mail: w19961009@126.com
 * Date: 2016/7/8 9:41
 * Version: 1.0
 */
public class JsonHelper {

    private static Gson mGson = new Gson();

    public static String toJson(Object object) {
        return mGson.toJson(object);
    }

}
