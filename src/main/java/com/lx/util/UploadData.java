package com.lx.util;

import com.lx.spider.entity.Movie;

import java.io.*;

/**
 * @Author: lx
 * @Date: 2019/8/2 23:17
 */
public class UploadData {

    public static void upload( String input) throws IOException {
        File file = new File("D:\\work\\spider\\douban.txt");
        if(!file.exists()){
            file.createNewFile();
        }



        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            byte[] outStr = input.getBytes("UTF-8");
            fos.write(outStr);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            fos.close();
        }


    }


}
