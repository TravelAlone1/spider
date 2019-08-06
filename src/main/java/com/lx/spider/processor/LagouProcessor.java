package com.lx.spider.processor;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: lx
 * @Date: 2019/7/27 0027 上午 9:19
 */
public class LagouProcessor  {

    public List<String> process(String data) {


        ArrayList<String> list = new ArrayList<>();
        //解析数据
        Document document = Jsoup.parse(data);

        //
        Elements elements = document.getElementsByTag("a");
        System.out.println(elements.size());

        for (Element e : elements) {
            String link = e.attr("abs:href");
            if (!list.contains(link) && !"".equals(link)) {
                list.add(link);
            }

        }
        System.out.println(list.size());
        return list;
    }


    public void context(String data) {
      //  System.out.println(data);
//        //解析数据
//        Document document = Jsoup.parse(data);
//        System.out.println(
//                "----------------"
//        );
//        System.out.println(document.select("body > div.position-head > div > div.position-content-l > div > h2").text());


    }
}
