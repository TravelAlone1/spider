package com.lx.util;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: lx
 * @Date: 2019/8/2 23:10
 */
@Data
public class JSONResult implements Serializable {

    private static final Long serialVersionUID= -3948389268046368059L;

    private Integer code;

    private String msg;

    private Object data;

    public void setResultCode(ResultCode code) {
        this.code = code.code();
        this.msg = code.message();
    }

    //成功 不返回数据直接返回成功信息
    public static JSONResult success(){
        JSONResult result=new JSONResult();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    //成功 并且加上返回数据
    public static JSONResult success(Object data){
        JSONResult result =new JSONResult();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }
    //成功 自定义成功返回状态 加上数据
    public static JSONResult success(ResultCode resultCode,Object data){
        JSONResult result=new JSONResult();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }

    public static JSONResult failure(ResultCode resultCode){
        JSONResult result=new JSONResult();
        result.setResultCode(resultCode);
        return result;
    }

    public static JSONResult failure(ResultCode resultCode,Object data){
        JSONResult result=new JSONResult();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }

    public static JSONResult failure(Object data){
        JSONResult result = new JSONResult();
        result.setData(data);
        return result;
    }
}
