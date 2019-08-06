package com.lx.spider.scheduler;

import com.google.common.base.Strings;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author: lx
 * @Date: 2019/7/27 0027 上午 10:31
 */
public class LocalScheduler implements Scheduler {


    PriorityBlockingQueue<String> queue = new PriorityBlockingQueue<String>();

    Set visited = new HashSet<String>();

    @Override
    public synchronized void put(String url) {
        if (!visited.contains(url)) {
            System.out.println(url);
            queue.add(url);
        }
    }

    @Override
    public synchronized String get() {
        try {
            String ret = queue.poll(5, TimeUnit.SECONDS);
            //sString ret = queue.take();
            if (!Strings.isNullOrEmpty(ret)) {
                visited.add(ret);
            }
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int size() {
        return queue.size();
    }
}
