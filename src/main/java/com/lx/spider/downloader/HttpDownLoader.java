package com.lx.spider.downloader;

import com.lx.spider.entity.ProxyServer;

import java.io.InputStream;
import java.net.*;

/**
 * @Author: lx
 * @Date: 2019/7/27 0027 上午 8:41
 */
public class HttpDownLoader implements DownLoader {
    @Override
    public String downLoad(String httpUrl) {
        String message="";
        try {
            URL url = new URL(httpUrl);
            Proxy proxy=null;
            HttpURLConnection connection =null;
            ProxyServer proxyServer=com.lx.spider.entity.Proxy.getServer();
            if (proxyServer != null){
                proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyServer.getIp(), proxyServer.getPort()));
            }
            if (proxy !=null){
                connection =(HttpURLConnection) url.openConnection(proxy);
            }else {
                connection =(HttpURLConnection) url.openConnection();
            }

            connection.setRequestMethod("GET");


            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.108 Safari/537.36");
            connection.setConnectTimeout(1000*15);
            connection.setReadTimeout(1000*15);
            connection.connect();
            if(connection.getResponseCode()==200){
                InputStream inputStream = connection.getInputStream();

                byte[] bytes = new byte[1024];
                StringBuffer stringBuffer = new StringBuffer();
                int length=0;
                while((length=inputStream.read(bytes))!=-1){
                    String s = new String(bytes,0,length);
                    stringBuffer.append(s);
                }
                message= stringBuffer.toString();
                inputStream.close();

            }
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }



}























