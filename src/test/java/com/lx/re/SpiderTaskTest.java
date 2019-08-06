package com.lx.re;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: lx
 * @Date: 2019/8/6 9:42
 */
public class SpiderTaskTest {

    @Test
    public void test(){
        String str = "https://movie.douban.com/subject/27125418/?from=subject-page";

        String regEx = "https://movie.douban.com/subject/([0-9]{7}|[0-9]{8})/\\?from=subject-page";

        Pattern pattern = Pattern.compile(regEx);

        Matcher matcher = pattern.matcher(str);
        boolean rs = matcher.find();

        System.out.println(rs);

    }
}
