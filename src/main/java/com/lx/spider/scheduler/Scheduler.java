package com.lx.spider.scheduler;

/**
 * @Author: lx
 * @Date: 2019/7/27 0027 上午 10:23
 */
public interface Scheduler {
    public void put(String url);

    public String get();

    public int size();
}
