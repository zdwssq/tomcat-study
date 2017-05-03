package com.alibaba.zheng.pyrmont2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author zhengxianwei.zxw
 * @description
 * @time 2017/4/24 20:58
 */
public class HttpServer2 {

    private static final String SHUTDOWN_COMMOND = "/SHUTDOWN";
    private boolean shutdown = false;

    public static void main(String[] args) {
        HttpServer2 server = new HttpServer2();
        server.await();
    }

    public void await() {
        ServerSocket serverSocket = null;
        int port = 8080;
        try{
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        while(!shutdown) {
            Socket socket = null;
            InputStream input = null;
            OutputStream output = null;
            try{
                socket = serverSocket.accept();
                input = socket.getInputStream();
                output = socket.getOutputStream();

                //request和response对象都是new出来的
                Request request = new Request(input);
                request.parse();
                Response response = new Response(output);
                response.setRequest(request);
                System.out.println("request=" + request);
                if(request.getUri().startsWith("/servlet/")) {
                    ServletProcessor2 processor = new ServletProcessor2();
                    processor.process(request, response);
                } else {
                    StaticResourceProcessor processor = new StaticResourceProcessor();
                    processor.process(request, response);
                }

                socket.close();
                shutdown = request.getUri().equals(SHUTDOWN_COMMOND);
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }
}
