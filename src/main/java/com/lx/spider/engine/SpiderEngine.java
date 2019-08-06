package com.lx.spider.engine;

import com.lx.spider.downloader.DownLoader;
import com.lx.spider.downloader.HttpDownLoader;
import com.lx.spider.downloader.OKHttpDownLoader;
import com.lx.spider.entity.WebPage;
import com.lx.spider.processor.DoubanProcessor;
import com.lx.spider.processor.Processor;
import com.lx.spider.processor.XiCiProcessor;
import com.lx.spider.scheduler.LocalScheduler;
import com.lx.spider.scheduler.Scheduler;

import java.util.List;

/**
 * @Author: lx
 * @Date: 2019/7/27 0027 上午 9:55
 */
public class SpiderEngine {


    Scheduler scheduler = new LocalScheduler();
    DownLoader downLoader = new HttpDownLoader();
    Processor processor = new DoubanProcessor();

    private int threadNum = 3;

    public void test() {
        String startUrl = "https://movie.douban.com/cinema/nowplaying/ganzhou/";

        scheduler.put(startUrl);
        for (int i = 0; i < threadNum; i++) {
            new Thread(new SpiderTask(scheduler, downLoader, processor)).start();
        }
    }

    public void getXiCi() {

        String url = "https://www.xicidaili.com/nt/";
        scheduler.put(url);

        XiCiProcessor xiCiProcessor = new XiCiProcessor();


        String data = downLoader.downLoad(url);
        xiCiProcessor.process(data);
    }

    public static void main(String[] args) {

        SpiderEngine spiderEngine = new SpiderEngine();
        //spiderEngine.getXiCi();
        spiderEngine.test();


    }
}
