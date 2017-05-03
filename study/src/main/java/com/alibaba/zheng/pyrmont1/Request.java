package com.alibaba.zheng.pyrmont1;

import java.io.IOException;
import java.io.InputStream;


/**
 * @author zhengxianwei.zxw
 * @description
 * @time 2017/4/20 14:54
 */
public class Request {
    private InputStream input;
    private String uri;

    public Request(InputStream input) {
        this.input = input;
    }

    public void parse() {
        //TODO
        StringBuffer request = new StringBuffer(2048);
        int i;
        byte[] buffer = new byte[2048];
        try{
            //返回读取的字节数
            i = input.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
            i = -1;
        }

        for(int j = 0; j < i; j++) {
            request.append((char)buffer[j]);
        }
        System.out.println(request.toString());
        uri = parseUri(request.toString());
    }

    private String parseUri(String requestString) {
        int index1, index2;
        index1 = requestString.indexOf(' ');
        if(index1 != -1) {
            index2 = requestString.indexOf(' ', index1 + 1);
            if(index2 > index1) {
                return requestString.substring(index1 + 1, index2);
            }
            return null;
        }
        return null;
    }

    public String getUri() {
        return uri;
    }
}
