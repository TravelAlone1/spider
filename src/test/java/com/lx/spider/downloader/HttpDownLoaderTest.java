package com.lx.spider.downloader;

import com.lx.spider.processor.LagouProcessor;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @Author: lx
 * @Date: 2019/7/27 0027 上午 9:05
 */
public class HttpDownLoaderTest {

    @Test
    public void downLoad()
    {
        String url= "https://www.xicidaili.com/?_id=015448211445";
        HttpDownLoader httpDownLoader = new HttpDownLoader();
        String s = httpDownLoader.downLoad(url);

        LagouProcessor lagouProcessor = new LagouProcessor();
        lagouProcessor.process(s);

    }


}
