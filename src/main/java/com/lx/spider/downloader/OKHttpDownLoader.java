package com.lx.spider.downloader;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @Author: lx
 * @Date: 2019/7/27 0027 上午 9:12
 */

public class OKHttpDownLoader implements DownLoader {
    @Override
    public String downLoad(String url) {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(5000, TimeUnit.SECONDS)
                .readTimeout(5000, TimeUnit.SECONDS)
                .build();

        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();


        Call call = client.newCall(request);
        Response response = null;

        String str = null;

        try {
            response = call.execute();
            if (response.isSuccessful()) {
                str = response.toString();

            }
            System.out.println(str);
            return str;


        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



}