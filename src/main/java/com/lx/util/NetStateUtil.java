package com.lx.util;

import com.lx.spider.scheduler.Scheduler;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import java.io.IOException;
import java.net.*;
import java.util.Enumeration;

/**
 * @Author: lx
 * @Date: 2019/7/30 0030 下午 21:35
 */
public class NetStateUtil {
    public static void printReachableIP(InetAddress remoteAddr, int port) {
        String retIP = null;
        Enumeration<NetworkInterface> netInterfaces;
        try {
            netInterfaces =
                    NetworkInterface.getNetworkInterfaces();
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = netInterfaces.nextElement();
                Enumeration<InetAddress> localAddrs = ni.getInetAddresses();
                while (localAddrs.hasMoreElements()) {
                    InetAddress localAddr =
                            localAddrs.nextElement();
                    if (isReachable(localAddr, remoteAddr, port, 5000)) {
                        retIP
                                = localAddr.getHostAddress();
                        break;
                    }
                }
            }
        } catch (SocketException e) {
            System.out.println("Error occurred while listing all the local network addresses.");
        }
        if (retIP == null) {
            System.out.println("NULL reachable local IP is found!");
        } else {
            System.out.println("Reachable local IP is found, it is " + retIP);
        }
    }


    public static boolean isReachable(InetAddress localInetAddr, InetAddress remoteInetAddr, int port, int timeout) {
        boolean isReachable = false;
        Socket socket = null;
        try {
            socket =
                    new Socket(); // 端口号设置为 0 表示在本地挑选一个可用端口进行连接
            SocketAddress localSocketAddr = new
                    InetSocketAddress(localInetAddr, 0);
            socket.bind(localSocketAddr);
            InetSocketAddress
                    endpointSocketAddr = new InetSocketAddress(remoteInetAddr, port);
            socket.connect(endpointSocketAddr, timeout);
            System.out.println("SUCCESS - connection established! Local: " + localInetAddr.getHostAddress() + " remote: " +
                    remoteInetAddr.getHostAddress() + " port" + port);
            isReachable = true;
        } catch (IOException e) {
            System.out.println("FAILRE - CAN not connect! Local: " +
                    localInetAddr.getHostAddress() + " remote: " + remoteInetAddr.getHostAddress() + " port" + port);
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    System.out.println("Error occurred while closing socket..");
                }
            }
        }
        return isReachable;
    }

    public static boolean isReachable(String host, int port) {
        String retIP = null;
        try {
            Enumeration<NetworkInterface> netInterfaces;
            InetAddress remoteAddr = InetAddress.getByName(host);
            netInterfaces = NetworkInterface.getNetworkInterfaces();
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = netInterfaces.nextElement();
                Enumeration<InetAddress> localAddrs = ni.getInetAddresses();
                while (localAddrs.hasMoreElements()) {
                    InetAddress localAddr = localAddrs.nextElement();
                    if (isReachable(localAddr, remoteAddr, port, 5000)) {
                        retIP = localAddr.getHostAddress();
                        //return true;
                        break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error occurred while listing all the local network addresses.");
        }
        if (retIP == null) {
            return false;
            //System.out.println("NULL reachable local IP is found!");
        } else {
            return true;
            //System.out.println("Reachable local IP is found, it is " + retIP);
        }
    }

    public static void main(String[] args) throws UnknownHostException {

        //netStateUtil.printReachableIP(InetAddress.getByName("www.baidu.com"),80);

        System.out.println( NetStateUtil.isReachable("14.215.177.39",80));

    }
}
