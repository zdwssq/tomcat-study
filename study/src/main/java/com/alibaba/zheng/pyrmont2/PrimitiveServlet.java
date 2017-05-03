package com.alibaba.zheng.pyrmont2;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author zhengxianwei.zxw
 * @description
 * @time 2017/4/24 20:36
 */
public class PrimitiveServlet implements Servlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("*init*");
    }

    @Override
    public ServletConfig getServletConfig() {
        System.out.println("*getServletConfig*");
        return null;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("from service");
        PrintWriter out = res.getWriter();
        out.println("roses are red");
        out.print("violets are blue");
    }

    @Override
    public String getServletInfo() {
        System.out.println("destroy");
        return null;
    }

    @Override
    public void destroy() {

    }
}
