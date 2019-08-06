package com.lx.spider.entity;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @Author: lx
 * @Date: 2019/7/30 0030 下午 20:51
 */
public class Proxy {
    private static final List<ProxyServer> servers = Lists.newArrayList();


    public static void addServer(ProxyServer server){
        servers.add(server);
    }

    public static ProxyServer getServer(){
        if (servers.size() >0){
            return servers.get(new Random().nextInt(servers.size()));
        }
        return null;
    }
}
