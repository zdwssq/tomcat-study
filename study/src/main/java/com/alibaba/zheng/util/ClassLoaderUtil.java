package com.alibaba.zheng.util;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;
import java.security.CodeSigner;

import com.alibaba.zheng.pyrmont2.PrimitiveServlet;

/**
 * @author zhengxianwei.zxw
 * @description
 * @time 2017/4/28 11:36
 */
public class ClassLoaderUtil {

    public static void main(String[] args) {
        /*URL[] urls = new URL[1];
        String classPath = "file:D:/github/tomcat-study/study/target/classes/";
        try {
            urls[0] = new URL(classPath);
            URLClassLoader classLoader = new URLClassLoader(urls);
            Class myClass = classLoader.loadClass("com.alibaba.zheng.pyrmont2.PrimitiveServlet");
            PrimitiveServlet servlet = (PrimitiveServlet)myClass.newInstance();
            System.out.println(servlet.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        generateClassLoader();
    }

    private static void generateClassLoader() {
        String path = "D:/github/tomcat-study/study/target/classes";
        try {
            URL[] urls = new URL[1];
            URLStreamHandler streamHandler = null;
            File classPath = new File(path);
            String repository = (new URL("file", null, classPath.getCanonicalPath() + File.separator)).toString();
            urls[0] = new URL(null, repository, streamHandler);
            URLClassLoader loader = new URLClassLoader(urls);
            loader.loadClass("com.alibaba.zheng.pyrmont2.PrimitiveServlet");
        } catch (IOException e) {
            System.out.println(e.toString());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
