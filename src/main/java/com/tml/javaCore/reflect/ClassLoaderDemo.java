package com.tml.javaCore.reflect;

/**
 * Created by admin on 2019/2/20.
 */
public class ClassLoaderDemo {

    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println("hello world!");
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        System.out.println(classLoader);

        ClassLoader classLoader1 = classLoader.getParent();
        System.out.println(classLoader1);

        ClassLoader classLoader2 = classLoader1.getParent();
        System.out.println(classLoader2);

        System.out.println("使用Class的相对路径获取资源");
        System.out.println(ClassLoaderDemo.class.getResourceAsStream("../../../tml.txt"));
        System.out.println("使用Class的绝对路径获取资源");
        System.out.println(ClassLoaderDemo.class.getResourceAsStream("/tml.txt"));
        System.out.println("使用ClassLoader获取资源");
        System.out.println(ClassLoaderDemo.class.getClassLoader().getResourceAsStream("tml.txt"));


    }

}
