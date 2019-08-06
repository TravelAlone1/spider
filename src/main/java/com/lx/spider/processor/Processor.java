package com.lx.spider.processor;

import com.lx.spider.entity.WebPage;

import java.util.List;

/**
 * @Author: lx
 * @Date: 2019/7/27 0027 上午 9:16
 */
public interface Processor {

    public List<String> process(String data);

    public void parseItem(WebPage page);
}
