package com.lx.spider.engine;

import com.google.common.base.Strings;
import com.lx.spider.downloader.DownLoader;
import com.lx.spider.entity.WebPage;
import com.lx.spider.processor.Processor;
import com.lx.spider.scheduler.Scheduler;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: lx
 * @Date: 2019/8/1 22:35
 */
public class SpiderTask implements Runnable {

    private Scheduler scheduler;
    private DownLoader downLoader;
    private Processor processor;
    private boolean isRunning = true;

    public SpiderTask(Scheduler scheduler, DownLoader downLoader, Processor processor) {
        this.scheduler = scheduler;
        this.downLoader = downLoader;
        this.processor = processor;
    }

    public void stop() {
        isRunning = false;
    }

    @Override
    public  void run() {
        while (isRunning) {
            System.out.println(Thread.currentThread().getName() + ">>>>>>>>>>>>>>>>>>>>>>>>");
            String url = scheduler.get();
            if (Strings.isNullOrEmpty(url)) {
                try {
                    Thread.sleep(3*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }

            String content = downLoader.downLoad(url);
            String regEx = "https:\\/\\/movie.douban.com\\/subject\\/([0-9]{7}|[0-9]{8})\\/\\?from=playing_poster";
            String regEx1= "https:\\/\\/movie.douban.com\\/subject\\/([0-9]{7}|[0-9]{8})\\/\\?from=subject-page";
            Pattern p = Pattern.compile(regEx);
            Pattern pattern = Pattern.compile(regEx1);
            Matcher matcher = p.matcher(url);
            Matcher matcher1 = pattern.matcher(url);

            boolean rs = matcher.find();
            boolean b = matcher1.find();
            System.out.println(rs);

            //System.out.println(content);
            WebPage webPage = null;
            if (!Strings.isNullOrEmpty(content)&& (rs || b  || url=="https://movie.douban.com/cinema/nowplaying/ganzhou/")) {
                webPage = new WebPage();
                webPage.setUrl(url);
                webPage.setContent(content);
            }

            if (webPage != null) {
                if (webPage.getUrl().startsWith("https://movie.douban.com/subject/") ) {
                    processor.parseItem(webPage); // 解析具体内容
                }
// 抓取链接
                List<String> urls = processor.process(content);
                for (String u : urls) {
                    if (u.startsWith("https://movie.douban.com/subject/")) {
                        scheduler.put(u);
                    }
                }
            }
        }
    }
}
