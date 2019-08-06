package com.lx.spider.processor;

import com.lx.spider.entity.WebPage;

import java.util.List;

/**
 * @Author: lx
 * @Date: 2019/7/27 0027 上午 11:45
 */
public class XiCiProcessor implements Processor {
    @Override
    public List<String> process(String data) {
        return null;
    }

    @Override
    public void parseItem(WebPage data) {



        //ProxyServer proxyServer = new ProxyServer();


        //Document document = Jsoup.parse(data);
        //Elements elements = document.select("#ip_list > tbody > tr");

        /*for(int i=2;i<elements.size();i++){
            Element element = elements.get(i);
            Element td2=element.selectFirst(" td:nth-child(2)");
            Element td3 = element.selectFirst("td:nth-child(3)");
            //System.out.println(td2.text());
            if (td2!=null && td3!= null ){
                System.out.println(td2.text()+":"+td3.text());
                ProxyServer proxyServer = new ProxyServer();
                proxyServer.setIp(td2.text());
                proxyServer.setPort(Integer.parseInt(td3.text()));

                try {
                    if (NetStateUtil.isReachable(proxyServer.getIp(),proxyServer.getPort())){
                        Proxy.addServer(proxyServer);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }*/




    }
}
