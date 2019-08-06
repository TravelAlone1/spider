package com.lx.spider.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: lx
 * @Date: 2019/8/1 19:53
 */
@Data
public class WebPage implements Serializable {

    private String url;

    private String content;



}
