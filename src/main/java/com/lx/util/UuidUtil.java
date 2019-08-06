package com.lx.util;

import java.util.Random;
import java.util.UUID;

/**
 * @Author: lx
 * @Date: 2019/8/1 14:57
 */
public class UuidUtil {

    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","").toLowerCase();
    }

    public static String[] getUUId(int number){
        if (number<1){
            return null;
        }
        String[] ss = new String[number];
        for (int i= 0;i<number;i++){
            ss[i]=getUUID();
        }
        return ss;
    }
}
