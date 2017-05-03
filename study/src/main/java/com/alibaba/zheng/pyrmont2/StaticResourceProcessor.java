package com.alibaba.zheng.pyrmont2;

import java.io.IOException;

/**
 * @author zhengxianwei.zxw
 * @description
 * @time 2017/4/24 21:55
 */
public class StaticResourceProcessor {

    public void process(Request request, Response response) {
        try {
            response.sendStaticResource();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
