package com.alibaba.zheng.pyrmont1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author zhengxianwei.zxw
 * @description
 * @time 2017/4/20 14:36
 */
public class HttpServer {

    public static final String WEB_ROOT = "D:\\study\\tomcat";

    private static final String SHUTDOWN_COMMOND = "/SHUTDOWN";

    private boolean shutdown = false;

    public static void main(String[] args) {
        HttpServer server = new HttpServer();
        server.await();
    }

    public void await() {
        ServerSocket serverSocket = null;
        int port = 8080;
        try {
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        //循环等待客户端的连接
        while (!shutdown) {
            Socket socket = null;
            InputStream input = null;
            OutputStream output = null;

            try {
                socket = serverSocket.accept();
                input = socket.getInputStream();
                output = socket.getOutputStream();
                //根据请求输入构造request对象
                Request request = new Request(input);
                //request对象内部的解析操作
                request.parse();
                Response response = new Response(output);
                response.setRequest(request);
                response.setStaticResource();

                socket.close();
                shutdown = request.getUri().equals(SHUTDOWN_COMMOND);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }
    }
}
