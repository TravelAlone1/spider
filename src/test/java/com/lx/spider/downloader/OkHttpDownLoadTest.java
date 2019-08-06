package com.lx.spider.downloader;

import org.junit.Test;

/**
 * @Author: lx
 * @Date: 2019/7/30 0030 下午 19:37
 */
public class OkHttpDownLoadTest {

    @Test
    public void test(){
        String url="https://www.xicidaili.com/?_id=015448211445";
        OKHttpDownLoader okHttpDownLoader = new OKHttpDownLoader();
        System.out.println(okHttpDownLoader.downLoad(url));
    }
}
